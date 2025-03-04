<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2025-01-14
  Time: 오후 9:06
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
  <title>회원 포인트 지급 요청 관리</title>
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
      <th>요청 번호</th>
      <th>요청 포인트</th>
      <th>요청일</th>
      <th>요청 상태</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userPointRechargeList}" var="request">
      <tr>
        <td>${request.rechargeId}</td>
        <td><fmt:formatNumber value="${request.points}" pattern="#,###" /></td>
        <td><fmt:formatDate value="${request.requestTime}" pattern="yyyy-MM-dd" /></td>
        <td data-state="${request.rechargeRequestState}">${request.rechargeRequestState}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <%-- 페이지네이션 UI --%>
  <div class="pagination">
    <%-- 이전 버튼 (첫 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage > 1}">
      <a href="?menu=point-request&page=${currentPage - 1}" class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?menu=point-request&page=${i}"
         class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?menu=point-request&page=${currentPage + 1}" class="page-link">&raquo;</a>
    </c:if>
  </div>
</section>
</body>
</html>
