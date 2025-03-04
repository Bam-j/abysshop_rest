<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:23
  To change this template use File | Settings | File Templates.
  info="관리자 페이지의 상품 삭제 뷰 입니다."
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
  <title>상품 삭제</title>
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
      <th>상품 번호</th>
      <th>상품명</th>
      <th>가격</th>
      <th>삭제</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="product">
      <tr>
        <td>${product.productId}</td>
        <td>${product.productName}</td>
        <td><fmt:formatNumber value="${product.price}" pattern="#,###" /></td>
        <td>
          <form action="/admin/product/remove" method="post">
            <input type="hidden" name="productId" value="${product.productId}">
            <%-- TODO: 삭제 버튼 클릭시 재확인하는 modal이나 alert 넣기 --%>
            <button type="submit" class="btn btn-danger">품목 삭제</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <%-- 페이지네이션 UI --%>
  <div class="pagination">
    <%-- 이전 버튼 (첫 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage > 1}">
      <a href="?menu=remove-product&page=${currentPage - 1}" class="page-link">&laquo;</a>
    </c:if>

    <%-- 페이지 번호 표시 --%>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <a href="?menu=remove-product&page=${i}"
         class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- 다음 버튼 (마지막 페이지가 아닐 때만 활성화) --%>
    <c:if test="${currentPage < totalPages}">
      <a href="?menu=remove-product&page=${currentPage + 1}" class="page-link">&raquo;</a>
    </c:if>
  </div>

</section>
</body>
</html>
