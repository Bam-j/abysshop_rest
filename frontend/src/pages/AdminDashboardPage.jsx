import { useSearchParams } from 'react-router-dom';

import '../styles/components/common/Nav.scss';

import AdminDashboardPageNav from '../components/admin/AdminDashboardPageNav';
import AdminPointRechargeManagement
  from '../components/admin/AdminPointRechargeManagement';
import AdminPointRechargeDetailManagement
  from '../components/admin/AdminPointRechargeDetailManagement';
import AdminCreateProduct from '../components/admin/AdminCreateProduct';
import AdminRemoveProduct from '../components/admin/AdminRemoveProduct';
import AdminOrderManagement from '../components/admin/AdminOrderManagement';


const AdminDashboardPage = () => {
  const [searchParams] = useSearchParams();
  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'point-recharge-management':
        return <AdminPointRechargeManagement />;
      case 'point-recharge-detail':
        return <AdminPointRechargeDetailManagement />;
      case 'add-product':
        return <AdminCreateProduct />;
      case 'remove-product':
        return <AdminRemoveProduct />;
      default:
        return <AdminOrderManagement />;
    }
  };

  return (
    <div className="admin-page">
      <AdminDashboardPageNav />
      <div id="content" className="admin-page-content">
        {renderContent()}
      </div>
    </div>
  );
};

export default AdminDashboardPage;
