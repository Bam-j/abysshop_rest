import { useState, useEffect } from 'react';

const UserAccountSettings = ({ user }) => {
  const [newNickname, setNewNickname] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleNicknameChange = async e => {
    e.preventDefault();

    if (!newNickname.trim()) {
      setErrorMessage('닉네임을 입력해주세요.');
      return;
    } else if (newNickname.toLowerCase().includes('admin')) {
      setErrorMessage('닉네임에 "admin"이라는 단어를 포함할 수 없습니다.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/account/nickname',
        {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            userId: user.id,
            newNickname: newNickname,
          }),
        });

      if (!response.ok) {
        const errorData = await response.json();
        setErrorMessage(errorData.message || '닉네임 변경에 실패했습니다.');
        return;
      }

      alert('닉네임이 성공적으로 변경되었습니다.');
      setNewNickname('');
    } catch (error) {
      const message = error.response?.data?.message;

      if (message) {
        setErrorMessage(message);
      } else {
        setErrorMessage('닉네임 변경 요청 중 오류가 발생했습니다.');
      }
    }
  };

  const handlePasswordChange = async e => {
    e.preventDefault();

    if (!newPassword.trim()) {
      setErrorMessage('새 비밀번호를 입력해주세요.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/account/password',
        {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            userId: user.id,
            newPassword: newPassword,
          }),
        });

      if (!response.ok) {
        const errorData = await response.json();
        setErrorMessage(errorData.message || '비밀번호 변경에 실패했습니다.');
        return;
      }

      alert('비밀번호가 성공적으로 변경되었습니다.');
      setNewPassword('');
    } catch (error) {
      const message = error.response?.data?.message;

      if (message) {
        setErrorMessage(message);
      } else {
        setErrorMessage('비밀번호 변경 요청 중 오류가 발생했습니다.');
      }
    }
  };

  const handleWithdraw = async (e) => {
    e.preventDefault();

    if (!currentPassword.trim()) {
      setErrorMessage('비밀번호를 입력해주세요.');
      return;
    }

    const token = localStorage.getItem('accessToken'); // 혹은 다른 저장소에서 가져오기

    if (!token) {
      setErrorMessage('인증 정보가 없습니다. 다시 로그인 해주세요.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/account/withdraw', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          userId: user.id,
          password: currentPassword,
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        setErrorMessage(errorData.message || '회원 탈퇴에 실패했습니다.');
        return;
      }

      alert('정상적으로 탈퇴 처리되었습니다.');

      localStorage.removeItem('accessToken');
      window.location.href = '/';
    } catch (error) {
      const message = error.response?.data?.message;

      if (message) {
        setErrorMessage(message);
      } else {
        setErrorMessage('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
      }
    }
  };

  return (
    <section className="user-info">
      {errorMessage && (
        <div className="alert alert-danger">{errorMessage}</div>
      )}

      <form onSubmit={handleNicknameChange}>
        <div className="alert alert-danger">
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
        <button type="submit" className="btn btn-primary">
          닉네임 변경
        </button>
      </form>

      <hr />

      <form onSubmit={handlePasswordChange}>
        <div className="mb-3">
          <input
            type="password"
            className="form-control"
            placeholder="변경할 비밀번호 입력"
            value={newPassword}
            onChange={e => setNewPassword(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          비밀번호 변경
        </button>
      </form>

      <hr />

      <button
        type="button"
        className="btn btn-danger"
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
        aria-hidden="true"
      >
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
