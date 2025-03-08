import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

import '../styles/pages/ProductDetail.scss';

const ProductDetailPage = () => {
  const { productId } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    // TODO: 백엔드 API 호출하여 상품 정보 및 사용자 정보 가져오기
  }, []);

  const handleAddToCart = async () => {
    //장바구니 담기 동작 제어
  };

  return (
    <>
      <nav>
        <button onClick={() => navigate('/')}
                className="btn btn-outline-primary btn-sm">
          <i className="bi bi-arrow-left"></i> 목록으로
        </button>
      </nav>
      <section>
        <img
          src={`/upload/${product.originalFileName}`}
          alt="상품 이미지"
          id="product-detail-image"
        />
        <ul id="product-detail-info">
          <li>
            <h2><strong>{product.productName}</strong></h2>
          </li>
          <li>
            <h3>
              <strong>{product.price.toLocaleString()} 포인트</strong>
            </h3>
          </li>
          <li id="product-description">
            <pre>{product.description}</pre>
          </li>
          <li>
            <button onClick={handleAddToCart} className="btn btn-primary">
              <i className="bi bi-cart"></i> 장바구니 담기
            </button>
          </li>
        </ul>
      </section>
    </>
  );
};

export default ProductDetailPage;
