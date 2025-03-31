import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/components/product/ProductList.scss';

const ProductList = ({ products }) => {
  const [productList, setProductList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  return (
    <section>
      <div className="item-list">
        {productList.map(product => (
          <div className="item" key={product.productId}>
            <Link to={`/product/detail/${product.productId}`}>
              <img
                src={`/upload/${product.fileName}`}
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

      {/*TODO: 반복 요소인 pagination 버튼 메뉴를 컴포넌트화 시키기*/}
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
