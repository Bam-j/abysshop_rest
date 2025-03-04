<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:04
  To change this template use File | Settings | File Templates.
  info="관리자 페이지의 주문 관리 뷰 입니다."
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
  <title>관리자 주문 관리</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/table.css">
</head>
<body>
<section>
  <table class="table table-hover">
    <thead>
    <tr class="table-primary">
      <th>주문 번호</th>
      <th>주문자</th>
      <%--<th>주문 상품</th>--%>
      <th>주문 포인트</th>
      <th>구매일</th>
      <th>주문 상태</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orderList}" var="order" varStatus="status">
      <tr data-index="${status.index}">
        <td>${order.orderId}</td>
        <td>{order.userId}.${order.nickname}</td>
        <td><fmt:formatNumber value="${order.totalPoints}" pattern="#,###" /></td>
        <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></td>
        <td>
          <div class="btn-group" role="group" data-index="${status.index}">
            <button type="button" id="dropdown-button" class="btn btn-success dropdown-toggle"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                ${order.orderState}
            </button>
            <ul class="dropdown-menu">
              <li>
                <a class="dropdown-item" data-value="shipping" href="#">상품 지급 대기</a>
              </li>
              <li>
                <a class="dropdown-item" data-value="completed" href="#">상품 지급 완료</a>
              </li>
              <li>
                <a class="dropdown-item" data-value="refunded" href="#">환불 처리 완료</a>
              </li>
            </ul>
            <form action="/admin/order/change-state" method="post">
              <input type="hidden" name="orderId" value="${order.orderId}">
              <input type="hidden" name="newState" id="newState" class="newState"
                     data-index="${status.index}" />
              <button type="submit" class="btn btn-primary change-button"
                      data-index="${status.index}">변경
              </button>
            </form>
          </div>
        </td>
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
