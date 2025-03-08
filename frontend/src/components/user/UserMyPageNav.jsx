import { NavLink } from 'react-router-dom';

import '../styles/components/Nav.scss';

const UserMyPageNav = () => {
  return (
    <nav className="user-my-page-nav">
      <NavLink to="/user/my-page?menu=order-management&page=1"
               className="nav-link">
        주문 관리
      </NavLink>
      <NavLink to="/user/my-page?menu=point-request&page=1"
               className="nav-link">
        포인트 지급 요청
      </NavLink>
      <NavLink to="/user/my-page?menu=user-info&page=1" className="nav-link">
        계정 관리
      </NavLink>
    </nav>
  );
};

export default UserMyPageNav;
