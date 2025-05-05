import { useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import useUserStore from '../stores/useUserStore';

import '../styles/pages/AdminDashboardPage.scss';
import '../styles/components/admin/AdminDashboardNav.scss';

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
  const { user } = useUserStore();
  const navigate = useNavigate();

  const menu = searchParams.get('menu') || 'order-management';

  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if (!token || !user || user.userType !== 'ADMIN') {
      alert('관리자만 접근 가능한 페이지입니다.');
      navigate('/');
    }
  }, [user, navigate]);

  const renderContent = () => {
    switch (menu) {
      case 'point-recharge-management':
        return <AdminPointRechargeManagement />;
      case 'point-recharge-detail':
        return <AdminPointRechargeDetailManagement />;
      case 'create-product':
        return <AdminCreateProduct />;
      case 'remove-product':
        return <AdminRemoveProduct />;
      default:
        return <AdminOrderManagement />;
    }
  };

  return (
    <div className="admin-page">
      <Helmet>
        <title>관리자 페이지</title>
      </Helmet>

      <AdminDashboardPageNav />
      <div id="content" className="admin-dashboard-content">
        {renderContent()}
      </div>
    </div>
  );
};

export default AdminDashboardPage;
