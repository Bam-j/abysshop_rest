import { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useUserStore from '../../stores/useUserStore';
import api from '../../api/axiosInstance';

import '../../styles/components/common/Header.scss';
import logo from '../../assets/images/abyssblock_square_64x64.png';

const Header = () => {
  const user = useUserStore(state => state.user);
  const cartQuantity = useUserStore(state => state.cartQuantity);
  const setUser = useUserStore(state => state.setUser);
  const resetUser = useUserStore(state => state.resetUser);
  const updateCartQuantity = useUserStore(state => state.updateCartQuantity);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    api.get('/users/me',
      {
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
        pointBalance: userInfo.pointBalance,
      });

      updateCartQuantity(userInfo);
    })
    .catch(err => {
      console.error('유저 정보 가져오기 실패:', err);
      resetUser();
    });
  }, [setUser, resetUser, updateCartQuantity]);

  const handleLoginClick = () => {
    navigate('/auth/sign-in');
  };

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    resetUser();
    window.location.href = '/';
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
                <Link to="/admin/dashboard?menu=order-management"
                      className="btn btn-primary">
                  관리자 페이지
                </Link>
              </li>
            ) : (
              <>
                <li>
                  <button type="button" className="btn btn-success disabled">
                    {user.pointBalance.toLocaleString()} 포인트
                  </button>
                </li>
                <li>
                  <Link to={`/users/cart/${user.cartId}`}
                        className="btn btn-primary">
                    <i className="bi bi-cart"></i> 장바구니
                    <span className="badge bg-secondary ms-2 px-2">
                      {typeof cartQuantity === 'number' ? String(cartQuantity) : '0'}
                    </span>
                  </Link>
                </li>
                <li>
                  <Link
                    to={`/users/my-page/${user.userId}?menu=order-management`}
                    className="btn btn-primary">
                    마이페이지
                  </Link>
                </li>
              </>
            )}
            <li>
              <button onClick={handleLogout} className="btn btn-primary">
                로그아웃
              </button>
            </li>
          </>
        ) : (
          <li>
            <button onClick={handleLoginClick} className="btn btn-primary">
              로그인
            </button>
          </li>
        )}
      </ul>
    </header>
  );
};

export default Header;
