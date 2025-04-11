import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import axios from 'axios';

const AdminOrderManagement = () => {
  const [orders, setOrders] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get(
          'http://localhost:8080/api/admin/dashboard/orders', {
            params: {
              page: currentPage - 1,
              size: 10,
              sort: 'orderedAt,desc',
            },
          });

        const data = response.data;

        setOrders(data.orders);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('주문 목록 불러오기 실패:', error);
        setErrorMessage('주문 데이터를 불러오지 못했습니다.');
      }
    };

    fetchOrders();
  }, [currentPage]);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'order-management', page: newPage });
  };

  const handleChangeOrderState = async (orderId, newState) => {
  };

  return (
    <section className="admin-order-management">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>주문 번호</th>
          <th>주문자</th>
          <th>주문 포인트</th>
          <th>구매일</th>
          <th>주문 상태</th>
        </tr>
        </thead>
        <tbody>
        {errorMessage ? (
          <tr>
            <td colSpan="5">
              <div className="text-danger text-center"
                   style={{ padding: '40px 0' }}>
                {errorMessage}
              </div>
            </td>
          </tr>
        ) : orders === null ? (
          <tr>
            <td colSpan="5">
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
            <td colSpan="5">
              <div className="text-center" style={{ padding: '40px 0' }}>
                주문 내역이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          orders.map(order => (
            <tr key={order.orderId}>
              <td>{order.orderId}</td>
              <td>{order.userId}. {order.nickname}</td>
              <td>{order.totalPoints.toLocaleString()}</td>
              <td>{new Date(order.orderDate).toLocaleDateString()}</td>
              <td>
                <div className="btn-group">
                  <button
                    type="button"
                    className="btn btn-success dropdown-toggle"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {order.orderState}
                  </button>
                  <ul className="dropdown-menu">
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeOrderState(order.orderId,
                          'shipping')}
                      >
                        상품 지급 대기
                      </button>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeOrderState(order.orderId,
                          'completed')}
                      >
                        상품 지급 완료
                      </button>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleChangeOrderState(order.orderId,
                          'refunded')}
                      >
                        환불 처리 완료
                      </button>
                    </li>
                  </ul>
                </div>
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

export default AdminOrderManagement;
