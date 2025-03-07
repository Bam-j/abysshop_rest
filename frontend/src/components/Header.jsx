import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import '../styles/components/Header.scss';
import logo from '../assets/images/abyssblock_square_64x64.png';

const Header = () => {
  const [user, setUser] = useState(null);
  const [cartQuantity, setCartQuantity] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    //TODO: API로 부터 사용자 데이터(로그인 상태, 장바구니 quantity, points, userId, cartId) 가져오기
  }, []);

  const handleLogout = () => {
    //TODO: 로그아웃 핸들러, 유저 로그인 인증 해제
    navigate('/');
  };

  return (
    <header>
      <div className="square-logo">
        <Link to="/">
          <img id="header-logo" src={logo} alt="어비스 블록 미니멀 로고"/>
        </Link>
      </div>

      <ul className="action-menu">
        {user ? (
          <>
            {user.userType === 'admin' ? (
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
              <button onClick={handleLogout} className="btn btn-primary">
                로그아웃
              </button>
            </li>
          </>
        ) : (
          <li>
            <Link to="/account/sign-in" className="btn btn-primary">
              로그인
            </Link>
          </li>
        )}
      </ul>
    </header>
  );
};

export default Header;
