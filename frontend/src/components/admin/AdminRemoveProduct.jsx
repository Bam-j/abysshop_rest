import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

//TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
const AdminRemoveProduct = () => {
  const [productList, setProductList] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = parseInt(searchParams.get('page')) || 1;

  useEffect(() => {
  }, []);

  const handlePageChange = newPage => {
    setSearchParams({ menu: 'remove-product', page: newPage });
  };

  const handleDeleteProduct = async (productId, productName) => {
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

export default AdminRemoveProduct;
