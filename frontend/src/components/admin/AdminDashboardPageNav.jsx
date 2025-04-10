import { NavLink, useLocation } from 'react-router-dom';

const AdminDashboardPageNav = () => {
  const location = useLocation();

  const isMenuActive = selectedMenu => {
    const params = new URLSearchParams(location.search);
    return params.get('menu') === selectedMenu;
  };

  return (
    <nav className="dashboard-nav">
      <p>관리자 페이지</p>
      <NavLink
        to="/admin/dashboard?menu=order-management&page=1"
        className={() =>
          `nav-link${isMenuActive('order-management') ? ' active' : ''}`}>
        주문 관리
      </NavLink>
      <NavLink
        to="/admin/dashboard?menu=point-recharge-management&page=1"
        className={() =>
          `nav-link${isMenuActive('point-recharge-management') ? ' active'
            : ''}`}>
        포인트 지급 요청
      </NavLink>
      <NavLink
        to="/admin/dashboard?menu=point-recharge-detail&page=1"
        className={() =>
          `nav-link${isMenuActive('point-recharge-detail') ? ' active' : ''}`}>
        포인트 지급 요청 정보 관리
      </NavLink>
      <NavLink
        to="/admin/dashboard?menu=create-product"
        className={() =>
          `nav-link${isMenuActive('create-product') ? ' active' : ''}`}>
        상품 추가
      </NavLink>
      <NavLink
        to="/admin/dashboard?menu=remove-product&page=1"
        className={() =>
          `nav-link${isMenuActive('remove-product') ? ' active' : ''}`}>
        상품 삭제
      </NavLink>
    </nav>
  );
};

export default AdminDashboardPageNav;
