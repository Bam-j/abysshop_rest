import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import Spinner from 'react-bootstrap/Spinner';
import axios from 'axios';

import useUserStore from '../stores/userUserStore';

import '../styles/pages/ProductDetail.scss';

const ProductDetailPage = () => {
  const { productId } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const { user } = useUserStore();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/products/${productId}`);
        setProduct(response.data);
      } catch (error) {
        console.error('상품 정보를 불러오는 중 오류 발생: ', error);
      }
    };

    fetchProduct();
  }, [productId]);

  const handleAddToCart = async () => {
    if (!user) {
      alert('로그인이 필요한 기능입니다.');
      return;
    }

    if (user.userType === 'ADMIN') {
      alert('관리자는 장바구니 기능을 사용할 수 없습니다.');
      return;
    }

    try {
      await axios.post(
        'http://localhost:8080/api/carts/items/add',
        {
          productId: product.productId,
          quantity: 1,
        },
        {
          headers: {
            Authorization: `Bearer ${user.token}`,
          },
        },
      );
    } catch (error) {
      console.error('장바구니 추가 중 오류 발생:', error);
      alert('장바구니 담기에 실패했습니다. 잠시후 다시 시도해주세요.');
    }
  };

  return (
    <>
      <Helmet>
        <title>
          {product ? `상품 상세 - ${product.productName}` : '상품 상세 - 로딩 중'}
        </title>
      </Helmet>

      <nav className={'detail-nav-menu'}>
        <button onClick={() => navigate('/')}
                className="btn btn-outline-primary btn-sm">
          <i className="bi bi-arrow-left"></i> 목록으로
        </button>
      </nav>

      <div className={'detail-wrapper'}>
        {!product ? (
          <div className="d-flex justify-content-center align-items-center"
               style={{ height: '300px' }}>
            <Spinner animation="border" variant="primary" role="status">
              <span className="visually-hidden">로딩 중...</span>
            </Spinner>
          </div>
        ) : (
          <section className={'detail-content'}>
            <img
              src={
                product.fileName
                  ? `http://localhost:8080/upload/${product.fileName}`
                  : '/product_temp_128x128.png'
              }
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
        )}
      </div>
    </>
  );
};

export default ProductDetailPage;
