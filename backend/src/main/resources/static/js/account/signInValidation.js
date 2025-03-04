document.addEventListener("DOMContentLoaded", () => {
  const signInForm = document.getElementById("sign-in-form");

  signInForm.addEventListener("submit", event  => {
    const usernameInput = document.querySelector(".username-input");
    const passwordInput = document.querySelector(".password-input");

    if (!usernameInput.value.trim()) {
      //window.alert 대신 html 경고창으로 이쁘게 경고하기를 고려해볼 것
      window.alert("계정을 입력해주세요.");
      event.preventDefault();
    } else if (!passwordInput.value.trim()) {
      window.alert("비밀번호를 입력해주세요.");
      event.preventDefault();
    }
  });
});