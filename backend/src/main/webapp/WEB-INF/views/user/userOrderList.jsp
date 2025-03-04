<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 7:54
  To change this template use File | Settings | File Templates.
  info="사용자의 주문 내역 리스트를 보여주는 뷰 입니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원 주문 관리</title>
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
      <%-- TODO: 어떤 상품을 주문했는지 상품명도 나오면 좋을듯! EX) oooo 외 0건 --%>
      <th>주문 번호</th>
      <th>주문 총 포인트</th>
      <th>구매일</th>
      <th>주문 상태</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userOrderList}" var="order">
      <tr>
        <td>${order.orderId}</td>
        <td><fmt:formatNumber value="${order.totalPoints}" pattern="#,###" /></td>
        <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></td>
        <td data-state="${order.orderState}">${order.orderState}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <%-- 페이지네이션 UI --%>
  <div class="pagination">
    <%-- 이전 버튼 (첫 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage > 1}">
      <a href="?menu=order-management&page=${currentPage - 1}" class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?menu=order-management&page=${i}"
         class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?menu=order-management&page=${currentPage + 1}" class="page-link">&raquo;</a>
    </c:if>
  </div>
</section>
</body>
</html>
