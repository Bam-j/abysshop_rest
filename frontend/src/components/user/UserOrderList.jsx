import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

import Spinner from 'react-bootstrap/Spinner';

const UserOrderList = ({ user }) => {
  const [orders, setOrders] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchOrders = async () => {
      const token = localStorage.getItem('accessToken');
      const page = currentPage - 1;
      const size = 10;

      try {
        const response = await fetch(
          `http://localhost:8080/api/users/my-page/orders?userId=${user.userId}&page=${page}&size=${size}`,
          {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`,
            },
          },
        );

        if (!response.ok) {
          const errorData = await response.json();
          setOrders([]);
          setTotalPages(1);
          setErrorMessage(errorData.message || '주문 정보를 불러오는 데 실패했습니다.');
          return;
        }

        const data = await response.json();

        setOrders(data.orders);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('주문 조회 에러:', error);
        setErrorMessage('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
      }
    };

    fetchOrders();
  }, [currentPage]);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'order-management', page: newPage });
  };

  return (
    <section className="user-order-list">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>주문 번호</th>
          <th>주문 합계 포인트</th>
          <th>구매일</th>
          <th>주문 상태</th>
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
        ) : orders === null ? (
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
        ) : orders.length === 0 ? (
          <tr>
            <td colSpan="4">
              <div className="text-center" style={{ padding: '40px 0' }}>
                주문 내역이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          orders.map(order => (
            <tr key={order.orderId}>
              <td>{order.orderId}</td>
              <td>{order.totalPrice.toLocaleString()}</td>
              <td>{new Date(order.orderedAt).toLocaleDateString()}</td>
              <td data-state={order.orderState}>{order.orderState}</td>
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

export default UserOrderList;
