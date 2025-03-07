<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-01
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    info="Abysshop의 메인 페이지입니다. 상품 목록을 보여줍니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Abysshop 홈</title>
  <%-- 기존 바닐라 부트스트랩 CDN
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  --%>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../resources/static/styles/common/header.css">
  <link rel="stylesheet" href="../../resources/static/styles/common/footer.css">
  <link rel="stylesheet" href="../../resources/static/styles/index.css">
  <link rel="stylesheet" href="../../resources/static/styles/product/productList.css">
  <link rel="shortcut icon" href="../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<%@ include file="common/header.jsp" %>

<%--
  캐러셀은 주로 신상품 광고에 사용된다.
  해당 캐러셀 광고 클릭시 해당 상품의 Detail Page로 이동하도록 만들것.
--%>
<aside>
  <div id="carouselIndicators" class="carousel slide">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="0"
              class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="1"
              aria-label="Slide 2"></button>
      <%--  carousel에 컨텐츠 추가시 아래 코드를 copy & paste
      <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="1"
              aria-label="Slide 2"></button>
      --%>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../resources/static/images/abyssblock_logo.png" class="d-block w-100"
             alt="abyssblock_logo.png">
      </div>
      <div class="carousel-item">
        <img src="../resources/static/images/abyssblock_mark.png" class="d-block w-100"
             alt="abyssblock_mark.png">
      </div>
      <%--  carousel에 컨텐츠 추가시 아래 코드를 copy & paste
      <div class="carousel-item">
        <img src="..." class="d-block w-100" alt="...">
      </div>
      --%>
    </div>
    <button class="carousel-control-prev" type="button"
            data-bs-target="#carouselIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button"
            data-bs-target="#carouselIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</aside>

<nav id="point-menu">
  <%@ include file="point/pointRechargeRequestForm.jsp" %>
  <%@ include file="order/transferAndRefundInfo.jsp" %>
</nav>

<main id="index-main-content">
  <div id="content">
    <jsp:include page="product/productList.jsp" />
  </div>
</main>

<%@ include file="common/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="../resources/static/js/index.js"></script>
<script src="../resources/static/js/common/textCopy.js"></script>
<script src="../resources/static/js/product/moveToDetail.js"></script>
<script>
  window.onload = () => {
    let failureMessage = "${failureMessage}";

    if (failureMessage !== "") {
      window.alert(failureMessage)
    }
  }
</script>
</body>
</html>
