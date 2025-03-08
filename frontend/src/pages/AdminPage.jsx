import { useSearchParams } from 'react-router-dom';

import '../styles/components/Nav.scss';

import AdminPageNav from '../components/admin/AdminPageNav';
import AdminPointRechargeManagement
  from '../components/admin/AdminPointRechargeManagement';
import AdminPointRechargeDetailManagement
  from '../components/admin/AdminPointRechargeDetailManagement';
import AdminAddProduct from '../components/admin/AdminAddProduct';
import AdminRemoveProduct from '../components/admin/AdminRemoveProduct';
import AdminOrderManagement from '../components/admin/AdminOrderManagement';


const AdminPage = () => {
  const [searchParams] = useSearchParams();
  const menu = searchParams.get('menu') || 'order-management';

  const renderContent = () => {
    switch (menu) {
      case 'point-recharge-management':
        return <AdminPointRechargeManagement />;
      case 'point-recharge-detail':
        return <AdminPointRechargeDetailManagement />;
      case 'add-product':
        return <AdminAddProduct />;
      case 'remove-product':
        return <AdminRemoveProduct />;
      default:
        return <AdminOrderManagement />;
    }
  };

  return (
    <div className="admin-page">
      <AdminPageNav />
      <div id="content" className="admin-page-content">
        {renderContent()}
      </div>
    </div>
  );
};

export default AdminPage;
