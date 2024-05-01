<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seedify</title>

    <jsp:include page="/common/general/metadata.jsp"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/home/style.css">
</head>
<body>
    <jsp:include page="/common/components/header/heade.jsp"/>
    <jsp:include page="/common/components/chat-button/chat-button.jsp"/>

    <div id="main-container">
        <div id="welcome-container">
            <div class="expandable">
                <a href="#">
                    Chi siamo?
                    <span class="material-icons md-18">info</span>
                </a>
            </div>
            <div id="message-container">
                <div id="slogan-container">
                    <p id="slogan">Dove ogni seme è un sogno in attesa di fiorire</p>
                </div>
                <div id="login-container">
                    <div id="sign-up-button" class="animated-round-button">
                        <a href="#">
                            <lord-icon class="round-button" src="https://cdn.lordicon.com/ysonqgnt.json" trigger="hover" colors="primary:#ffffff,secondary:#ffffff"></lord-icon>
                            Registrati
                        </a>
                    </div>
                    <div id="sign-in-button" class="animated-round-button">
                        <a href="#">
                            <lord-icon class="round-button" src="https://cdn.lordicon.com/lsfszdzd.json" trigger="hover" colors="primary:#ffffff,secondary:#ffffff"></lord-icon>
                            Accedi
                        </a>
                    </div>
                </div>
            </div>
            <!-- https://unsplash.com/it/foto/frutto-rotondo-rosso-su-terreno-marrone-hTKYAYwJoSQ -->
            <img src="${pageContext.request.contextPath}/common/assets/img/growing-plant.jpg" alt="">
        </div>
        <div class="section-separator"></div>
        <div id="products-container">
            <div id="products-preview-container">
                <div class="product-container">
                    <div class="product-thumbnail">
                        <img src="https://blog.dutch-bulbs.com/wp-content/uploads/2023/04/Peony-2.jpg" alt="">
                    </div>
                    <div class="product-title">
                        <p>Fiorellino rosa rubato, ce ne saranno altri</p>
                    </div>
                    <div class="product-price">
                        <p>19.99$</p>
                    </div>
                    <div class="product-actions">
                        <span class="material-icons md-18">add_shopping_cart</span>
                        <span class="material-icons md-18">favorite</span>
                    </div>
                </div>
            </div>
            <div class="section-separator"></div>
        </div>
    </div>
    <footer>
    </footer>
</body>
</html>