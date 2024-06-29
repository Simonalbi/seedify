<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.unisa.seedify.model.ProductDao" %>

<!-- TODO Add to favorites -->
<!-- TODO Add to cart -->
<!-- TODO Filter products -->
<!-- TODO Request products to server -->
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
    <div id="latests-products-container">
      <div class="section-title">
        <h5 class="rubik-500">Prodotti più recenti</h5>
      </div>
      <!-- TODO Scroll with JS -->
      <div id="scrollable-container">
        <button class="scrollable-command-button material-button" id="previous-button">
          <span class="material-icons-round md-18">arrow_back_ios</span>
        </button>
        <div class="section-title-breakline"></div>
        <div id="latests-products">
          <% for (int i = 0; i < 10; i++) { %>
            <div class="product-container ui-block rubik-300">
            <button class="favorite-button material-button">
              <span class="material-icons-round md-18">favorite_border</span>
            </button>
            <div class="product-image-section">
              <div class="product-image-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/product_placeholder.jpeg" alt="">
              </div>
            </div>
            <div class="product-info-section">
              <div class="product-info-container">
                <div class="product-info">
                  <p class="product-title rubik-600">Piantina delicata</p>
                  <p class="product-price">1200.00 €</p>
                </div>
                <div class="product-actions">
                  <button class="cart-button material-button">
                    <span class="material-icons-round md-18">add_shopping_cart</span>
                    <span class="small-text">Aggiungi al carrello</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
          <% } %>
        </div>
        <button class="scrollable-command-button material-button" id="next-button">
          <span class="material-icons-round md-18">arrow_forward_ios</span>
        </button>
      </div>
    </div>
    <div id="all-products-container">
      <div class="section-title">
        <h5 class="rubik-500">Tutti i nostri prodotti</h5>
      </div>
      <div class="section-title-breakline"></div>
      <%--<div class="products-category-container">
        <div class="category-title">
          <h6 class="rubik-400">Categoria 1</h6>
        </div>
        <div class="category-products">
          <% for (int i = 0; i < 21; i++) { %>
            <div class="product-container ui-block rubik-300">
              <button class="favorite-button material-button">
                <span class="material-icons-round md-18">favorite_border</span>
              </button>
              <div class="product-image-section">
                <div class="product-image-container">
                  <img src="${pageContext.request.contextPath}/common/assets/img/product_placeholder.jpeg" alt="">
                </div>
              </div>
              <div class="product-info-section">
                <div class="product-info-container">
                  <div class="product-info">
                    <p class="product-name rubik-600">Piantina delicata</p>
                    <p class="product-price">1200.00 €</p>
                  </div>
                  <div class="product-actions">
                    <button class="cart-button material-button">
                      <span class="material-icons-round md-18">add_shopping_cart</span>
                      <span class="small-text">Aggiungi al carrello</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          <% } %>
        </div>
      </div>
      <div class="category-separator"></div>
      <div class="products-category-container">
      <div class="category-title">
        <h6 class="rubik-400">Categoria 2</h6>
      </div>
      <div class="category-products">
        <% for (int i = 0; i < 13; i++) { %>
        <div class="product-container ui-block rubik-300">
          <button class="favorite-button material-button">
            <span class="material-icons-round md-18">favorite_border</span>
          </button>
          <div class="product-image-section">
            <div class="product-image-container">
              <img src="${pageContext.request.contextPath}/common/assets/img/product_placeholder.jpeg" alt="">
            </div>
          </div>
          <div class="product-info-section">
            <div class="product-info-container">
              <div class="product-info">
                <p class="product-title rubik-600">Piantina delicata</p>
                <p class="product-price">1200.00 €</p>
              </div>
              <div class="product-actions">
                <button class="cart-button material-button">
                  <span class="material-icons-round md-18">add_shopping_cart</span>
                  <span class="small-text">Aggiungi al carrello</span>
                </button>
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </div>
    </div>--%>
    </div>
  </div>

  <jsp:include page="/common/components/chat-button/chat-button.jsp"/>
</body>
</html>
