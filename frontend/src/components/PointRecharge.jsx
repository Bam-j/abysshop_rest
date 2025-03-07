import { useState } from 'react';

const PointRecharge = ({ user }) => {
  const [points, setPoints] = useState('');

  const handleInputChange = event => {
    setPoints(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();

    if (!points.trim()) {
      alert('포인트를 입력해주세요.');
      return;
    }

    if (isNaN(points.value)) {
      alert('숫자만 입력이 가능합니다.');
      return;
    }

    if (Number(points.value) <= 0) {
      alert('0 이하의 값은 입력할 수 없습니다.');
      return;
    }

    //TODO: 모든 입력 검증이 통과되면 API 호출해서 요청 보내기
  };

  return (
    <div>
      {user && user.userType === 'user' && (
        <button
          type="button"
          className="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#pointRechargeModal"
        >
          포인트 충전
        </button>
      )}

      <div className="modal fade" id="pointRechargeModal" tabIndex="-1"
           aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5">포인트 충전 요청</h1>
              <button type="button" className="btn-close"
                      data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <div className="alert alert-warning" role="alert">
                <h3><strong>[계좌 번호를 적을 공간]</strong></h3>
                <ul>
                  <li><strong>반드시 아래 안내 사항들을 읽고 송금을 한 후 송금 완료 버튼을
                    클릭해주세요.</strong></li>
                  <li>송금하실 때 송금자를 닉네임과 동일하게 설정해주세요.</li>
                  <li>입금 후 포인트 지급까지 <strong>10분에서 24시간</strong>까지 소요될 수 있습니다.
                  </li>
                  <li>결제 과정에서 문의는 디스코드에서 받고있습니다.</li>
                </ul>
              </div>
            </div>
            <div className="modal-footer">
              <form onSubmit={handleSubmit}>
                <div className="input-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="요청 포인트"
                    value={points}
                    onChange={handleInputChange}
                  />
                  <button type="submit" className="btn btn-primary">포인트 충전
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PointRecharge;
