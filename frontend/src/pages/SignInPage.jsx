import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/pages/SignInPage.scss';
import logo from '../assets/images/abyssblock_mark_sd.png';

const SignInPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [failureMessage, setFailureMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async e => {
    e.preventDefault();
    /*
    * TODO: 로그인 처리 요청 보내기 관련 로직
    *  empty input req, not exist username, wrong password => failure
    *  signIn api 요청 보내기 & 실패시 setFailureMessage 설정
    */
  };

  return (
    <main>
      <aside>
        <Link className="btn btn-outline-primary btn-sm" to="/">
          <i className="bi bi-arrow-left"></i> 메인으로
        </Link>
      </aside>

      <section>
        <div className="mark-logo">
          <img src={logo} alt="어비스블록 로고" />
        </div>

        {failureMessage && <p className="error-message">{failureMessage}</p>}

        <form id="sign-in-form" onSubmit={handleSubmit}>
          <input
            className="username-input"
            type="text"
            placeholder="계정"
            value={username}
            onChange={e => setUsername(e.target.value)}
          />
          <input
            className="password-input"
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
          <button type="submit" id="sign-in-button" className="btn btn-primary">
            로그인
          </button>
        </form>

        <Link to="/account/sign-up" className="btn btn-secondary">
          회원가입
        </Link>
      </section>
    </main>
  );
};

export default SignInPage;
