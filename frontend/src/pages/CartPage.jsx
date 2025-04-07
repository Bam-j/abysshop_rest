import { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

import '../styles/pages/CartPage.scss';

const ShoppingCartPage = () => {
  const [userId, setUserId] = useState(null);
  const [cartId, setCartId] = useState(null);
  const [cartItems, setCartItems] = useState([]);
  const [totalPoints, setTotalPoints] = useState(0);
  const [totalQuantity, setTotalQuantity] = useState(0);
  const [quantityBuffer, setQuantityBuffer] = useState({});
  const [isPurchasing, setIsPurchasing] = useState(false);
  const debounceTimer = useRef({});
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    const fetchCart = async () => {
      try {
        const userRes = await axios.get('http://localhost:8080/api/users/me', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserId(userRes.data.userId);

        const cartRes = await axios.get(
          `http://localhost:8080/api/carts/${userId}`);
        const cartData = cartRes.data;

        setCartId(cartData.cart.cartId || 0);
        setCartItems(cartData.cartItems || []);
        setTotalPoints(cartData.cart.totalPoints || 0);
        setTotalQuantity(cartData.cart.totalQuantity || 0);
      } catch (error) {
        console.error('장바구니 정보 불러오기 실패:', error);
      }
    };

    fetchCart();
  }, []);

  const updateQuantity = (productId, action) => {
    setCartItems(prevItems =>
      prevItems.map(item => {
        if (item.productId === productId) {
          const newQuantity =
            action === 'increase' ? item.quantity + 1 : Math.max(1,
              item.quantity - 1);

          setQuantityBuffer(prev => ({
            ...prev,
            [productId]: newQuantity,
          }));

          return { ...item, quantity: newQuantity };
        }
        return item;
      }),
    );

    if (debounceTimer.current) {
      clearTimeout(debounceTimer.current);
    }

    debounceTimer.current = setTimeout(() => {
      sendAllQuantityUpdates();
    }, 3000);
  };

  const sendAllQuantityUpdates = async () => {
    const token = localStorage.getItem('accessToken');
    if (!token || !cartId) {
      return;
    }

    const requestList = Object.entries(quantityBuffer).map(
      ([productId, quantity]) => ({
        cartId,
        productId: Number(productId),
        quantity,
      }));

    if (requestList.length === 0) {
      return;
    }

    try {
      await axios.patch('http://localhost:8080/api/carts/quantity', requestList,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

      const res = await axios.get(`http://localhost:8080/api/carts/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const data = res.data;
      setCartItems(data.cartItems || []);
      setTotalPoints(data.cart.totalPoints || 0);
      setTotalQuantity(data.cart.totalQuantity || 0);
      setQuantityBuffer({});

    } catch (error) {
      console.error('수량 업데이트 또는 재조회 실패:', error);
    }
  };

  const removeItem = async productId => {
    const token = localStorage.getItem('accessToken');
    if (!token || !cartId) {
      return;
    }

    try {
      await axios.delete('http://localhost:8080/api/carts/delete', {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        data: {
          cartId,
          productId,
        },
      });

      setCartItems(prevItems =>
        prevItems.filter(item => item.productId !== productId),
      );
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
      await axios.post(
        'http://localhost:8080/api/orders/create',
        {
          cartId,
          userId,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        },
      );

      navigate('/order/complete');
    } catch (error) {
      console.error('주문 실패:', error);
      alert('주문에 실패했습니다. 잠시후 다시 시도해주세요.');
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
              <button
                className="btn btn-success"
                onClick={handlePurchase}
                disabled={isPurchasing}
              >
                구매하기
              </button>
            </td>
          </tr>
          </tfoot>
        </table>
      </section>
    </div>
  );
};

export default ShoppingCartPage;
