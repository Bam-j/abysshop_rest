<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    info="사용자 회원가입 폼 페이지입니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 가입</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../../resources/static/styles/account/signUp.css">
  <link rel="shortcut icon" href="../../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<main>
  <aside>
    <a class="btn btn-outline-primary btn-sm" href="/account/sign-in">
      <i class="bi bi-arrow-left"></i> 로그인
    </a>
  </aside>
  <section>
    <div id="logo">
      <img src="../resources/static/images/abyssblock_mark_sd.png" alt="어비스블록 로고">
    </div>
    <form id="sign-up-form" action="/account/sign-up" method="post">
      <input class="username-input" name="username" type="text" placeholder="계정">
      <input class="nickname-input" name="nickname" type="text" data-bs-toggle="tooltip"
             data-bs-placement="right" data-bs-html="true"
             title="인게임 닉네임과 동일하게 가입해주세요.<br>거래 과정에서 문제가 발생할 수 있습니다." placeholder="마인크래프트 닉네임">
      <input class="password-input" name="password" type="password" placeholder="비밀번호">
      <button type="submit" id="sign-up-button" class="btn btn-primary">회원가입</button>
    </form>
  </section>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="../../../../resources/static/js/account/signUpValidation.js"></script>
<script src="../../../../resources/static/js/account/showSignUpPasswordTooltip.js"></script>
<script>
  //window.alert 대신 모달 alert 형식으로 변경할 것을 고려
  window.onload = () => {
    let failureMessage = "${failureMessage}";

    if (failureMessage !== "") {
      window.alert(failureMessage)
    }
  }
</script>
</body>
</html>
