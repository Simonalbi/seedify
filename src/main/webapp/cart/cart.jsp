<%@ page import="com.unisa.seedify.model.CartBean" %>
<%@ page import="com.unisa.seedify.model.ProductBean" %>
<%@ page import="com.unisa.seedify.model.CartItemBean" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    CartBean cartBean = (CartBean) request.getSession(true).getAttribute("cart");
%>

<%--TODO Errore nella costruzione del cart bean, un prodotto viene aggiunto una volta per quantità invece di aumentare solo il contatore--%>

<html lang="en">
<head>
    <jsp:include page="/common/general/metadata.jsp"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/cart/styles/style.css">

    <script type="module" src="${pageContext.request.contextPath}/cart/scripts/script.js" defer></script>
</head>
<body>
    <jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

    <div class="main-page-content">
        <div id="cart-products-container">
            <% for (CartItemBean cartItemBean : cartBean.getCartItems()) {
                ProductBean productBean = cartItemBean.getProduct(); %>
                <div class="product-container ui-block rubik-300" onclick="goToProductPage(<%= productBean.getProductId() %>)">
                    <button class="remove-from-cart-button material-button" onclick="sendRemoveFromCartRequest(this, <%= productBean.getProductId() %>, <%= cartItemBean.getQuantity() %>, <%= cartItemBean.getProduct().getPrice() %>)">
                        <span class="material-icons-round md-18">delete</span>
                    </button>
                    <div class="product-image-section">
                        <div class="product-image-container">
                            <img src="http://localhost:8080/seedify_war/resources-servlet?resource_type=product_image&amp;entity_primary_key=codice_prodotto=<%= productBean.getProductId() %>">
                        </div>
                    </div>
                    <div class="product-info-section">
                        <div class="product-info-container">
                            <div class="product-info">
                                <p class="product-name rubik-500"><%= productBean.getName() %></p>
                                <p class="product-price"><%= productBean.getPrice() %> €</p>
                            </div>
                        </div>
                    </div>
                    <p class="product-quantity">Quantità: <%= cartItemBean.getQuantity() %></p>
                </div>
            <% } %>
        </div>
        <div id="cart-menu" class="ui-block">
            <div id="cart-summary-container" class="ui-block">
                <div class="summary-info">
                    <span class="rubik-400">Prodotti:</span>
                    <span id="total-cart-products" class="rubik-300"><%= cartBean.getCartItems().size() %></span>
                </div>
                <div class="summary-info">
                    <span class="rubik-400">Totale:</span>
                    <span class="rubik-300"><span id="total-cart-price"><%= new DecimalFormat("0.00", new java.text.DecimalFormatSymbols(java.util.Locale.US)).format(cartBean.getTotalCartPrice()) %></span> €</span>
                </div>
            </div>
            <div id="cart-actions-container">
                <button id="go-to-checkout-button" class="material-button">
                    <span class="material-icons-round md-18">credit_score</span>
                    <span class="small-text">Procedi all'acquisto</span>
                </button>
            </div>
        </div>
    </div>

    <jsp:include page="/common/components/main-footer/main-footer.jsp" />
    <jsp:include page="/common/components/chat-button/chat-button.jsp" />
</body>
</html>
