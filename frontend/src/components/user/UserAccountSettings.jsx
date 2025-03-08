import { useState, useEffect } from 'react';

const UserAccountSettings = ({ user }) => {
  const [newNickname, setNewNickname] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    // TODO: 존재하는 닉네임, 동일 PW 변경 요청, 공백 요청 등에 대한 에러 메시지 Hook
    if (errorMessage) {
      alert(errorMessage);
    }
  }, [errorMessage]);

  //TODO: 각각 닉네임 변경, 패스워드 변경, 회원 탈퇴 요청. 백엔드 API 설계 후 채워넣기
  const handleNicknameChange = async e => {
    e.preventDefault();
  };

  const handlePasswordChange = async e => {
    e.preventDefault();
  };

  const handleWithdraw = async e => {
    e.preventDefault();
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
