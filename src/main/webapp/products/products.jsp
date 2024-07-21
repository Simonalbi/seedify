<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- TODO Filter products -->

<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/products/styles/style.css">

  <script type="module" src="${pageContext.request.contextPath}/products/scripts/script.js" defer></script>
</head>
<body>
  <jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

  <div class="main-page-content">
    <div id="featured-products-container">
      <div class="section-title">
        <h5 class="rubik-600">Prodotti più recenti</h5>
      </div>
      <div class="section-title-breakline"></div>
      <jsp:include page="/common/components/scrollable-container/scrollable-container.jsp">
        <jsp:param name="id" value="latest-products-scrollable-container" />
        <jsp:param name="loading-text" value="Caricamento prodotti..." />
      </jsp:include>
      <div class="section-title">
        <h5 class="rubik-600">Prodotti più acquistati</h5>
      </div>
      <div class="section-title-breakline"></div>
      <jsp:include page="/common/components/scrollable-container/scrollable-container.jsp">
        <jsp:param name="id" value="most-purchased-products-scrollable-container" />
        <jsp:param name="loading-text" value="Caricamento prodotti..." />
      </jsp:include>
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

  <jsp:include page="/common/components/main-footer/main-footer.jsp"/>
</body>
</html>
