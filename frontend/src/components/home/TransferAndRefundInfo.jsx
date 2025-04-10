import { useState } from 'react';

const TransferRefundInfo = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <div>
      <button type="button" className="btn btn-primary" onClick={openModal}>
        계좌 이체 & 환불 안내 사항
      </button>

      {isModalOpen && (
        <div className="modal fade show d-block" tabIndex="-1">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5">포인트 충전 요청 & 환불 안내</h1>
                <button type="button" className="btn-close" onClick={closeModal}
                        aria-label="Close"></button>
              </div>
              <div className="modal-body">
                <div className="alert alert-warning" role="alert">
                  <h2>계좌 이체 안내</h2>
                  <h3><strong>[계좌 번호를 적을 공간]</strong></h3>
                  <ul>
                    <li>송금하실 때 송금자를 닉네임과 동일하게 설정해주세요.</li>
                    <li>입금 후 포인트 지급까지 <strong>10분에서 24시간</strong>까지 소요될 수 있습니다.
                    </li>
                    <li>결제 과정에서 문의는 디스코드에서 받고 있습니다.</li>
                  </ul>
                  <hr />
                  <h2>환불 안내</h2>
                  <ul>
                    <li>환불 요청은 주문 번호, 송금자, 계좌번호, 금액, 이체 증명(이미지 등)를 첨부해서 디스코드로
                      요청해주세요.
                    </li>
                    <li>환불 요청 후 처리까지 <strong>10분에서 48시간</strong>까지 소요될 수 있습니다.
                    </li>
                  </ul>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary"
                        onClick={closeModal}>
                  닫기
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {isModalOpen && <div className="modal-backdrop fade show"
                           onClick={closeModal}></div>}
    </div>
  );
};

export default TransferRefundInfo;
