import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './styles/App.scss';

import HomePage from './pages/HomePage';
import SignInPage from './pages/SignInPage';
import SignUpPage from './pages/SignUpPage';
import UserMyPage from './pages/UserMyPage';
import AdminDashboardPage from './pages/AdminDashboardPage';
import CartPage from './pages/CartPage';
import Layout from './components/Layout';
import OrderCompletePage from './pages/OrderCompletePage';
import ProductDetailPage from './pages/ProductDetailPage';

const App = () => {
  return (
    <div className="App">
      <Router>
        <Routes>
          {/* TODO: 마이페이지, 장바구니는 userId를 받아와서 처리하게끔 변경 */}
          <Route element={<Layout />}>
            {/* Header, Footer 컴포넌트가 포함된 페이지들은 여기에 */}
            <Route path="/" element={<HomePage />} />
            <Route path="/users/my-page" element={<UserMyPage />} />
            <Route path="/admin/dashboard" element={<AdminDashboardPage />} />
            <Route path="/users/cart" element={<CartPage />} />
            <Route path="/products/detail" element={<ProductDetailPage />} />
          </Route>

          {/* Header, Footer가 포함되지 않는 단독 페이지는 여기에 */}
          <Route path="/auth/sign-in" element={<SignInPage />} />
          <Route path="/auth/sign-up" element={<SignUpPage />} />
          <Route path="/orders/complete" element={<OrderCompletePage />} />
        </Routes>
      </Router>
    </div>
  );
};

export default App;
