document.addEventListener("DOMContentLoaded", () => {
  const signUpForm = document.getElementById("sign-up-form");

  signUpForm.addEventListener("submit", event => {
    const usernameInput = document.querySelector(".username-input");
    const nicknameInput = document.querySelector(".nickname-input");
    const passwordInput = document.querySelector(".password-input");

    //각 input이 비어있는지 체크
    if (!usernameInput.value.trim()) {
      //window.alert 대신 html 경고창으로 이쁘게 경고하기를 고려해볼 것
      window.alert("계정을 입력해주세요.");
      event.preventDefault();
    } else if (!nicknameInput.value.trim()) {
      window.alert(
          `닉네임을 입력해주세요.\n
          반드시 인게임 닉네임과 동일한 닉네임을 넣어주세요.\n
          일치하지 않는 경우 후원 및 아이템 지급 과정에서 문제가 발생할 수 있습니다.`
      );
      event.preventDefault();
    } else if (!passwordInput.value.trim()) {
      window.alert("비밀번호를 입력해주세요.");
      event.preventDefault();
    }

    //각 input 입력에 공백이나 개행, 탭이 포함되어있는지 체크
    const containSpaceInUsername = /\s/.test(usernameInput.value);
    const containSpaceInNickname = /\s/.test(nicknameInput.value);
    const containSpaceInPassword = /\s/.test(passwordInput.value);

    if (containSpaceInUsername) {
      window.alert("계정에 공백은 허용되지 않습니다.");
      event.preventDefault();
    } else if (containSpaceInNickname) {
      window.alert("닉네임에 공백은 허용되지 않습니다.");
      event.preventDefault();
    } else if (containSpaceInPassword) {
      window.alert("비밀번호에 공백은 허용되지 않습니다.");
      event.preventDefault();
    }
  });
});