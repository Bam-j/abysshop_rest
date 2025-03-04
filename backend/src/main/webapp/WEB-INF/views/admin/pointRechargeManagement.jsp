<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-01-14
  Time: 오후 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>포인트 지급 요청 관리</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<section>
  <h3 class="alert alert-warning"><strong>포인트 지급 전 반드시 요청 정보에 정보를 입력해주세요.</strong></h3>
  <table class="table table-hover">
    <thead>
    <tr class="table-primary">
      <th>요청 번호</th>
      <th>요청자</th>
      <th>요청 포인트</th>
      <th>요청일</th>
      <th>요청 상태</th>
      <th>요청 승인</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pointRechargeList}" var="pointRecharge" varStatus="status">
      <tr data-index="${status.index}">
        <td>${pointRecharge.rechargeId}</td>
        <td>${pointRecharge.nickname}</td>
        <td><fmt:formatNumber value="${pointRecharge.points}" pattern="#,###" /></td>
        <td><fmt:formatDate value="${pointRecharge.requestTime}" pattern="yyyy-MM-dd" /></td>
        <td>
          <div class="btn-group" data-index="${status.index}">
            <button type="button" id="dropdown-button" class="btn btn-success dropdown-toggle"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                ${pointRecharge.rechargeRequestState}
            </button>
            <ul class="dropdown-menu">
              <li>
                <a class="dropdown-item" data-value="pending_payment" href="#">송금 확인 대기</a>
              </li>
              <li>
                <a class="dropdown-item" data-value="pending_point_deposit" href="#">포인트 지급 대기</a>
              </li>
              <li>
                <a class="dropdown-item" data-value="completed" href="#">포인트 지급 완료</a>
              </li>
              <li>
                <a class="dropdown-item" data-value="refunded" href="#">환불 처리 완료</a>
              </li>
            </ul>
            <form action="/admin/recharge/change-state" method="post">
              <input type="hidden" name="rechargeId" value="${pointRecharge.rechargeId}">
              <input type="hidden" name="newState" id="newState" class="newState"
                     data-index="${status.index}" />
              <button type="submit" class="btn btn-primary change-button"
                      data-index="${status.index}">변경
              </button>
            </form>
          </div>
        </td>
        <td>
          <form action="/admin/point/provide" method="post" id="provide-confirm-form">
            <input type="hidden" name="userId" value="${pointRecharge.userId}" />
            <input type="hidden" name="point" value="${pointRecharge.points}" />
            <button type="submit" id="provide-confirm-button" class="btn btn-success"
                    data-index="${status.index}"
              ${pointRecharge.rechargeRequestState == "completed" ||
                  pointRecharge.rechargeRequestState == "refunded" ? "disabled" : ""}>
              지급 승인
            </button>
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
      <a href="?menu=point-recharge-management&page=${currentPage - 1}"
         class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?menu=point-recharge-management&page=${i}"
         class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?menu=point-recharge-management&page=${currentPage + 1}"
         class="page-link">&raquo;</a>
    </c:if>
  </div>

</section>
</body>
</html>
