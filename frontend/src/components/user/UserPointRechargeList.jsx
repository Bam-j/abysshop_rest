import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";

const UserPointRechargeList = () => {
  const [requests, setRequests] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get("page")) || 1;

  //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
  useEffect(() => {
  }, []);

  const handlePageChange = newPage => {
    setSearchParams({ menu: "point-request", page: newPage });
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
        {requests.map(request => (
          <tr key={request.rechargeId}>
            <td>{request.rechargeId}</td>
            <td>{request.points.toLocaleString()}</td>
            <td>{new Date(request.requestTime).toLocaleDateString()}</td>
            <td data-state={request.rechargeRequestState}>{request.rechargeRequestState}</td>
          </tr>
        ))}
        </tbody>
      </table>

      <div className="pagination">
        {currentPage > 1 && (
          <button onClick={() => handlePageChange(currentPage - 1)} className="page-link">
            &laquo;
          </button>
        )}

        {[...Array(totalPages)].map((_, i) => (
          <button
            key={i + 1}
            onClick={() => handlePageChange(i + 1)}
            className={`page-link ${i + 1 === currentPage ? "active" : ""}`}
          >
            {i + 1}
          </button>
        ))}

        {currentPage < totalPages && (
          <button onClick={() => handlePageChange(currentPage + 1)} className="page-link">
            &raquo;
          </button>
        )}
      </div>
    </section>
  );
};

export default UserPointRechargeList;
