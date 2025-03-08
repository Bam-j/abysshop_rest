import './styles/App.scss';

import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import SignInPage from './pages/SignInPage';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const App = () => {
  return (
    //TODO: 각 페이지 Route 연결은 일단 JSP > React 컨버트 끝나고 하자
    <div className="App">
      <Router>
        <Routes>
          <Route
            path="/*"
            element={
              <>
                <Header />
                <Routes>
                  <Route path="/" element={<HomePage />} />
                </Routes>
                <Footer />
              </>
            }
          />
        </Routes>

        <Routes>
          <Route path="/account/sign-in" element={<SignInPage />} />
        </Routes>
      </Router>
    </div>
  );
};

export default App;
