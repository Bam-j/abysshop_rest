import { useSearchParams } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import useUserStore from '../stores/userUserStore';

import '../styles/components/common/Nav.scss';
import '../styles/pages/UserMyPage.scss';

import UserMyPageNav from '../components/user/UserMyPageNav';
import UserPointRechargeList from '../components/user/UserPointRechargeList';
import UserOrderList from '../components/user/UserOrderList';
import UserAccountSettings from '../components/user/UserAccountSettings';

const UserMyPage = () => {
  const [searchParams] = useSearchParams();
  const { user } = useUserStore();

  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'user-account-settings':
        return <UserAccountSettings />;
      case 'point-recharges':
        return <UserPointRechargeList />;
      default:
        return <UserOrderList />;
    }
  };

  return (
    <div className="user-my-page">
      <Helmet>
        <title>마이페이지 - {user.nickname}</title>
      </Helmet>

      <UserMyPageNav user={user} />
      <div id="content" className="user-my-page-content">
        {renderContent()}
      </div>
    </div>
  );
};

export default UserMyPage;
