<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    info="회원 로그인 폼 페이지입니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../../resources/static/styles/account/signIn.css">
  <link rel="shortcut icon" href="../../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<main>
  <aside>
    <a class="btn btn-outline-primary btn-sm" href="/">
      <i class="bi bi-arrow-left"></i> 메인으로
    </a>
  </aside>
  <section>
    <div class="mark-logo">
      <img src="../resources/static/images/abyssblock_mark_sd.png" alt="어비스블록 로고">
    </div>
    <form id="sign-in-form" action="/account/sign-in" method="post">
      <input class="username-input" name="username" type="text" placeholder="계정">
      <input class="password-input" name="password" type="password" placeholder="비밀번호">
      <button type="submit" class="btn btn-primary">로그인</button>
    </form>
    <form action="/account/sign-up" method="get">
      <button type="submit" class="btn btn-secondary">회원가입</button>
    </form>
  </section>
</main>
<%-- 에러 처리를 스프링 컨트롤러에서 처리하므로 스크립트는 제외
<script src="../../../../resources/static/js/account/signInValidation.js"></script>
--%>
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
