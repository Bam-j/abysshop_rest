<%--
  Created by IntelliJ IDEA.
  User: juhyu
  Date: 2024-12-03
  Time: 오후 8:06
  To change this template use File | Settings | File Templates.

  !!! 포인트 요청 페이지가 생김에 따라 더 이상 사용되지 않음 !!!
--%>
<%@ page
    info="포인트 주문 후 이체용 계좌를 표시하는 페이지입니다."
    contentType="text/html;charset=UTF-8"
    language="java"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
  <title>계좌이체 안내</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="../../../resources/static/styles/common/common.css">
  <link rel="stylesheet" href="../../../resources/static/styles/order/bankTransferInfo.css">
  <link rel="shortcut icon" href="../../../resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<main>
  <section>
    <div class="mark-logo">
      <img src="../resources/static/images/abyssblock_mark_sd.png" alt="어비스블록 로고">
    </div>
    <div id="bank-transfer-info">
      <h2>[계좌 번호를 적을 공간]</h2>
      <ul>
        <li><strong>반드시 아래 안내 사항들을 읽고 송금을 한 후 송금 완료 버튼을 클릭해주세요.</strong></li>
        <li>송금자 명을 닉네임과 동일하게 설정해주세요.</li>
        <li>입금 후 포인트/상품 지급까지 5분에서 최대 20분까지 소요될 수 있습니다.</li>
        <li>결제 과정에서 문의는 디스코드에서 받고있습니다.</li>
      </ul>
    </div>
    <form action="/payment/complete" method="get">
      <button type="button" class="btn btn-primary">
        송금 완료 <i class="bi bi-arrow-right"></i>
      </button>
    </form>
  </section>
</main>
</body>
</html>
