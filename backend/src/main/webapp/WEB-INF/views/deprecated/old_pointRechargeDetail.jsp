<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-01-14
  Time: 오후 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>포인트 충전 요청 상세 정보</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<section>
  <form action="/point/recharge/detail" method="get"  id="rechargeDetailForm">
    <input type="hidden" name="rechargeId" value="${pointRecharge.rechargeId}">
    <button id="manage-recharge-detail-button" type="button" class="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#manage-recharge-detail-modal">
      상세 정보 입력
    </button>
  </form>

  <div class="modal fade" id="manage-recharge-detail-modal" tabindex="-1"
       aria-labelledby="manage-recharge-detail-modal-label"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="manage-recharge-detail-modal-label">포인트 충전 상세 정보</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="alert alert-warning" role="alert">
            정확한 정보를 입력해주세요.<br>
            입력된 정보는 환불 등 처리에 사용됩니다.
          </div>
          <form action="/point/recharge/detail" method="post">
            <input type="hidden" name="rechargeId" value="${pointRechargeDetail.rechargeId}">
            <input type="hidden" name="userId" value="${pointRechargeDetail.userId}">
            <input type="text" name="bank" value="${pointRechargeDetail.bank != null ?
            pointRechargeDetail.bank : ''}" placeholder="은행">
            <input type="text" name="accountNumber" value="${pointRechargeDetail.accountNumber!= null ?
            pointRechargeDetail.accountNumber : ''}" placeholder="계좌 번호">
            <button type="submit" class="btn btn-primary">상세 정보 저장</button>
          </form>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>
