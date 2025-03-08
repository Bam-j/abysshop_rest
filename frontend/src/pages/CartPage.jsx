import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import '../styles/pages/CartPage.scss';

const ShoppingCartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalPoints, setTotalPoints] = useState(0);
  const [totalQuantity, setTotalQuantity] = useState(0);

  useEffect(() => {
    //TODO: 백엔드 API로부터 회원의 장바구니 정보 가져오기
  }, []);

  const updateQuantity = () => {
    //TODO: 장바구니 개별 아이템 수량 조절
  };

  const removeItem = () => {
    //TODO: 장바구니에서 아이템 삭제 요청 보내기
  };

  return (
    <div className="cart-content">
      <nav>
        <Link to="/" className="btn btn-outline-primary">
          <i className="bi bi-arrow-left"></i> 메인으로
        </Link>
      </nav>

      <section>
        <table id="cart-table" className="table table-hover">
          <thead>
          <tr className="table-primary">
            <th>상품명</th>
            <th>수량</th>
            <th>포인트</th>
            <th>삭제</th>
          </tr>
          </thead>
          <tbody>
          {cartItems.map(item => (
            <tr key={item.productId}>
              <td>{item.productName}</td>
              <td>
                <div className="quantity-controller">
                  <button className="btn btn-primary"
                          onClick={() => updateQuantity(item.productId,
                            'increase')}>+
                  </button>
                  <p id="quantity"><strong>{item.quantity}</strong></p>
                  <button className="btn btn-primary"
                          onClick={() => updateQuantity(item.productId,
                            'decrease')}>-
                  </button>
                </div>
              </td>
              <td>{item.price.toLocaleString()} 포인트</td>
              <td>
                <button className="btn btn-danger"
                        onClick={() => removeItem(item.productId)}>X
                </button>
              </td>
            </tr>
          ))}
          </tbody>
          <tfoot>
          <tr>
            <td>주문 합계 포인트: {totalPoints.toLocaleString()} 포인트</td>
            <td>총 수량: {totalQuantity}</td>
            <td>
              <button className="btn btn-success">구매하기</button>
            </td>
          </tr>
          </tfoot>
        </table>
      </section>
    </div>
  );
};

export default ShoppingCartPage;
