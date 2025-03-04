<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:09
  To change this template use File | Settings | File Templates.
  info="관리자 페이지의 상품 추가 뷰입니다."
--%>
<%@ page
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>상품 추가</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/admin/addProduct.css">
</head>
<body>
<section>
  <hr>
  <form id="add-product-form" action="/admin/product/add" method="post"
        enctype="multipart/form-data">
    <label for="product-image" class="form-label mt-4">상품 이미지: </label>
    <input type="file" id="product-image" class="form-control" name="image" multiple>
    <hr>
    <input type="text" id="product-name" class="form-control" name="productName" placeholder="상품명">
    <input type="text" id="product-price" class="form-control" name="price" placeholder="상품 가격">
    <textarea id="product-description" class="form-control" name="description" placeholder="상품 설명"></textarea>
    <button type="submit" class="btn btn-success">상품 등록</button>
  </form>
</section>
</body>
</html>
