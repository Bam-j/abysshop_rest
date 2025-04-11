import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import axios from 'axios';

const AdminPointRechargeManagement = () => {
  const [pointRequests, setPointRequests] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchPointRecharges = async () => {
      try {
        const response = await axios.get(
          'http://localhost:8080/api/admin/dashboard/point-recharges', {
            params: {
              page: currentPage - 1,
              size: 10,
              sort: 'requestedAt,desc',
            },
          });

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
  };

  const handleApprovePoint = async (userId, points, rechargeRequestState) => {
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
              <div className="d-flex justify-content-center align-items-center"
                   style={{ height: '200px' }}>
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
          pointRequests.map(request => (
            <tr key={request.rechargeId}>
              <td>{request.rechargeId}</td>
              <td>{request.nickname}</td>
              <td>{request.points.toLocaleString()}</td>
              <td>{new Date(request.requestTime).toLocaleDateString()}</td>
              <td>
                <div className="btn-group">
                  <button
                    type="button"
                    className="btn btn-success dropdown-toggle"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {request.rechargeRequestState}
                  </button>
                  <ul className="dropdown-menu">
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeState(request.rechargeId,
                          'pending_payment')}
                      >
                        송금 확인 대기
                      </button>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeState(request.rechargeId,
                          'pending_point_deposit')}
                      >
                        포인트 지급 대기
                      </button>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeState(request.rechargeId,
                          'completed')}
                      >
                        포인트 지급 완료
                      </button>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeState(request.rechargeId,
                          'refunded')}
                      >
                        환불 처리 완료
                      </button>
                    </li>
                  </ul>
                </div>
              </td>
              <td>
                <button
                  className="btn btn-success"
                  onClick={() => handleApprovePoint(request.userId,
                    request.points, request.rechargeRequestState)}
                  disabled={
                    request.rechargeRequestState === 'completed' ||
                    request.rechargeRequestState === 'refunded'
                  }
                >
                  지급 승인
                </button>
              </td>
            </tr>
          ))
        )}
        </tbody>

      </table>

      <div className="pagination">
        {currentPage > 1 && (
          <button onClick={() => handlePageChange(currentPage - 1)}
                  className="page-link">
            &laquo;
          </button>
        )}

        {[...Array(totalPages)].map((_, i) => (
          <button
            key={i + 1}
            onClick={() => handlePageChange(i + 1)}
            className={`page-link ${i + 1 === currentPage ? 'active' : ''}`}>
            {i + 1}
          </button>
        ))}

        {currentPage < totalPages && (
          <button onClick={() => handlePageChange(currentPage + 1)}
                  className="page-link">
            &raquo;
          </button>
        )}
      </div>
    </section>
  );
};

export default AdminPointRechargeManagement;
