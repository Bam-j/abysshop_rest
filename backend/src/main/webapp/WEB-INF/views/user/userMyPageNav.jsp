<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:54
  To change this template use File | Settings | File Templates.
  info="회원의 개인 페이지의 nav에서 사용되는 메뉴 바입니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>일반 회원 마이페이지 메뉴바</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/nav.css">
</head>
<body>
<nav class="nav">
  <a class="nav-link active" aria-current="page"
     href="/user/my-page/${user.userId}?menu=order-management&page=1"
     onclick="changeURL('order-management')">주문 관리</a>
  <a class=" nav-link" href="/user/my-page/${user.userId}?menu=point-request&page=1"
     onclick="changeURL('point-request')">포인트 지급 요청</a>
  <a class="nav-link" href="/user/my-page/${user.userId}?menu=user-info&"
     onclick="changeURL('user-info')">계정 관리</a>
</nav>

<script type="text/javascript">
  function changeURL(menu) {
    if (menu === "order-management") {
      history.replaceState(null, null, "/user/my-page/${user.userId}?menu=order-management");
    } else if (menu === "user-info") {
      history.replaceState(null, null, "/user/my-page/${user.userId}?menu=user-info");
    } else if (menu === "point-request") {
      history.replaceState(null, null, "/user/my-page/${user.userId}?menu=point-request");
    }
  }
</script>
</body>
</html>
