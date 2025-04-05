import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import '../../styles/components/product/ProductList.scss';

const ProductList = ({ products }) => {
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 12;
  const [totalPages, setTotalPages] = useState(1);
  const [productList, setProductList] = useState([]);

  useEffect(() => {
    if (Array.isArray(products)) {
      setProductList(products);
      setTotalPages(Math.ceil(products.length / itemsPerPage));
    } else {
      setProductList([]);
      setTotalPages(1);
    }
  }, [products]);

  const paginatedProducts = productList.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage,
  );

  return (
    <section>
      <div className="item-list">
        {paginatedProducts.map(product => (
          <div className="item" key={product.productId}>
            <Link to={`/products/${product.productId}`}>
              <img
                src={
                  product.fileName ?
                    `/upload/${product.fileName}` : '/product_temp_128x128.png'
                }
                alt={product.productName}
              />
              <div className="card-body">
                <h5 className="card-title">{product.productName}</h5>
                <p className="card-text">
                  {new Intl.NumberFormat('ko-KR').format(product.price)} 포인트
                </p>
              </div>
            </Link>
          </div>
        ))}
      </div>

      {/* 페이지네이션 버튼 */}
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
