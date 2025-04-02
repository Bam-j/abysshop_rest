import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

const AdminPointRechargeDetailManagement = () => {
  const [rechargeDetails, setRechargeDetails] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchRechargeDetails = async () => {
      try {
        const response = await axios.get(
          'http://localhost:8080/api/admin/dashboard/point-recharge/details', {
            params: {
              page: currentPage - 1,
              size: 10,
              sort: 'depositConfirmedAt,desc',
            },
          });

        const data = response.data;

        const mapped = data.pointRechargeDetails.map(detail => ({
          rechargeDetailId: detail.pointRechargeDetailId,
          rechargeId: detail.pointRechargeId,
          bank: detail.bank,
          accountNumber: detail.accountNumber,
          depositAmount: detail.depositAmount,
          depositConfirmedTime: detail.depositConfirmedAt,
        }));

        setRechargeDetails(mapped);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('충전 상세 정보 로딩 실패:', error);
        setErrorMessage('충전 상세 정보를 불러오는 데 실패했습니다.');
      }
    };

    fetchRechargeDetails();
  }, [currentPage]);

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
          <th>입금 확인 일</th>
          <th>입금액</th>
          <th>은행, 계좌번호</th>
        </tr>
        </thead>
        <tbody>
        {rechargeDetails.map((detail, index) => (
          <tr key={detail.rechargeDetailId}>
            <td>{detail.rechargeDetailId}</td>
            <td>{detail.rechargeId}</td>
            <td>{detail.depositAmount.toLocaleString()}</td>
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
