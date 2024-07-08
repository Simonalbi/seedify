<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer>
    <div id="main-footer" class="rubik-300">
        <div class="footer-section">
            <span class="footer-section-title rubik-600">Contatti</span>
            <div class="footer-section-content">
                <div class="footer-section-row">
                    <span class="material-icons-round">email</span>
                    <span>info@seedify.it</span>
                </div>
                <div class="footer-section-row">
                    <span class="material-icons-round">call</span>
                    <span>+39 123 456 7890</span>
                </div>
                <div class="footer-section-row">
                    <span class="material-icons-round">home_work</span>
                    <span>Via delle Piante 10, Salerno (SA), 84010, Campania, Italia</span>
                </div>
            </div>
        </div>
        <div class="footer-section">
            <span class="footer-section-title rubik-600">Link utili</span>
            <div id="useful-links" class="footer-section-content">
                <div class="footer-section-row">
                    <a href="${pageContext.request.contextPath}/home">
                        <span class="material-icons-round">home</span>
                        <span>Home</span>
                    </a>
                </div>
                <div class="footer-section-row">
                    <a href="${pageContext.request.contextPath}/products-page">
                        <span class="material-icons-round">shopping_bag</span>
                        <span>Prodotti</span>
                    </a>
                </div>
                <div class="footer-section-row">
                    <a href="${pageContext.request.contextPath}/about-us">
                        <span class="material-icons-round">info_outline</span>
                        <span>Chi Siamo</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-break-line"></div>
    <div id="sub-footer"  class="rubik-300">
        <div id="footer-copyright-container">
            <div id="footer-logo-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300.png" alt="Seedify Logo">
            </div>
            <span id="footer-copyright-text">&copy; 2024 Seedify. Tutti i diritti riservati.</span>
        </div>
        <div id="footer-social-container">
            <a href="#">
                <img src="${pageContext.request.contextPath}/common/assets/img/socials/instagram.svg" alt="Instagram">
            </a>
            <a href="#">
                <img src="${pageContext.request.contextPath}/common/assets/img/socials/facebook.svg" alt="Facebook">
            </a>
            <a href="#">
                <img src="${pageContext.request.contextPath}/common/assets/img/socials/x.svg" alt="X">
            </a>
            <a href="#">
                <img src="${pageContext.request.contextPath}/common/assets/img/socials/linkedin.svg" alt="Linkedin">
            </a>
        </div>
    </div>
</footer>
