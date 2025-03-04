<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:48
  To change this template use File | Settings | File Templates.
  info="페이지 전반에서 사용되는 헤더 뷰 입니다. 로고, 로그인/로그아웃 버튼, 마이페이지, 장바구니와 같은 버튼이 존재합니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>header</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<header>
  <div class="square-logo">
    <a href="/">
      <img id="header-logo" src="../../../resources/static/images/abyssblock_square_64x64.png"
           alt="어비스 블록 미니멀 로고">
    </a>
  </div>
  <ul class="action-menu">
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <c:choose>
          <c:when test="${user.userType == 'admin'}">
            <li class="divider-elem">
              <a href="/admin/my-page?menu=order-management" class="btn btn-primary">
                관리자 페이지
              </a>
            </li>
          </c:when>
          <c:otherwise>
            <li>
              <button type="button" class="btn btn-success disabled">
                  ${user.points} 포인트
              </button>
            </li>
            <li>
              <form action="/user/cart/${user.cartId}" method="get">
                <input type="hidden" name="userId" value="${user.userId}">
                <button type="submit" class="btn btn-primary">
                  <i class="bi bi-cart"></i>
                  장바구니 <span class="badge bg-secondary">${cart.quantity}</span>
                </button>
              </form>
            </li>

            <li>
              <a href="/user/my-page/${user.userId}?menu=order-management" class="btn btn-primary">
                마이페이지
              </a>
            </li>
          </c:otherwise>
        </c:choose>
        <li>
          <form action="/account/logout" method="post">
            <button type="submit" class="btn btn-primary">로그아웃</button>
          </form>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <form action="/account/sign-in" method="get">
            <button type="submit" class="btn btn-primary">로그인</button>
          </form>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
</header>
</body>
</html>
