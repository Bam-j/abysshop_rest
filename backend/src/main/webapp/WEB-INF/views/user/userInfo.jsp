<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
  info="사용자 계정 정보를 확인/수정하는 뷰 입니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 정보 관리</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<section id="user-info-section">
  <br>
  <form id="change-nickname-form" action="/account/change/nickname" method="post">
    <div class="alert alert-danger" role="alert">
      <strong>반드시 인게임 닉네임과 동일한 닉네임으로 설정해주세요.</strong><br>
      인게임 닉네임과 상점 페이지 닉네임이 다른 경우 후원 과정에서 문제가 발생할 가능성이 높습니다.
    </div>
    <div class="input-group mb-3">
      <input type="hidden" name="userId" value="${user.userId}">
      <input type="text" class="nickname-input" name="newNickname" placeholder="변경할 닉네임 입력">
      <button type="submit" class="btn btn-primary">닉네임 변경</button>
    </div>
  </form>
  <hr>
  <form id="change-password-form" action="/account/change/password" method="post">
    <div class="input-group mb-3">
      <input type="hidden" name="userId" value="${user.userId}">
      <input type="password" class="password-input" name="newPassword" placeholder="변경할 비밀번호 입력">
      <button type="submit" class="btn btn-primary">비밀번호 변경</button>
    </div>
  </form>

  <hr>

  <button id="withdraw-button" type="button" class="btn btn-danger" data-bs-toggle="modal"
          data-bs-target="#withdraw-modal">
    회원 탈퇴
  </button>

  <div class="modal fade" id="withdraw-modal" tabindex="-1" aria-labelledby="withdraw-modal-label"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="withdraw-modal-label">회원 탈퇴 확인</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="alert alert-warning" role="alert">
            탈퇴 하시려면 현재 비밀번호를 입력해주세요.
          </div>
          <input class="password-input" type="text" placeholder="현재 비밀번호">
        </div>
        <div class="modal-footer">
          <form id="withdraw-form" action="/account/withdraw" method="post">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            <input type="hidden" name="userId" value="${user.userId}">
            <button type="submit" name="password" class="btn btn-danger">회원 탈퇴</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>

<%-- 에러 처리를 스프링 컨트롤러에서 처리하므로 스크립트는 제외
<script src="../../../../resources/static/js/user/userInfoValidation.js"></script>
--%>
<script>
  window.onload = () => {
    let failureMessage = "${failureMessage}";

    if (failureMessage !== "") {
      window.alert(failureMessage)
    }
  }
</script>
</body>
</html>
