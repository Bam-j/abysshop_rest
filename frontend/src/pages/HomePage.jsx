import { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

import '../styles/pages/Homepage.scss';

import Carousel from '../components/home/Carousel';
import PointRecharge from '../components/home/PointRecharge';
import TransferAndRefundInfo from '../components/home/TransferAndRefundInfo';
import ProductList from '../components/product/ProductList';
import Pagination from '../components/common/Pagination';

/* 캐러셀 이미지 관련 import */
import abyssblockLogo from '../assets/images/abyssblock_logo.png';
import abyssblockMark from '../assets/images/abyssblock_mark.png';

export const HomePage = () => {
  const [products, setProducts] = useState([]);
  const [user, setUser] = useState(null);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);

  const carouselImages = [abyssblockLogo, abyssblockMark];

  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      axios
      .get('http://localhost:8080/api/user/me', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(res => setUser(res.data))
      .catch(error => {
        console.error('Failed to fetch user:', error);
        setUser(null);
      });
    } else {
      setUser(null);
    }
  }, []);

  useEffect(() => {
    axios
    .get('http://localhost:8080/api/home', {
      params: {
        page,
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
  }, [page]);

  return (
    <div className="homepage-wrapper">
      <Helmet>
        <title>홈 | Abysshop</title>
      </Helmet>

      <Carousel images={carouselImages} />

      {user && (
        <nav id="point-action-menu">
          <PointRecharge user={user} />
          <TransferAndRefundInfo />
        </nav>
      )}

      <ProductList products={products} />

      {totalPages > 1 && (
        <Pagination
          currentPage={page}
          totalPages={totalPages}
          onPageChange={setPage}
        />
      )}
    </div>
  );
};

export default HomePage;
