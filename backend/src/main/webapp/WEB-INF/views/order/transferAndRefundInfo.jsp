<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-01-22
  Time: 오후 6:52
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
  <title>계좌 이체와 환불 안내</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<nav>
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <button id="transfer-refund-button" type="button" class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#transfer-refund-modal">
        계좌 이체 & 환불 안내 사항
      </button>
    </c:when>
  </c:choose>

  <div class="modal fade" id="transfer-refund-modal" tabindex="-1"
       aria-labelledby="transfer-refund-modal-label"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="transfer-refund-modal-label">포인트 충전 요청 & 환불 안내</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="alert alert-warning" role="alert">
            <h2>[계좌 이체 안내]</h2>
            <h3><strong>[계좌 번호를 적을 공간]</strong></h3>
            <ul>
              <li>송금하실 때 송금자를 닉네임과 동일하게 설정해주세요.</li>
              <li>입금 후 포인트 지급까지 <strong>10분에서 24시간</strong>까지 소요될 수 있습니다.</li>
              <li>결제 과정에서 문의는 디스코드에서 받고있습니다.</li>
            </ul>
            <hr>
            <h2>[환불 안내]</h2>
            <ul>
              <li>환불 요청은 주문 번호, 송금자, 계좌번호, 금액, 이체 증명(이미지 등)를 첨부해서 디스코드로 요청해주세요.</li>
              <li>환불 요청 후 처리까지 <strong>10분에서 48시간</strong>까지 소요될 수 있습니다.</li>
            </ul>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</nav>
</body>
</html>
