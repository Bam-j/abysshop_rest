<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-01-14
  Time: 오후 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>포인트 지급 요청 폼</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<nav>
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <c:choose>
        <c:when test="${user.userType == 'user'}">
          <button id="point-recharge-button" type="button" class="btn btn-primary"
                  data-bs-toggle="modal"
                  data-bs-target="#point-recharge-modal">
            포인트 충전
          </button>
        </c:when>
      </c:choose>
    </c:when>
  </c:choose>

  <div class="modal fade" id="point-recharge-modal" tabindex="-1"
       aria-labelledby="point-recharge-modal-label"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="point-recharge-modal-label">포인트 충전 요청</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="alert alert-warning" role="alert">
            <h3><strong>[계좌 번호를 적을 공간]</strong></h3>
            <ul>
              <li><strong>반드시 아래 안내 사항들을 읽고 송금을 한 후 송금 완료 버튼을 클릭해주세요.</strong></li>
              <li>송금하실 때 송금자를 닉네임과 동일하게 설정해주세요.</li>
              <li>입금 후 포인트 지급까지 <strong>10분에서 24시간</strong>까지 소요될 수 있습니다.</li>
              <li>결제 과정에서 문의는 디스코드에서 받고있습니다.</li>
            </ul>
          </div>
        </div>
        <div class="modal-footer">
          <form id="recharge-request-form" action="/point/recharge/request" method="post">
            <div class="input-group mb-3">
              <input type="hidden" name="userId" value="${user.userId}">
              <input type="hidden" name="nickname" value="${user.nickname}">
              <input type="text" id="point-input" class="form-control" name="points"
                     placeholder="요청 포인트">
              <button type="submit" id="button-addon2" class="btn btn-primary">포인트 충전</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</nav>
</body>

<script src="../../resources/static/js/point/pointRechargeFormValidation.js"></script>
</html>
