import './styles/App.scss';

import Header from './components/Header';
import Carousel from './components/Carousel';
import Footer from './components/Footer';
import PointRecharge from './components/PointRecharge';
import TransferAndRefundInfo from './components/TransferAndRefundInfo';
import ProductList from './components/ProductList';

const App = () => {
  //TODO: 에러표시 제거용 임시 유저. 백엔드 API로 유저 정보 받아올 것
  const user = null;

  return (
    <div className="App">
      <Header />
      <Carousel />
      {user && (
        <nav id="point-action-menu">
          <PointRecharge user={user} />
          <TransferAndRefundInfo />
        </nav>
      )}
      <main>
        <ProductList />
      </main>
      <Footer />
    </div>
  );
};

export default App;
