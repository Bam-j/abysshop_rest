<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:01
  To change this template use File | Settings | File Templates.
  info="상품 리스트를 보여주는 뷰 입니다. 리스트의 항목을 클릭하면 해당 물품의 상세 페이지로 이동합니다."
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
  <title>상품 목록</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/cosmo/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/product/productList.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/pagination.css">
</head>
<body>
<section>
  <h2>상품 목록</h2>
  <div class="item-list">
    <c:forEach items="${productList}" var="product">
      <div class="item" data-item-id="${product.productId}">
        <a href="/product/detail/${product.productId}">
          <img src="/upload/${product.originalFileName}" class="card-img-top"
               alt="${product.productName}">
          <div class="card-body">
            <h5 class="card-title">${product.productName}</h5>
            <p class="card-text"><fmt:formatNumber value="${product.price}" pattern="#,###" /></p>
          </div>
        </a>
      </div>
    </c:forEach>
  </div>

  <%-- 페이지네이션 UI --%>
  <div class="pagination">
    <%-- 이전 버튼 (첫 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage > 1}">
      <a href="?page=${currentPage - 1}" class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?page=${i}" class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?page=${currentPage + 1}" class="page-link">&raquo;</a>
    </c:if>
  </div>

</section>
</body>
</html>
