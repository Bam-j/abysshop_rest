<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    info="사용자가 장바구니에 담은 상품 목록들을 보여주는 페이지입니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/header.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/footer.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/table.css">
  <link rel="stylesheet" href="../../../resources/static/styles/cart/shoppingCart.css">
  <link rel="shortcut icon" href="../../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<%@ include file="../common/header.jsp" %>
<nav class="cart-content">
  <a href="/" type="button" class="btn btn-outline-primary">
    <i class="bi bi-arrow-left"></i>메인으로
  </a>
</nav>
<main class="cart-content">
  <section>
    <table id="cart-table" class="table table-hover">
      <thead>
      <tr class="table-primary">
        <th>상품명</th>
        <td>수량</td>
        <th>포인트</th>
        <th>삭제</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${cartItemList}" var="item">
        <tr>
          <td>${item.productName}</td>
          <td>
              <%--페이지에 보여질 totalPoints(price), quantity는 스크립트에서 계산, order 저장시는 백엔드에서 계산--%>
            <div class="quantity-controller">
              <button id="plus-button" class="btn btn-primary"
                      data-cart-id="${cart.cartId}" data-user-id="${user.userId}"
                      data-product-id="${item.productId}">
                +
              </button>
              <p id="quantity"><strong>${item.quantity}</strong></p>
              <button id="minus-button" class="btn btn-primary"
                      data-cart-id="${cart.cartId}" data-user-id="${user.userId}"
                      data-product-id="${item.productId}">
                -
              </button>
            </div>
          </td>
          <td><fmt:formatNumber value="${item.price}" pattern="#,###" /></td>
          <td>
            <form action="/cart/item/remove" method="post">
              <input type="hidden" name="cartId" value="${cart.cartId}">
              <input type="hidden" name="productId" value="${item.productId}">
              <input type="hidden" name="userId" value="${user.userId}">
              <button id="item-remove-button" class="btn btn-danger">X</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
      <tfoot>
      <tr>
        <td>주문 합계 포인트: <fmt:formatNumber value="${cart.totalPoints}" pattern="#,###" /></td>
        <td>총 수량: ${cart.quantity}</td>
        <td>
          <form action="/order/create" method="post">
            <input type="hidden" name="userId" value="${user.userId}">
            <input type="hidden" name="cartId" value="${cart.cartId}">
            <button type="submit" class="btn btn-success">구매하기</button>
          </form>
        </td>
      </tr>
      </tfoot>
    </table>
  </section>
</main>

<%@ include file="../common/footer.jsp" %>

<script src="../../resources/static/js/cart/quantityControl.js"></script>
</body>
</html>
