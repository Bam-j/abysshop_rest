document.addEventListener("DOMContentLoaded", () => {
  const changeNicknameForm = document.getElementById("change-nickname-form");
  const changePasswordForm = document.getElementById("change-password-form");
  const withdrawForm = document.getElementById("withdraw-form");

  changeNicknameForm.addEventListener("submit", event => {
    const nicknameInput = document.querySelector(".nickname-input");

    if (!nicknameInput.value.trim()) {
      window.alert("변경할 닉네임을 입력해주세요.");
      event.preventDefault()
    }
  });

  changePasswordForm.addEventListener("submit", event => {
    const passwordInput = document.querySelector(".password-input");

    if (!passwordInput.value.trim()) {
      window.alert("변경할 비밀번호를 입력해주세요.");
      event.preventDefault()
    }
  });

  withdrawForm.addEventListener("submit", event => {
    const passwordInput = document.querySelector(".password-input");

    if (!passwordInput.value.trim()) {
      window.alert("비밀번호를 입력해주세요.");
      event.preventDefault()
    }
  });
});
