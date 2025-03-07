import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../styles/components/ProductList.scss';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  useEffect(() => {
    //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
    fetch(`/api/products?page=${currentPage}`)
    .then(res => res.json())
    .then(data => {
      setProducts(data.products);
      setTotalPages(data.totalPages);
    })
    .catch(error => console.error('Error fetching products:', error));
  }, [currentPage]);

  return (
    <section>
      <div className="item-list">
        {products.map(product => (
          <div className="item" key={product.productId}>
            <Link to={`/product/detail/${product.productId}`}>
              <img
                src={`/upload/${product.originalFileName}`}
                alt={product.productName}
              />
              <div className="card-body">
                <h5 className="card-title">{product.productName}</h5>
                <p className="card-text">
                  {new Intl.NumberFormat('ko-KR').format(product.price)}원
                </p>
              </div>
            </Link>
          </div>
        ))}
      </div>

      <div className="pagination">
        {currentPage > 1 && (
          <button className="page-link"
                  onClick={() => setCurrentPage(currentPage - 1)}>
            &laquo;
          </button>
        )}
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i + 1}
            className={`page-link ${currentPage === i + 1 ? 'active' : ''}`}
            onClick={() => setCurrentPage(i + 1)}
          >
            {i + 1}
          </button>
        ))}
        {currentPage < totalPages && (
          <button className="page-link"
                  onClick={() => setCurrentPage(currentPage + 1)}>
            &raquo;
          </button>
        )}
      </div>
    </section>
  );
};

export default ProductList;
