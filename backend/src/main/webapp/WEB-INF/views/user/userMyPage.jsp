<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    info="사용자의 개인 마이 페이지입니다. 주문 내역(default), 사용자 정보 관리를 할 수 있습니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>마이 페이지</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/header.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/footer.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/pagination.css">
  <link rel="stylesheet" href="../../../resources/static/styles/user/userMyPage.css">
  <link rel="stylesheet" href="../../../resources/static/styles/user/userInfo.css">
  <link rel="shortcut icon" href="../../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<%@ include file="../common/header.jsp" %>

<%@ include file="userMyPageNav.jsp" %>

<main>
  <div id="content" class="user-my-page-content">
    <c:choose>
      <c:when test="${param.menu eq 'user-info'}">
        <jsp:include page="userInfo.jsp" />
      </c:when>
      <c:when test="${param.menu eq 'point-request'}">
        <jsp:include page="userPointRechargeList.jsp" />
      </c:when>
      <c:otherwise>
        <jsp:include page="userOrderList.jsp" />
      </c:otherwise>
    </c:choose>
  </div>
</main>

<%@ include file="../common/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="../../resources/static/js/common/navEvent.js"></script>
<script src="../../resources/static/js/common/translateState.js"></script>
</body>
</html>
