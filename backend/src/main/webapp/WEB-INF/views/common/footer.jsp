<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:59
  To change this template use File | Settings | File Templates.
  info="페이지 전반에서 사용되는 푸터 뷰 입니다. 로고, 서버 주소, 디스코드 주소 등이 포함됩니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>footer</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<footer>
  <div class="square-logo">
    <a href="/">
      <img src="../../../resources/static/images/abyssblock_square_64x64.png" alt="어비스 블록 미니멀 로고">
    </a>
  </div>
  <ul class="address-menu">
    <li id="server-address">서버 주소</li>
    <li id="discord-address">디스코드 주소</li>
  </ul>
</footer>
</body>
</html>
