import { useSearchParams } from 'react-router-dom';

import '../styles/pages/AdminPage.scss';
import '../styles/components/Nav.scss';

import Header from '../components/Header';
import Footer from '../components/Footer';
import AdminPageNav from '../components/admin/AdminPageNav';
import PointRechargeManagement
  from '../components/admin/PointRechargeManagement';
import PointRechargeDetail from '../components/admin/PointRechargeDetail';
import AddProduct from '../components/admin/AddProduct';
import RemoveProduct from '../components/admin/RemoveProduct';
import OrderManagement from '../components/admin/OrderManagement';

const AdminPage = () => {
  const [searchParams] = useSearchParams();
  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'point-recharge-management':
        return <PointRechargeManagement />;
      case 'point-recharge-detail':
        return <PointRechargeDetail />;
      case 'add-product':
        return <AddProduct />;
      case 'remove-product':
        return <RemoveProduct />;
      default:
        return <OrderManagement />;
    }
  };

  return (
    <div className="admin-page">
      <Header />
      <AdminPageNav />
      <main>
        <div id="content" className="admin-page-content">
          {renderContent()}
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default AdminPage;
