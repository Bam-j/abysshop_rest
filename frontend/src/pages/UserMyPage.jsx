import { useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import useUserStore from '../stores/userUserStore';
import Spinner from 'react-bootstrap/Spinner';

import '../styles/pages/UserMyPage.scss';
import '../styles/components/user/UserMyPageNav.scss';

import UserMyPageNav from '../components/user/UserMyPageNav';
import UserPointRechargeList from '../components/user/UserPointRechargeList';
import UserOrderList from '../components/user/UserOrderList';
import UserAccountSettings from '../components/user/UserAccountSettings';

const UserMyPage = () => {
  const [searchParams] = useSearchParams();
  const { user, setUser } = useUserStore();
  const navigate = useNavigate();

  const menu = searchParams.get('menu') || 'order-management';

  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (token) {
      fetch('http://localhost:8080/api/users/me', {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
      .then(res => res.json())
      .then(data => setUser(data))
      .catch(() => navigate('/'));
    }
  }, []);

  const renderContent = () => {
    switch (menu) {
      case 'user-account-settings':
        return <UserAccountSettings user={user} />;
      case 'point-recharges':
        return <UserPointRechargeList user={user} />;
      default:
        return <UserOrderList user={user} />;
    }
  };

  return (
    <div className="user-my-page">
      <Helmet>
        <title>
          {user ? `마이페이지 - ${user.nickname}` : `마이페이지 - 로딩중`}
        </title>
      </Helmet>

      {!user ? (
        <div className="d-flex justify-content-center align-items-center"
             style={{ height: '300px' }}>
          <Spinner animation="border" variant="primary" role="status">
            <span className="visually-hidden">로딩 중...</span>
          </Spinner>
        </div>
      ) : (
        <>
          <UserMyPageNav user={user} />
          <div id="content" className="user-my-page-content">
            {renderContent()}
          </div>
        </>
      )}
    </div>
  );
};

export default UserMyPage;
