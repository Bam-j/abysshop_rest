import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Tooltip } from 'bootstrap';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

import '../styles/pages/SignUpPage.scss';
import logo from '../assets/images/abyssblock_mark_sd.png';

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

    if (!username) {
      setErrorMessage('계정을 입력해주세요.');
      return;
    } else if (username.toLowerCase().includes('admin')) {
      setErrorMessage('계정에 "admin"이라는 단어를 포함할 수 없습니다.');
      return;
    } else if (username.length < 3) {
      setErrorMessage('계정은 4글자 이상 입력해주세요.');
      return;
    } else if (!/^[a-zA-Z0-9]+$/.test(username)) {
      setErrorMessage('계정은 영문자와 숫자만 사용할 수 있습니다.');
      return;
    }

    if (!nickname) {
      setErrorMessage('닉네임을 입력해주세요.');
      return;
    } else if (nickname.toLowerCase().includes('admin')) {
      setErrorMessage('닉네임에 "admin"이라는 단어를 포함할 수 없습니다.');
      return;
    } else if (nickname.length < 2) {
      setErrorMessage('닉네임은 3글자 이상 입력해주세요.');
      return;
    }

    if (!password) {
      setErrorMessage('비밀번호를 입력해주세요.');
      return;
    } else if (password.length < 8) {
      setErrorMessage('비밀번호는 8자 이상이어야 합니다.');
      return;
    } else if (!/[A-Za-z]/.test(password) || !/[0-9]/.test(password)) {
      setErrorMessage('비밀번호는 영문자와 숫자를 모두 포함해야 합니다.');
      return;
    }

    try {
      await axios.post('http://localhost:8080/api/auth/sign-up', {
        username,
        nickname,
        password,
      });

      navigate('/auth/sign-in');
    } catch (error) {
      const message = error.response?.data?.message;

      if (error.response?.status === 409 && message) {
        setErrorMessage(message);
      } else {
        setErrorMessage('회원가입 중 오류가 발생했습니다.');
      }
    }
  };

  return (
    <div className="sign-up-page-wrapper">
      <Helmet>회원가입</Helmet>

      <main>
        <aside>
          <Link className="btn btn-outline-primary btn-sm" to="/auth/sign-in">
            <i className="bi bi-arrow-left"></i> 로그인
          </Link>
        </aside>

        <section>
          <div className="mark-logo">
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
              data-bs-html="true"
              title="인게임 닉네임과 동일하게 가입해주세요.<br> 거래 과정에서 문제가 발생할 수 있습니다."
              onChange={e => setNickname(e.target.value)}
            />
            <input
              className="password-input"
              type="password"
              placeholder="비밀번호"
              value={password}
              data-bs-toggle="tooltip"
              data-bs-placement="right"
              data-bs-html="true"
              title="8글자 이상의<br> 영문 대소문자, 숫자,<br> 특수문자만 사용가능합니다."
              onChange={e => setPassword(e.target.value)}
            />
            <button type="submit" id="sign-up-button"
                    className="btn btn-primary">
              회원가입
            </button>
          </form>
        </section>
      </main>
    </div>
  );
};

export default SignUpPage;
