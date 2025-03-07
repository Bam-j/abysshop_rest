<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-02-10
  Time: 오후 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>포인트 충전 요청 상세 정보</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<section>
  <table class="table table-hover">
    <thead>
    <tr class="table-primary">
      <th>요청 정보 번호</th>
      <th>요청 번호</th>
      <th>주문자</th>
      <th>입금 확인 일</th>
      <%-- TODO: 입금액 컬럼 추가를 고려해보자 --%>
      <th></th>
      <th></th>
      <th>은행, 계좌번호</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pointRechargeDetailList}" var="pointRechargeDetail" varStatus="status">
      <tr data-index="${status.index}">
        <td>${pointRechargeDetail.rechargeDetailId}</td>
        <td>${pointRechargeDetail.rechargeId}</td>
        <td>${pointRechargeDetail.nickname}</td>
        <td>
          <fmt:formatDate value="${pointRechargeDetail.depositConfirmedTime}"
                          pattern="yyyy-MM-dd" />
        </td>
        <td colspan="3">
          <form action="/point/recharge/detail" method="post">
            <div id="detail-input">
              <input type="text" class="form-control" name="bank"
                     value="${pointRechargeDetail.bank != null ? pointRechargeDetail.bank : ''}"
                     placeholder="은행" />
              <input type="text" class="form-control" name="accountNumber"
                     value="${pointRechargeDetail.accountNumber != null ? pointRechargeDetail.accountNumber : ''}"
                     placeholder="계좌번호" />
              <input type="hidden" name="rechargeDetailId"
                     value="${pointRechargeDetail.rechargeDetailId}" data-index="${status.index}" />
              <button type="submit" class="btn btn-success" data-index="${status.index}">입력</button>
            </div>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <%-- 페이지네이션 UI --%>
  <div class="pagination">
    <%-- 이전 버튼 (첫 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage > 1}">
      <a href="?menu=point-recharge-detail&page=${currentPage - 1}" class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?menu=point-recharge-detail&page=${i}"
         class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?menu=point-recharge-detail&page=${currentPage + 1}" class="page-link">&raquo;</a>
    </c:if>
  </div>
</section>
</body>
</html>