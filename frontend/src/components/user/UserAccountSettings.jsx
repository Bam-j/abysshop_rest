import { useState } from 'react';
import axios from 'axios';

import '../../styles/components/user/UserAccountSettings.scss';

const UserAccountSettings = ({ user }) => {
  const [newNickname, setNewNickname] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [nicknameErrorMessage, setNicknameErrorMessage] = useState('');
  const [passwordErrorMessage, setPasswordErrorMessage] = useState('');
  const [withdrawErrorMessage, setWithdrawErrorMessage] = useState('');

  const handleNicknameChange = async e => {
    e.preventDefault();

    if (!newNickname.trim()) {
      setNicknameErrorMessage('닉네임을 입력해주세요.');
      return;
    } else if (newNickname.toLowerCase().includes('admin')) {
      setNicknameErrorMessage('닉네임에 "admin"이라는 단어를 포함할 수 없습니다.');
      return;
    } else if (newNickname.length < 2) {
      setNicknameErrorMessage('닉네임은 3글자 이상 입력해주세요.');
      return;
    }

    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    try {
      await axios.patch('http://localhost:8080/api/account/nickname', {
        userId: user.userId,
        newNickname,
      }, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      alert('닉네임이 성공적으로 변경되었습니다.');
      setNewNickname('');
      setNicknameErrorMessage('');
    } catch (error) {
      const message = error.response?.data?.message || '닉네임 변경에 실패했습니다.';
      setNicknameErrorMessage(message);
    }
  };

  const handlePasswordChange = async e => {
    e.preventDefault();

    if (!newPassword.trim()) {
      setPasswordErrorMessage('변경할 비밀번호를 입력해주세요.');
      return;
    } else if (newPassword.length < 8) {
      setPasswordErrorMessage('비밀번호는 8자 이상이어야 합니다.');
      return;
    } else if (!/[A-Za-z]/.test(newPassword) || !/[0-9]/.test(newPassword)) {
      setPasswordErrorMessage('비밀번호는 영문자와 숫자를 모두 포함해야 합니다.');
      return;
    }

    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    try {
      await axios.patch('http://localhost:8080/api/account/password', {
        userId: user.userId,
        newPassword,
      }, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      alert('비밀번호가 성공적으로 변경되었습니다.');
      setNewPassword('');
      setPasswordErrorMessage('');
    } catch (error) {
      const message = error.response?.data?.message || '비밀번호 변경 요청 중 오류가 발생했습니다.';
      setPasswordErrorMessage(message);
    }
  };

  const handleWithdraw = async (e) => {
    e.preventDefault();

    if (!currentPassword.trim()) {
      setWithdrawErrorMessage('비밀번호를 입력해주세요.');
      return;
    }

    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    try {
      await axios.delete('http://localhost:8080/api/account/withdraw', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        data: {
          userId: user.userId,
          password: currentPassword,
        },
      });

      alert('정상적으로 탈퇴 처리되었습니다.');
      localStorage.removeItem('accessToken');
      window.location.href = '/';
    } catch (error) {
      const message = error.response?.data?.message || '서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.';
      setWithdrawErrorMessage(message);
    }
  };

  return (
    <section className="user-info">
      <hr />
      <div className="settings-form-group">
        <form onSubmit={handleNicknameChange}>
          <h3>닉네임 변경</h3>
          <div className="alert alert-warning">
            <strong>반드시 인게임 닉네임과 동일한 닉네임으로 설정해주세요.</strong><br />
            인게임 닉네임과 다르게 설정하는 경우 상품 구매 및 전달 과정에서 문제가 발생할 수 있습니다.
          </div>
          <div className="mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="변경할 닉네임 입력"
              value={newNickname}
              onChange={e => setNewNickname(e.target.value)}
            />
          </div>
          {nicknameErrorMessage && <p
            className="error-message">{nicknameErrorMessage}</p>}
          <button type="submit"
                  className="btn btn-primary settings-button-group">
            닉네임 변경
          </button>
        </form>
      </div>
      <hr />
      <div className="settings-form-group">
        <form onSubmit={handlePasswordChange}>
          <h3>비밀번호 변경</h3>
          <div className="mb-3">
            <input
              type="password"
              className="form-control"
              placeholder="변경할 비밀번호 입력"
              value={newPassword}
              onChange={e => setNewPassword(e.target.value)}
            />
          </div>
          {passwordErrorMessage && <p
            className="error-message">{passwordErrorMessage}</p>}
          <button type="submit"
                  className="btn btn-primary settings-button-group">
            비밀번호 변경
          </button>
        </form>
      </div>
      <hr />
      <h3>회원 탈퇴</h3>
      <button
        type="button"
        className="btn btn-danger settings-button-group"
        data-bs-toggle="modal"
        data-bs-target="#withdraw-modal"
      >
        회원 탈퇴
      </button>

      <div className="modal fade" id="withdraw-modal" tabIndex="-1"
           aria-labelledby="withdraw-modal-label" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="withdraw-modal-label">
                회원 탈퇴 확인
              </h5>
              <button type="button" className="btn-close"
                      data-bs-dismiss="modal" aria-label="Close">
              </button>
            </div>
            <div className="modal-body">
              <div className="alert alert-warning">
                탈퇴 하시려면 현재 비밀번호를 입력해주세요.
              </div>
              <input
                type="password"
                className="form-control"
                placeholder="현재 비밀번호"
                value={currentPassword}
                onChange={(e) => setCurrentPassword(e.target.value)}
              />
              {withdrawErrorMessage && <p
                className="error-message">{withdrawErrorMessage}</p>}
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary"
                      data-bs-dismiss="modal">
                닫기
              </button>
              <button type="button" className="btn btn-danger"
                      onClick={handleWithdraw}>
                회원 탈퇴
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default UserAccountSettings;
