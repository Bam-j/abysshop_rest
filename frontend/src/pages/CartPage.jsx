import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import Spinner from 'react-bootstrap/Spinner';
import useUserStore from '../stores/useUserStore';
import api from '../api/axiosInstance';

import '../styles/pages/CartPage.scss';

const ShoppingCartPage = () => {
  const { user, updateCartQuantity } = useUserStore();
  const userId = user?.userId;
  const cartId = user?.cartId;
  const [cartItems, setCartItems] = useState([]);
  const [totalPoints, setTotalPoints] = useState(0);
  const [totalQuantity, setTotalQuantity] = useState(0);
  const [isPurchasing, setIsPurchasing] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (!token || !user?.userId || !user?.cartId) {
      return;
    }

    const fetchCart = async () => {
      try {
        const res = await api.get(
          `/carts/${user.userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          },
        );

        const data = res.data;
        setCartItems(data.cartItems || []);
        setTotalPoints(data.cart.totalPoints || 0);
        setTotalQuantity(data.cart.totalQuantity || 0);

        updateCartQuantity(user);
      } catch (error) {
        console.error('장바구니 정보 불러오기 실패:', error);
      }
    };

    fetchCart();
  }, [user, updateCartQuantity]);

  const updateQuantity = async (productId, action) => {
    const token = localStorage.getItem('accessToken');
    if (!token || !cartId) {
      return;
    }

    const currentItem = cartItems.find(item => item.productId === productId);
    if (!currentItem) {
      return;
    }

    const newQuantity =
      action === 'increase' ? currentItem.quantity + 1 : Math.max(1,
        currentItem.quantity - 1);

    try {
      await api.patch(
        '/carts/items/quantity',
        [
          {
            cartId,
            productId,
            quantity: newQuantity,
          },
        ],
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      setCartItems(prevItems =>
        prevItems.map(item =>
          item.productId === productId ? { ...item, quantity: newQuantity }
            : item,
        ),
      );

      const res = await api.get(`/carts/${userId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

      const data = res.data;
      setCartItems(data.cartItems || []);
      setTotalPoints(data.cart.totalPoints || 0);
      setTotalQuantity(data.cart.totalQuantity || 0);
      updateCartQuantity(user);
    } catch (error) {
      console.error('수량 업데이트 실패:', error);
    }
  };

  const removeItem = async productId => {
    const token = localStorage.getItem('accessToken');
    if (!token || !cartId) {
      return;
    }

    try {
      const res = await api.delete(
        '/carts/items/delete',
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          data: {
            cartId,
            productId,
          },
        },
      );

      setCartItems(prevItems =>
        prevItems.filter(item => item.productId !== productId),
      );
      updateCartQuantity(user);
    } catch (error) {
      console.error('장바구니 아이템 삭제 실패:', error);
    }
  };

  const handlePurchase = async () => {
    const token = localStorage.getItem('accessToken');
    if (!token || !cartId || !userId || isPurchasing) {
      return;
    }

    setIsPurchasing(true);

    try {
      await api.post('/orders/create',
        {
          cartId, userId,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

      navigate('/orders/complete');
    } catch (error) {
      const message = error?.response?.data?.message || '주문에 실패했습니다.';
      alert(message);
      console.error('주문 실패:', error);
    } finally {
      setIsPurchasing(false);
    }
  };

  return (
    <div className="cart-content">
      <Helmet>
        <title>장바구니</title>
      </Helmet>

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
          {cartItems === null ? (
            <tr>
              <td colSpan="4">
                <div
                  className="d-flex justify-content-center align-items-center"
                  style={{ height: '200px' }}>
                  <Spinner animation="border" variant="primary" role="status">
                    <span className="visually-hidden">로딩 중...</span>
                  </Spinner>
                </div>
              </td>
            </tr>
          ) : cartItems.length === 0 ? (
            <tr>
              <td colSpan="4">
                <div className="text-center" style={{ padding: '40px 0' }}>
                  장바구니가 비어있습니다.
                </div>
              </td>
            </tr>
          ) : (
            cartItems.map(item => (
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
                <td>
                  {(item.price * item.quantity).toLocaleString()} 포인트
                </td>
                <td>
                  <button className="btn btn-danger"
                          onClick={() => removeItem(item.productId)}>X
                  </button>
                </td>
              </tr>
            ))
          )}
          </tbody>
          {cartItems && cartItems.length > 0 && (
            <tfoot>
            <tr>
              <td>
                주문 합계 포인트: <strong>{totalPoints.toLocaleString()} 포인트</strong>
              </td>
              <td>
                총 수량: <strong>{totalQuantity}</strong>
              </td>
              <td colSpan="2">
                <button className="btn btn-success" onClick={handlePurchase}
                        disabled={isPurchasing}>
                  구매하기
                </button>
              </td>
            </tr>
            </tfoot>
          )}
        </table>
      </section>
    </div>
  );
};

export default ShoppingCartPage;
