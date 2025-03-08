import { useSearchParams } from 'react-router-dom';

import '../styles/pages/UserMyPage.scss';
import '../styles/components/Nav.scss';

import Header from '../components/Header';
import Footer from '../components/Footer';
import UserMyPageNav from '../components/user/UserMyPageNav';
import UserInfo from '../components/user/UserInfo';
import UserPointRechargeList from '../components/user/UserPointRechargeList';
import UserOrderList from '../components/user/UserOrderList';

const UserMyPage = () => {
  const [searchParams] = useSearchParams();
  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'user-info':
        return <UserInfo />;
      case 'point-request':
        return <UserPointRechargeList />;
      default:
        return <UserOrderList />;
    }
  };

  return (
    <div className="user-my-page">
      <Header />
      <UserMyPageNav />
      <main>
        <div id="content" className="user-my-page-content">
          {renderContent()}
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default UserMyPage;
