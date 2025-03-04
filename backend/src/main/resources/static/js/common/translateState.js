const orderStateMapping = {
  shipping: "상품 지급 대기",
  completed: "상품 지급 완료",
  refunded: "환불 처리 완료"
};

const rechargeStateMapping = {
  pending_payment: "송금 확인 대기",
  pending_point_deposit: "포인트 지급 대기",
  completed: "포인트 지급 완료",
  refunded: "환불 처리 완료"
};

const translateState = () => {
  document.querySelectorAll("#dropdown-button").forEach(button => {
    const state = button.textContent.trim();

    if (orderStateMapping[state]) {
      button.textContent = orderStateMapping[state];
    } else if (rechargeStateMapping[state]) {
      button.textContent = rechargeStateMapping[state];
    }
  });

  document.querySelectorAll("[data-state]").forEach(td => {
    const state = td.getAttribute("data-state").trim();
    if (orderStateMapping[state]) {
      td.textContent = orderStateMapping[state];
    } else if (rechargeStateMapping[state]) {
      td.textContent = rechargeStateMapping[state];
    }
  });
};

document.addEventListener("DOMContentLoaded", () => {
  translateState();
});

document.querySelectorAll(".nav-link").forEach(navTab => {
  navTab.addEventListener("click", () => {
    setTimeout(() => {
      translateState();
      //비동기 처리 대기로 인해 1000ms의 시간을 줘야했는데
      //실 사용에서 다소 어색한 동작을 보여줌. 다른 방법을 찾아보자
    }, 1000);
  });
});