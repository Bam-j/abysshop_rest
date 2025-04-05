import { useSearchParams } from 'react-router-dom';

import '../styles/components/common/Nav.scss';

import UserMyPageNav from '../components/user/UserMyPageNav';
import UserPointRechargeList from '../components/user/UserPointRechargeList';
import UserOrderList from '../components/user/UserOrderList';
import UserAccountSettings from '../components/user/UserAccountSettings';

const UserMyPage = () => {
  const [searchParams] = useSearchParams();
  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'user-account-settings':
        return <UserAccountSettings />;
      case 'point-request':
        return <UserPointRechargeList />;
      default:
        return <UserOrderList />;
    }
  };

  return (
    <div className="user-my-page">
      <UserMyPageNav />
      <div id="content" className="user-my-page-content">
        {renderContent()}
      </div>
    </div>
  );
};

export default UserMyPage;
