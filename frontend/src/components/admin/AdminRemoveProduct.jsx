import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const AdminRemoveProduct = () => {
  const [productList, setProductList] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [errorMessage, setErrorMessage] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/admin/dashboard/products?page=${currentPage
          - 1}&size=10`);
        if (!response.ok) {
          throw new Error('상품 데이터를 불러오는 데 실패했습니다.');
        }
        const data = await response.json();
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

      const response = await fetch(
        'http://localhost:8080/api/admin/products/delete',
        {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({ productId }),
        },
      );

      if (!response.ok) {
        const errorData = await response.json();
        setErrorMessage(errorData.message || '상품 삭제에 실패했습니다.');
        return;
      }

      alert('상품이 삭제되었습니다.');

      setProductList(prev =>
        prev.filter(product => product.productId !== productId),
      );
    } catch (error) {
      console.error('상품 삭제 오류:', error);
      setErrorMessage('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
    }
  };

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'remove-product', page: newPage });
  };

  return (
    <section className="remove-product">
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

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
        {productList.map(product => (
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

export default AdminRemoveProduct;
