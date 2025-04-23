import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import Spinner from 'react-bootstrap/Spinner';
import Pagination from '../common/Pagination';
import api from '../../api/axiosInstance';

const AdminRemoveProduct = () => {
  const [productList, setProductList] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchProducts = async () => {
      const token = localStorage.getItem('accessToken');

      try {
        const response = await api.get(
          '/admin/dashboard/products',
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            params: {
              page: currentPage - 1,
              size: 10,
            },
          },
        );

        const data = response.data;
        setProductList(data.products);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error('상품 목록 불러오기 실패:', error);
        setErrorMessage('상품 목록을 불러오는 중 오류가 발생했습니다.');
      }
    };

    fetchProducts();
  }, [currentPage]);

  const handleDeleteProduct = async (productId, productName) => {
    const confirmed = window.confirm(`'${productName}' 상품을 삭제하시겠습니까?`);
    if (!confirmed) {
      return;
    }

    try {
      const token = localStorage.getItem('accessToken');

      const response = await api.delete(
        '/admin/products/delete',
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          data: { productId },
        },
      );

      alert('상품이 삭제되었습니다.');

      setProductList(prev =>
        prev.filter(product => product.productId !== productId),
      );
    } catch (error) {
      console.error('상품 삭제 오류:', error);
      const message = error.response?.data?.message || '상품 삭제에 실패했습니다.';
      setErrorMessage(message);
    }
  };

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'remove-product', page: newPage });
  };

  return (
    <section className="remove-product">
      <table className="table table-hover">
        <thead>
        <tr className="table-primary">
          <th>상품 번호</th>
          <th>상품명</th>
          <th>가격</th>
          <th>삭제</th>
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
        ) : productList === null ? (
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
        ) : productList.length === 0 ? (
          <tr>
            <td colSpan="4">
              <div className="text-center" style={{ padding: '40px 0' }}>
                등록된 상품이 없습니다.
              </div>
            </td>
          </tr>
        ) : (
          productList.map(product => (
            <tr key={product.productId}>
              <td>{product.productId}</td>
              <td>{product.productName}</td>
              <td>{product.price.toLocaleString()} 포인트</td>
              <td>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDeleteProduct(product.productId,
                    product.productName)}
                >
                  품목 삭제
                </button>
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

export default AdminRemoveProduct;
