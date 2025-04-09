import { useState } from 'react';

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
    console.log(user);

    if (!newNickname.trim()) {
      setNicknameErrorMessage('닉네임을 입력해주세요.');
      return;
    } else if (newNickname.toLowerCase().includes('admin')) {
      setNicknameErrorMessage('닉네임에 "admin"이라는 단어를 포함할 수 없습니다.');
      return;
    }

    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/account/nickname',
        {
          method: 'PATCH',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            userId: user.userId,
            newNickname: newNickname,
          }),
        });

      if (!response.ok) {
        const errorData = await response.json();
        setNicknameErrorMessage(errorData.message || '닉네임 변경에 실패했습니다.');
        return;
      }

      alert('닉네임이 성공적으로 변경되었습니다.');
      setNewNickname('');
      setNicknameErrorMessage('');
    } catch (error) {
      console.error('네트워크 오류 또는 서버 미응답:', error);
      setNicknameErrorMessage('서버와의 통신 중 오류가 발생했습니다.');
    }
  };

  const handlePasswordChange = async e => {
    e.preventDefault();

    if (!newPassword.trim()) {
      setPasswordErrorMessage('새 비밀번호를 입력해주세요.');
      return;
    }

    const token = localStorage.getItem('accessToken');
    if (!token) {
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/account/password',
        {
          method: 'PATCH',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            userId: user.userId,
            newPassword: newPassword,
          }),
        });

      if (!response.ok) {
        const errorData = await response.json();
        setPasswordErrorMessage(errorData.message || '비밀번호 변경에 실패했습니다.');
        return;
      }

      alert('비밀번호가 성공적으로 변경되었습니다.');
      setNewPassword('');
    } catch (error) {
      const message = error.response?.data?.message;

      if (message) {
        setPasswordErrorMessage(message);
      } else {
        setPasswordErrorMessage('비밀번호 변경 요청 중 오류가 발생했습니다.');
      }
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
      const response = await fetch('http://localhost:8080/api/account/withdraw',
        {
          method: 'DELETE',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            userId: user.userId,
            password: currentPassword,
          }),
        });

      if (!response.ok) {
        const errorData = await response.json();
        setWithdrawErrorMessage(errorData.message || '회원 탈퇴에 실패했습니다.');
        return;
      }

      alert('정상적으로 탈퇴 처리되었습니다.');

      localStorage.removeItem('accessToken');
      window.location.href = '/';
    } catch (error) {
      const message = error.response?.data?.message;

      if (message) {
        setWithdrawErrorMessage(message);
      } else {
        setWithdrawErrorMessage('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
      }
    }
  };

  return (
    <section className="user-info">
      <hr />
      <div className="settings-form-group">
        <form onSubmit={handleNicknameChange}>
          <h3>닉네임 변경</h3>
          <div className="alert alert-warning">
            <strong>반드시 인게임 닉네임과 동일한 닉네임으로 설정해주세요.</strong>
            <br />
            인게임 닉네임과 상점 페이지 닉네임이 다른 경우 후원 과정에서 문제가 발생할 가능성이 높습니다.
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

      <div
        className="modal fade"
        id="withdraw-modal"
        tabIndex="-1"
        aria-labelledby="withdraw-modal-label"
        aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="withdraw-modal-label">
                회원 탈퇴 확인
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
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
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                닫기
              </button>
              <button
                type="button"
                className="btn btn-danger"
                onClick={handleWithdraw}
              >
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
