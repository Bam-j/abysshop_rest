import { Link } from 'react-router-dom';

import '../styles/pages/OrderCompletePage.scss';
import logo from '../assets/images/abyssblock_mark_sd.png';

const OrderCompletePage = () => {
  //TODO: 회원 마이 페이지 주문 내역 이동을 위해 userId를 백엔드로부터 가져오기
  const userId = 0;

  return (
    <div className="order-complete">
      <section>
        <div className="mark-logo">
          <img src={logo} alt="abyssblock 로고" />
        </div>
        <h3>주문이 완료되었습니다.</h3>
        <div id="order-complete-info"
             className="alert alert-dismissible alert-success">
          <ol>
            <li>주문 상태는 '마이 페이지'의 '주문 관리' 또는 '포인트 지급 요청'에서 확인하실 수 있습니다.</li>
            <li>입금 후 포인트/상품 지급까지 <strong>10분에서 최대 24시간까지</strong> 소요될 수 있습니다.
            </li>
            <li>결제 과정의 문의나 환불 및 QnA는 디스코드에서 받고 있습니다.</li>
          </ol>
        </div>
        <div className="button-container">
          <Link to="/" id="move-home-button" className="btn btn-primary">
            <i className="bi bi-house-door"></i> 메인으로
          </Link>
          <Link to={`/user/my-page/${userId}?menu=order-management`}
                id="move-my-page-button" className="btn btn-success">
            <i className="bi bi-card-list"></i> 주문 내역으로
          </Link>
        </div>
      </section>
    </div>
  );
};

export default OrderCompletePage;
