import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const UserOrderList = () => {
  const [orders, setOrders] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
  useEffect(() => {
  }, []);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'order-management', page: newPage });
  };

  return (
    <section className="user-order-list">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>주문 번호</th>
          <th>주문 총 포인트</th>
          <th>구매일</th>
          <th>주문 상태</th>
        </tr>
        </thead>
        <tbody>
        {orders.map(order => (
          <tr key={order.orderId}>
            <td>{order.orderId}</td>
            <td>{order.totalPoints.toLocaleString()}</td>
            <td>{new Date(order.orderDate).toLocaleDateString()}</td>
            <td data-state={order.orderState}>{order.orderState}</td>
          </tr>
        ))}
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
