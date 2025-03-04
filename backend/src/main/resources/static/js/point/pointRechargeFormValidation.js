document.addEventListener("DOMContentLoaded", () => {
  const rechargeRequestForm = document.getElementById("recharge-request-form");

  rechargeRequestForm.addEventListener("submit", event => {
    const pointInput = document.getElementById("point-input");

    if (!pointInput.value.trim()) {
      window.alert("포인트를 입력해주세요.");
      event.preventDefault();
    }

    if (isNaN(pointInput.value)) {
      window.alert("숫자만 입력이 가능합니다.");
      event.preventDefault();
    }

    if (Number(pointInput.value) <= 0) {
      window.alert("0 이하의 값은 입력할 수 없습니다.");
      event.preventDefault();
    }
  });
});