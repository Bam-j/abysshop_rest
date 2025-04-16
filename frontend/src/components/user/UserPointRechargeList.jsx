import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import { POINT_RECHARGE_STATE_LABEL } from '../../constants/pointRechargeStates';
import axios from 'axios';

const UserPointRechargeList = ({ user }) => {
  const [requests, setRequests] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchPointRecharges = async () => {
      const token = localStorage.getItem('accessToken');
      const page = currentPage - 1;
      const size = 10;

      try {
        const response = await axios.get(
          'http://localhost:8080/api/users/my-page/point-recharges',
          {
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`,
            },
            params: {
              userId: user.userId,
              page,
              size,
            },
          },
        );

        const data = response.data;
        setRequests(data.pointRecharges);
        setTotalPages(data.totalPages);
      } catch (error) {
        const message = error.response?.data?.message
          || '포인트 요청 정보를 불러오는 데 실패했습니다.';
        setRequests([]);
        setTotalPages(1);
        setErrorMessage(message);
        console.error('포인트 요청 조회 에러:', error);
      }
    };

    fetchPointRecharges();
  }, [user?.userId, currentPage]);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'point-request', page: newPage });
  };

  return (
    <section className="user-point-recharge-list">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>요청 번호</th>
          <th>요청 포인트</th>
          <th>요청일</th>
          <th>요청 상태</th>
        </tr>
        </thead>
        <tbody>
        {errorMessage ? (
          <tr>
            <td colSpan="4">
              <div className="text-danger text-center"
                   style={{ padding: '40px 0' }}>
                {errorMessage}
              </div>
            </td>
          </tr>
        ) : requests === null ? (
          <tr>
            <td colSpan="4">
              <div className="d-flex justify-content-center align-items-center"
                   style={{ height: '200px' }}>
                <Spinner animation="border" variant="primary" role="status">
                  <span className="visually-hidden">로딩 중...</span>
                </Spinner>
              </div>
            </td>
          </tr>
        ) : requests.length === 0 ? (
          <tr>
            <td colSpan="4">
              <div className="text-center" style={{ padding: '40px 0' }}>
                포인트 충전 요청 내역이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          requests.map(request => (
            <tr key={request.pointRechargeId}>
              <td>{request.pointRechargeId}</td>
              <td>{request.requestedPoints.toLocaleString()}</td>
              <td>{new Date(request.requestedAt).toLocaleDateString()}</td>
              <td data-state={request.rechargeState}>
                {POINT_RECHARGE_STATE_LABEL[request.rechargeState] || request.rechargeState}
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
            className={`page-link ${i + 1 === currentPage ? 'active' : ''}`}
          >
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

export default UserPointRechargeList;
