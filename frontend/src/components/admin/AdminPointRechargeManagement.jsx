import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import { Spinner, ButtonGroup, Dropdown } from 'react-bootstrap';
import {
  POINT_RECHARGE_STATE,
  POINT_RECHARGE_STATE_LABEL,
} from '../../constants/pointRechargeStates';
import axios from 'axios';
import Pagination from '../common/Pagination';

const AdminPointRechargeManagement = () => {
  const [pointRequests, setPointRequests] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [selectedState, setSelectedState] = useState({});
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchPointRecharges = async () => {
      const token = localStorage.getItem('accessToken');

      try {
        const response = await axios.get(
          'http://localhost:8080/api/admin/dashboard/point-recharges',
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            params: {
              page: currentPage - 1,
              size: 10,
              sort: 'requestedAt,desc',
            },
          },
        );

        const data = response.data;
        const mapped = data.pointRecharges.map(item => ({
          rechargeId: item.pointRechargeId,
          userId: item.userId,
          nickname: item.nickname,
          points: item.requestedPoints,
          requestTime: item.requestedAt,
          rechargeRequestState: item.rechargeState,
        }));

        setPointRequests(mapped);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('포인트 요청 목록 불러오기 실패:', error);
        setErrorMessage('포인트 요청 데이터를 불러오는 데 실패했습니다.');
      }
    };

    fetchPointRecharges();
  }, [currentPage]);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'point-recharge-management', page: newPage });
  };

  const handleChangeState = async (rechargeId, newState) => {
    const token = localStorage.getItem('accessToken');

    try {
      await axios.patch(
        'http://localhost:8080/api/admin/point-recharges/state',
        {
          rechargeId,
          newState,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      setSelectedState(prev => ({
        ...prev,
        [rechargeId]: newState,
      }));
    } catch (error) {
      console.error('상태 변경 실패:', error);
      alert('상태 변경에 실패했습니다.');
    }
  };

  const handleApprovePoint = async (userId, points, rechargeRequestState,
    rechargeId) => {
    const token = localStorage.getItem('accessToken');

    if (rechargeRequestState === POINT_RECHARGE_STATE.COMPLETED ||
      rechargeRequestState === POINT_RECHARGE_STATE.REFUNDED) {
      alert('이미 처리된 요청입니다.');
      return;
    }

    try {
      await axios.patch(
        'http://localhost:8080/api/admin/points/provide',
        {
          userId,
          points,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      setSelectedState(prev => ({
        ...prev,
        [rechargeId]: POINT_RECHARGE_STATE.COMPLETED,
      }));

      alert('포인트 지급이 완료되었습니다.');
    } catch (error) {
      console.error('포인트 지급 실패:', error);
      alert('포인트 지급 중 오류가 발생했습니다.');
    }
  };

  return (
    <section className="point-recharge-management">
      <h3 className="alert alert-warning">
        <strong>포인트 지급 전 반드시 요청 정보 관리에서 입금 정보를 입력해주세요.</strong>
      </h3>
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>요청 번호</th>
          <th>요청자</th>
          <th>요청 포인트</th>
          <th>요청일</th>
          <th>요청 상태</th>
          <th>요청 승인</th>
        </tr>
        </thead>
        <tbody>
        {errorMessage ? (
          <tr>
            <td colSpan="6">
              <div className="text-danger text-center"
                   style={{ padding: '40px 0' }}>
                {errorMessage}
              </div>
            </td>
          </tr>
        ) : pointRequests === null ? (
          <tr>
            <td colSpan="6">
              <div
                className="d-flex justify-content-center align-items-center"
                style={{ height: '200px' }}
              >
                <Spinner animation="border" variant="primary" role="status">
                  <span className="visually-hidden">로딩 중...</span>
                </Spinner>
              </div>
            </td>
          </tr>
        ) : pointRequests.length === 0 ? (
          <tr>
            <td colSpan="6">
              <div className="text-center" style={{ padding: '40px 0' }}>
                포인트 충전 요청 내역이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          pointRequests.map(request => {
            const currentState = selectedState[request.rechargeId]
              || request.rechargeRequestState;
            const displayState = POINT_RECHARGE_STATE_LABEL[currentState];

            return (
              <tr key={request.rechargeId}>
                <td>{request.rechargeId}</td>
                <td>{request.nickname}</td>
                <td>{request.points.toLocaleString()}</td>
                <td>{new Date(request.requestTime).toLocaleDateString()}</td>
                <td>
                  <Dropdown as={ButtonGroup}>
                    <Dropdown.Toggle
                      variant="primary"
                      id={`dropdown-${request.rechargeId}`}
                      style={{
                        width: '140px',
                        textAlign: 'center',
                      }}
                    >
                      {displayState}
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                      {Object.entries(POINT_RECHARGE_STATE).map(
                        ([key, value]) => (
                          <Dropdown.Item
                            key={key}
                            onClick={() => handleChangeState(request.rechargeId,
                              value)}
                          >
                            {POINT_RECHARGE_STATE_LABEL[value]}
                          </Dropdown.Item>
                        ))}
                    </Dropdown.Menu>
                  </Dropdown>
                </td>
                <td>
                  <button
                    className="btn btn-success"
                    onClick={() =>
                      handleApprovePoint(request.userId, request.points,
                        currentState, request.rechargeId)}
                    disabled={currentState === 'COMPLETED' || currentState
                      === 'REFUNDED'}
                  >
                    지급 승인
                  </button>
                </td>
              </tr>
            );
          })
        )}
        </tbody>
      </table>

      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </section>
  );
};

export default AdminPointRechargeManagement;
