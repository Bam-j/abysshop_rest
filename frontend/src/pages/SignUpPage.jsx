import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/pages/SignUpPage.scss';
import logo from '../assets/images/abyssblock_mark_sd.png';
import { Tooltip } from 'bootstrap';

const SignUpPage = () => {
  const [username, setUsername] = useState('');
  const [nickname, setNickname] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const tooltipTriggerList = document.querySelectorAll(
      '[data-bs-toggle="tooltip"]');
    tooltipTriggerList.forEach(el => new Tooltip(el));
  }, []);

  const handleSubmit = async e => {
    e.preventDefault();

    /*
    * TODO: 회원가입 처리 요청 보내기 관련 로직
    *  empty input req, exists username, nickname => failure
    *  signUp api 요청 보내기 & 실패시 setFailureMessage 설정
    */
  };

  return (
    <main>
      <aside>
        <Link className="btn btn-outline-primary btn-sm" to="/account/sign-in">
          <i className="bi bi-arrow-left"></i> 로그인
        </Link>
      </aside>

      <section>
        <div id="logo">
          <img src={logo} alt="어비스블록 로고" />
        </div>

        {errorMessage && <p className="error-message">{errorMessage}</p>}

        <form id="sign-up-form" onSubmit={handleSubmit}>
          <input
            className="username-input"
            type="text"
            placeholder="계정"
            value={username}
            onChange={e => setUsername(e.target.value)}
          />
          <input
            className="nickname-input"
            type="text"
            placeholder="마인크래프트 닉네임"
            value={nickname}
            data-bs-toggle="tooltip"
            data-bs-placement="right"
            title="인게임 닉네임과 동일하게 가입해주세요. 거래 과정에서 문제가 발생할 수 있습니다."
            onChange={e => setNickname(e.target.value)}
          />
          <input
            className="password-input"
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
          <button type="submit" id="sign-up-button" className="btn btn-primary">
            회원가입
          </button>
        </form>
      </section>
    </main>
  );
};

export default SignUpPage;
