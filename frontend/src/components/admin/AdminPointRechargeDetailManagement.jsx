import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

import '../../styles/components/admin/PointRechargeDetail.scss';

const AdminPointRechargeDetailManagement = () => {
  const [rechargeDetails, setRechargeDetails] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
  useEffect(() => {
  }, []);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'point-recharge-detail', page: newPage });
  };

  const handleInputChange = (e, index, field) => {
  };

  const handleSubmit = async (e, rechargeDetailId, index) => {
    e.preventDefault();
  };

  return (
    <section className="point-recharge-detail">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>요청 정보 번호</th>
          <th>요청 번호</th>
          <th>주문자</th>
          <th>입금 확인 일</th>
          <th>은행, 계좌번호</th>
        </tr>
        </thead>
        <tbody>
        {rechargeDetails.map((detail, index) => (
          <tr key={detail.rechargeDetailId}>
            <td>{detail.rechargeDetailId}</td>
            <td>{detail.rechargeId}</td>
            <td>{detail.nickname}</td>
            <td>{new Date(
              detail.depositConfirmedTime).toLocaleDateString()}</td>
            <td>
              <form onSubmit={e => handleSubmit(e, detail.rechargeDetailId,
                index)}>
                <div className="detail-input">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="은행"
                    value={detail.bank || ''}
                    onChange={e => handleInputChange(e, index, 'bank')}
                  />
                  <input
                    type="text"
                    className="form-control"
                    placeholder="계좌번호"
                    value={detail.accountNumber || ''}
                    onChange={e => handleInputChange(e, index,
                      'accountNumber')}
                  />
                  <button type="submit" className="btn btn-success">
                    입력
                  </button>
                </div>
              </form>
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

export default AdminPointRechargeDetailManagement;
