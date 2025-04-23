import { useState } from 'react';
import { Helmet } from 'react-helmet-async';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/axiosInstance';

import '../styles/pages/SignInPage.scss';
import logo from '../assets/images/abyssblock_mark_sd.png';

const SignInPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [failureMessage, setFailureMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async e => {
    e.preventDefault();

    if (!username) {
      setFailureMessage('아이디를 입력해주세요.');
      return;
    } else if (!password) {
      setFailureMessage('비밀번호를 입력해주세요.');
      return;
    }

    try {
      const response = await api.post('/auth/sign-in',
        {
          username,
          password,
        });

      const token = response.data.token;

      if (token) {
        localStorage.setItem('accessToken', token);
        navigate('/');
      } else {
        setFailureMessage('로그인에 실패했습니다. 다시 시도해주세요.');
      }
    } catch (error) {
      const message = error.response?.data?.message || '서버 오류';
      setFailureMessage(message);
    }
  };

  return (
    <div className="sign-in-page-wrapper">
      <Helmet>
        <title>로그인</title>
      </Helmet>

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
            <button type="submit" id="sign-in-button"
                    className="btn btn-primary">
              로그인
            </button>

            <Link to="/auth/sign-up"
                  className="btn btn-secondary move-sign-up-page">
              회원가입
            </Link>
          </form>
        </section>
      </main>
    </div>
  );
};

export default SignInPage;
