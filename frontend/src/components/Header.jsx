import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

import '../styles/components/Header.scss';
import logo from '../assets/images/abyssblock_square_64x64.png';
import useUserStore from '../stores/userUserStore';

const Header = () => {
  const { user, setUser, cartQuantity, setCartQuantity, resetUser } = useUserStore();
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    axios.get('http://localhost:8080/api/users/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(res => {
      const userInfo = res.data;

      setUser({
        userId: userInfo.userId,
        cartId: userInfo.cartId,
        username: userInfo.username,
        nickname: userInfo.nickname,
        userType: userInfo.userType,
        points: userInfo.pointBalance,
      });

      return axios.get(
        `http://localhost:8080/api/carts/${userInfo.cartId}/quantity`, {
          headers: {
            Authorization: `Bearer ${token}`
          },
        });
    })
    .then(res => {
      setCartQuantity(res.data.quantity);
    })
    .catch(error => {
      console.error('유저 또는 장바구니 정보 가져오기 실패:', error);
      resetUser();
    });
  }, []);

  const handleLoginClick = () => {
    navigate('/auth/sign-in');
  };

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    resetUser();
    navigate('/');
  };

  return (
    <header>
      <div className="square-logo">
        <Link to="/">
          <img id="header-logo" src={logo} alt="어비스 블록 미니멀 로고" />
        </Link>
      </div>

      <ul className="action-menu">
        {user ? (
          <>
            {user.userType === 'ADMIN' ? (
              <li>
                <Link to="/admin/my-page?menu=order-management"
                      className="btn btn-primary">
                  관리자 페이지
                </Link>
              </li>
            ) : (
              <>
                <li>
                  <button type="button" className="btn btn-success disabled">
                    {user.points} 포인트
                  </button>
                </li>
                <li>
                  <Link to={`/user/cart/${user.cartId}`}
                        className="btn btn-primary">
                    <i className="bi bi-cart"></i> 장바구니{' '}
                    <span className="badge bg-secondary">{cartQuantity}</span>
                  </Link>
                </li>
                <li>
                  <Link
                    to={`/user/my-page/${user.userId}?menu=order-management`}
                    className="btn btn-primary">
                    마이페이지
                  </Link>
                </li>
              </>
            )}
            <li>
              <button onClick={handleLogout} className="btn btn-primary">로그아웃
              </button>
            </li>
          </>
        ) : (
          <li>
            <button onClick={handleLoginClick} className="btn btn-primary">로그인
            </button>
          </li>
        )}
      </ul>
    </header>
  );
};

export default Header;
