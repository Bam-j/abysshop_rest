import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import { Dropdown, ButtonGroup } from 'react-bootstrap';
import { ORDER_STATE, ORDER_STATE_LABEL } from '../../constants/orderStates';
import axios from 'axios';
import Pagination from '../common/Pagination';

const AdminOrderManagement = () => {
  const [orders, setOrders] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [selectedOrderState, setSelectedOrderState] = useState({});
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchOrders = async () => {
      const token = localStorage.getItem('accessToken');

      try {
        const response = await axios.get(
          'http://localhost:8080/api/admin/dashboard/orders', {
            headers: {
              Authorization: `Bearer ${token}`,
            },
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
    const token = localStorage.getItem('accessToken');

    try {
      await axios.patch(
        'http://localhost:8080/api/admin/orders/state',
        {
          orderId,
          newState,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      setSelectedOrderState(prev => ({
        ...prev,
        [orderId]: newState,
      }));
    } catch (error) {
      console.error('주문 상태 변경 실패:', error);
      alert('주문 상태 변경에 실패했습니다.');
    }
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
          orders.map(order => {
            const currentState = selectedOrderState[order.orderId]
              || order.orderState;
            const displayState = ORDER_STATE_LABEL[currentState]
              || currentState;

            return (
              <tr key={order.orderId}>
                <td>{order.orderId}</td>
                <td>{order.userId}. {order.nickname}</td>
                <td>{order.totalPoints.toLocaleString()}</td>
                <td>{new Date(order.orderDate).toLocaleDateString()}</td>
                <td>
                  <Dropdown as={ButtonGroup}>
                    <Dropdown.Toggle
                      variant="primary"
                      id={`dropdown-${order.orderId}`}
                      style={{
                        width: '160px',
                        textAlign: 'center',
                      }}
                    >
                      {displayState}
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                      {Object.entries(ORDER_STATE).map(([key, value]) => (
                        <Dropdown.Item
                          key={key}
                          onClick={() => handleChangeOrderState(order.orderId, value)}
                        >
                          {ORDER_STATE_LABEL[value]}
                        </Dropdown.Item>
                      ))}
                    </Dropdown.Menu>
                  </Dropdown>
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

export default AdminOrderManagement;
