import { NavLink } from 'react-router-dom';

import '../../styles/components/Nav.scss';

const AdminDashboardPageNav = () => {
  return (
    <nav className="admin-page-nav">
      <NavLink to="/admin/my-page?menu=order-management&page=1"
               className="nav-link">
        주문 관리
      </NavLink>
      <NavLink to="/admin/my-page?menu=point-recharge-management&page=1"
               className="nav-link">
        포인트 지급 요청
      </NavLink>
      <NavLink to="/admin/my-page?menu=point-recharge-detail&page=1"
               className="nav-link">
        포인트 지급 요청 정보 관리
      </NavLink>
      <NavLink to="/admin/my-page?menu=create-product" className="nav-link">
        상품 추가
      </NavLink>
      <NavLink to="/admin/my-page?menu=remove-product&page=1"
               className="nav-link">
        상품 삭제
      </NavLink>
    </nav>
  );
};

export default AdminDashboardPageNav;
