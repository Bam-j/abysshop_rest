import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import Pagination from '../common/Pagination';
import api from '../../api/axiosInstance';

const AdminPointRechargeDetailManagement = () => {
  const [rechargeDetails, setRechargeDetails] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchRechargeDetails = async () => {
      const token = localStorage.getItem('accessToken');

      try {
        const response = await api.get(
          '/admin/dashboard/point-recharge/details',
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            params: {
              page: currentPage - 1,
              size: 10,
              sort: 'pointRechargeDetailId,desc',
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
    const value = e.target.value;

    setRechargeDetails(prevDetails =>
      prevDetails.map((detail, i) => {
        if (i === index) {
          return {
            ...detail,
            [field]: field === 'depositAmount'
              ? value.replace(/,/g, '')
              : value,
          };
        }
        return detail;
      }));
  };

  const handleSubmit = async (e, rechargeDetailId, index) => {
    e.preventDefault();
    const token = localStorage.getItem('accessToken');
    const { bank, accountNumber, depositAmount } = rechargeDetails[index];

    try {
      await api.patch(
        '/admin/point-recharges/details/update',
        {
          pointRechargeDetailId: rechargeDetailId,
          bank,
          accountNumber,
          depositAmount: depositAmount ? Number(
            depositAmount.toString().replace(/,/g, '')) : 0,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      alert('입금 정보가 성공적으로 저장되었습니다.');
    } catch (error) {
      console.error('입금 정보 저장 실패:', error);
      alert('입금 정보를 저장하는 중 오류가 발생했습니다.');
    }
  };

  return (
    <section className="point-recharge-detail">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>요청 정보 번호</th>
          <th>요청 번호</th>
          <th>입금 확인일</th>
          <th>은행, 계좌번호, 입금액</th>
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
        ) : rechargeDetails === null ? (
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
        ) : rechargeDetails.length === 0 ? (
          <tr>
            <td colSpan="5">
              <div className="text-center" style={{ padding: '40px 0' }}>
                입금 상세 내역이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          rechargeDetails.map((detail, index) => (
            <tr key={detail.rechargeDetailId}>
              <td>{detail.rechargeDetailId}</td>
              <td>{detail.rechargeId}</td>
              <td>
                {detail.depositConfirmedTime
                  ? new Date(detail.depositConfirmedTime).toLocaleDateString()
                  : ''}
              </td>
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
                    <input
                      type="text"
                      className="form-control"
                      placeholder="입금액"
                      value={
                        detail.depositAmount !== null && detail.depositAmount
                        !== undefined
                          ? Number(detail.depositAmount).toLocaleString()
                          : ''
                      }
                      onChange={e => handleInputChange(e, index,
                        'depositAmount')}
                    />
                    <button type="submit" className="btn btn-success">
                      입력
                    </button>
                  </div>
                </form>
              </td>
            </tr>
          ))
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

export default AdminPointRechargeDetailManagement;
