import { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet-async';
import useUserStore from '../stores/useUserStore';
import Carousel from '../components/home/Carousel';
import PointRecharge from '../components/home/PointRecharge';
import TransferAndRefundInfo from '../components/home/TransferAndRefundInfo';
import ProductList from '../components/product/ProductList';
import Pagination from '../components/common/Pagination';
import api from '../api/axiosInstance';

import '../styles/pages/Homepage.scss';

/* 캐러셀 이미지 관련 import */
import abyssblockLogo from '../assets/images/abyssblock_logo.png';
import abyssblockMark from '../assets/images/abyssblock_mark.png';
import { useSearchParams } from 'react-router-dom';

export const HomePage = () => {
  const [products, setProducts] = useState([]);
  const [searchParams, setSearchParams] = useSearchParams();
  const [totalPages, setTotalPages] = useState(1);
  const currentPage = parseInt(searchParams.get('page')) || 1;
  const { user, setUser, resetUser } = useUserStore();

  const carouselImages = [abyssblockLogo, abyssblockMark];

  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      api.get('/users/me', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(res => setUser(res.data))
      .catch(error => {
        console.error('Failed to fetch user:', error);
        resetUser();
      });
    } else {
      resetUser();
    }
  }, [setUser, resetUser]);

  useEffect(() => {
    api.get('/home',
      {
        params: {
          page: currentPage - 1,
          size: 12,
          sort: 'productId,desc',
        },
      })
    .then(response => {
      const data = response.data.productList;
      setProducts(data.content);
      setTotalPages(data.totalPages);
    })
    .catch(error => {
      console.error('Failed to fetch products:', error);
    });
  }, [currentPage]);

  const handlePageChange = newPage => {
    const newParams = new URLSearchParams(searchParams);
    newParams.set('page', newPage);
    setSearchParams(newParams);
  };

  return (
    <div className="homepage-wrapper">
      <Helmet>
        <title>홈 | Abysshop</title>
      </Helmet>

      <Carousel images={carouselImages} />

      <div className="product-menu">
        {user && (
          <nav className="point-action-menu">
            <PointRecharge user={user} />
            <TransferAndRefundInfo />
          </nav>
        )}

        <ProductList products={products} />
      </div>

      {totalPages > 1 && (
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={handlePageChange}
        />
      )}
    </div>
  );
};

export default HomePage;
