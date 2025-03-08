import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const AdminPointRechargeManagement = () => {
  const [pointRequests, setPointRequests] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
  useEffect(() => {
  }, []);

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
        <strong>포인트 지급 전 반드시 요청 정보에 정보를 입력해주세요.</strong>
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
        {pointRequests.map(request => (
          <tr key={request.rechargeId}>
            <td>{request.rechargeId}</td>
            <td>{request.nickname}</td>
            <td>{request.points.toLocaleString()}</td>
            <td>{new Date(request.requestTime).toLocaleDateString()}</td>
            <td>
              <div className="btn-group">
                <button type="button"
                        className="btn btn-success dropdown-toggle"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                  {request.rechargeRequestState}
                </button>
                <ul className="dropdown-menu">
                  <li>
                    <button className="dropdown-item"
                            onClick={() => handleChangeState(request.rechargeId,
                              'pending_payment')}>
                      송금 확인 대기
                    </button>
                  </li>
                  <li>
                    <button className="dropdown-item"
                            onClick={() => handleChangeState(request.rechargeId,
                              'pending_point_deposit')}>
                      포인트 지급 대기
                    </button>
                  </li>
                  <li>
                    <button className="dropdown-item"
                            onClick={() => handleChangeState(request.rechargeId,
                              'completed')}>
                      포인트 지급 완료
                    </button>
                  </li>
                  <li>
                    <button className="dropdown-item"
                            onClick={() => handleChangeState(request.rechargeId,
                              'refunded')}>
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
                disabled={request.rechargeRequestState === 'completed'
                  || request.rechargeRequestState === 'refunded'}>
                지급 승인
              </button>
            </td>
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
