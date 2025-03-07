import './styles/App.scss';

/* 캐러셀 이미지 관련 import */
import abyssblockLogo from './assets/images/abyssblock_logo.png';
import abyssblockMark from './assets/images/abyssblock_mark.png';

import Header from './components/Header';
import Carousel from './components/Carousel';
import Footer from './components/Footer';
import PointRecharge from './components/PointRecharge';
import TransferAndRefundInfo from './components/TransferAndRefundInfo';
import ProductList from './components/ProductList';
import { BrowserRouter as Router } from 'react-router-dom';

const App = () => {
  //TODO: 에러표시 제거용 임시 유저. 백엔드 API로 유저 정보 받아올 것
  const user = null;
  const carouselImages = [abyssblockLogo, abyssblockMark];

  return (
    <div className="App">
      <Router>
        <Header />
        <Carousel images={carouselImages} />
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
      </Router>
    </div>
  );
};

export default App;
