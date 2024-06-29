<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.unisa.seedify.model.ProductDao" %>

<!-- TODO Add to favorites -->
<!-- TODO Add to cart -->
<!-- TODO Filter products -->
<%
  ProductDao productDao = ProductDao.getInstance();
%>

<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/products/styles/style.css">

  <script type="module" src="${pageContext.request.contextPath}/products/scripts/script.js" defer></script>
</head>
<body>
  <jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

  <div class="main-page-content">
    <div id="latest-products-container">
      <div class="section-title">
        <h5 class="rubik-600">Prodotti più recenti</h5>
      </div>
      <!-- TODO Scroll with JS -->
      <div id="scrollable-container">
        <button class="scrollable-command-button material-button" id="previous-button">
          <span class="material-icons-round md-18">arrow_back_ios</span>
        </button>
        <div class="section-title-breakline"></div>
        <div id="latest-products">
          <h6 class="rubik-400" id="loading-latest-products-text">Caricamento prodotti...</h6>
        </div>
        <button class="scrollable-command-button material-button" id="next-button">
          <span class="material-icons-round md-18">arrow_forward_ios</span>
        </button>
      </div>
    </div>
    <div id="all-products-container">
      <div class="section-title">
        <h5 class="rubik-600">Tutti i nostri prodotti</h5>
      </div>
      <div class="section-title-breakline"></div>
      <div id="loading-text-container">
        <h6 class="rubik-400" id="loading-all-products-text">Caricamento prodotti...</h6>
      </div>
    </div>
  </div>

  <jsp:include page="/common/components/chat-button/chat-button.jsp"/>
</body>
</html>
