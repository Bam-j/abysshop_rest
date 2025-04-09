import { NavLink, useLocation } from 'react-router-dom';

const UserMyPageNav = ({ user }) => {
  const location = useLocation();

  const isMenuActive = (selectedMenu) => {
    const params = new URLSearchParams(location.search);
    const current = params.get('menu');
    console.log(`[isMenuActive] menu=${current}, selected=${selectedMenu}, active=${current === selectedMenu}`);
    return current === selectedMenu;
  };

  return (
    <nav className="user-my-page-nav">
      <p>{user.nickname}님의 마이페이지</p>
      <NavLink
        to={`/users/my-page/${user.userId}?menu=order-management&page=1`}
        className={() =>
        `nav-link${isMenuActive('order-management') ? ' active' : ''}`}>
        주문 관리
      </NavLink>
      <NavLink
        to={`/users/my-page/${user.userId}?menu=point-recharges&page=1`}
        className={() =>
          `nav-link${isMenuActive('point-recharges') ? ' active' : ''}`}>
        포인트 지급 요청
      </NavLink>
      <NavLink
        to={`/users/my-page/${user.userId}?menu=user-account-settings`}
        className={() =>
          `nav-link${isMenuActive('user-account-settings') ? ' active' : ''}`}>
        계정 관리
      </NavLink>
    </nav>
  );
};

export default UserMyPageNav;
