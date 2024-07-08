<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.unisa.seedify.model.ProductBean" %>
<%@ page import="java.util.Base64" %>
<%@ page import="org.apache.tika.Tika" %>
<%@ page import="com.unisa.seedify.model.FavoritesBean" %>
<%@ page import="com.unisa.seedify.model.UserBean" %>
<%@ page import="com.unisa.seedify.model.FavoritesDao" %>

<%
  ProductBean productBean = (ProductBean) request.getAttribute("product_bean");

  boolean isFavorite = false;
  try {
    UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
    FavoritesDao favoritesDao = FavoritesDao.getInstance();
    FavoritesBean userFavorites = favoritesDao.getUserFavorites(userBean);

    if (userFavorites.getProducts().contains(productBean)) {
      isFavorite = true;
    }
  } catch (Exception ignored) {}

  String favoriteIcon = isFavorite ? "favorite" : "favorite_border";

  byte[] image = productBean.getImage();
  Tika tika = new Tika();
  String mimeType = tika.detect(image);
  String base64Image = Base64.getEncoder().encodeToString(image);
  String imageSrc = "data:" + mimeType + ";base64," + base64Image;
%>

<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/product/styles/style.css">

  <script type="module" src="${pageContext.request.contextPath}/product/scripts/script.js" defer></script>
</head>
<body>
  <jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

  <div class="main-page-content">
    <div class="product-page-section" id="product-image-section">
      <div id="product-image-container">
        <img src="<%= imageSrc %>" alt="">
      </div>
    </div>
    <div class="product-page-section" id="product-main-info-section">
      <div id="product-title">
        <div class="section-title">
          <h4 class="rubik-600"><%= productBean.getName() %></h4>
        </div>
        <div class="section-title-breakline"></div>
      </div>
      <div id="product-main-info">
        <div id="product-price-container" class="rubik-400">
          <span>1200.0 €</span>
        </div>
        <div id="product-description-container" class="rubik-300">
          <div class="section-title">
            <span class="rubik-500">Descrizione prodotto</span>
          </div>
          <div class="product-section-breakline"></div>
          <div id="product-description"><%= productBean.getDescription() %></div>
        </div>
      </div>
    </div>
    <div class="product-page-section ui-block" id="product-other-info-section">
      <div id="other-info-container" class="rubik-300 ui-block">
        <div class="other-info">
          <span class="rubik-400">Acqua:</span>
          <% if (productBean.getRequiredWater() == ProductBean.RequiredWater.LITTLE) { %>
            <span class="material-icons-outlined water-drop">water_drop</span>
          <% } else if (productBean.getRequiredWater() == ProductBean.RequiredWater.NORMAL) { %>
            <span class="material-icons-outlined water-drop">water_drop</span>
            <span class="material-icons-outlined water-drop">water_drop</span>
          <% } else if (productBean.getRequiredWater() == ProductBean.RequiredWater.A_LOT) { %>
            <span class="material-icons-outlined water-drop">water_drop</span>
            <span class="material-icons-outlined water-drop">water_drop</span>
            <span class="material-icons-outlined water-drop">water_drop</span>
          <% } %>
        </div>
        <div class="other-info">
          <span class="rubik-400">Stagione:</span>
          <% if (productBean.getSeason() == ProductBean.Seasons.SUMMER) { %>
            <span class="season">Estate</span>
            <span class="material-icons-round">wb_sunny</span>
          <% } else if (productBean.getSeason() == ProductBean.Seasons.WINTER) { %>
            <span class="season">Inverno</span>
            <span class="material-icons-round">ac_unit</span>
          <% } else if (productBean.getSeason() == ProductBean.Seasons.AUTUMN) { %>
            <span class="season">Autunno</span>
            <span class="material-icons-round">nights_stay</span>
          <% } else if (productBean.getSeason() == ProductBean.Seasons.SPRING) { %>
            <span class="season">Primavera</span>
            <span class="material-icons-round">local_florist</span>
          <% } %>
        </div>
        <div class="other-info">
          <span class="rubik-400">Categoria:</span>
          <span class="season"><%= productBean.getPlantType().toUpperCase().charAt(0) + productBean.getPlantType().toLowerCase().substring(1) %></span>
        </div>
      </div>
      <div id="user-actions-container" class="rubik-300">
        <button id="add-to-favorites-button" class="material-button" onclick="addOrRemoveFromFavorites(this, <%= productBean.getProductId() %>)">
          <span class="material-icons-round md-18"><%= favoriteIcon %></span>
          <span class="small-text">Aggiungi ai preferiti</span>
        </button>
        <div id="add-to-cart-form-container">
          <button id="add-to-cart-button" class="material-button" onclick="sendAddToCartRequest(<%= productBean.getProductId() %>, 1)">
            <span class="material-icons-round md-18">add_shopping_cart</span>
            <span class="small-text">Aggiungi al carrello</span>
          </button>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="/common/components/main-footer/main-footer.jsp"/>
  <jsp:include page="/common/components/chat-button/chat-button.jsp"/>
</body>
</html>

