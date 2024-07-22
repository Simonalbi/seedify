<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <jsp:include page="/common/general/metadata.jsp"/>
    <title>La Nostra Storia - Seedify</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/about-us/styles/style.css">

    <script type="module" src="${pageContext.request.contextPath}/about-us/scripts/script.js" defer></script>

</head>
<body>
    <jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

    <div id="about-us-main-container">
        <div id="about-us-decorations-container">
            <div class="dec-row dec-row-left">
                <div class="dec dec-royal-green dec-1"></div>
            </div>
            <div class="dec-row dec-row-right">
                <div class="dec dec-mughal-green dec-2"></div>
            </div>
            <div class="dec-row dec-row-right">
                <div class="dec dec-sap-green dec-6"></div>
            </div>
            <div class="dec-row dec-row-left">
                <div class="dec dec-android-green dec-2"></div>
            </div>
            <div class="dec-row dec-row-left">
                <div class="dec dec-sap-green dec-6"></div>
            </div>
            <div class="dec-row dec-row-right">
                <div class="dec dec-royal-green dec-4"></div>
            </div>
            <div class="dec-row dec-row-left">
                <div class="dec dec-mughal-green dec-4"></div>
            </div>
        </div>
        <div id="about-us-text-container" class="rubik-400">
            <div id="about-us-logo-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300.png" alt="Immagine di un giardino botanico">
            </div>
            <h4>La Nostra Storia</h4>
            <p>
                Benvenuti su Seedify, il nostro sito dedicato alla vendita di piante e fiori! L'idea del nostro sito web è nata dalla passione per la natura e dal desiderio di rendere accessibili a tutti le meraviglie del mondo vegetale. Con anni di esperienza nel settore del giardinaggio e un amore profondo per le piante, abbiamo deciso di creare uno spazio dove chiunque possa trovare la pianta perfetta per la propria casa o il proprio giardino.
            </p>

            <div class="about-us-section-title-breakline"></div>

            <h4>Come è nata l'idea</h4>
            <p>
                Tutto è iniziato durante una passeggiata in un giardino botanico, dove siamo stati ispirati dalla varietà e dalla bellezza delle piante esposte. Ci siamo resi conto di quanto sia importante per il benessere delle persone avere un contatto diretto con la natura. Da qui, è nata l'idea di creare Seedify, una piattaforma che permetta di portare un pezzetto di natura nella vita quotidiana di ognuno.
            </p>
            <div class="about-us-image-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/garden.jpg" alt="Immagine di un giardino botanico">
            </div>

            <div class="about-us-section-title-breakline"></div>

            <h4>Il nostro obiettivo</h4>
            <p>
                Il nostro obiettivo con Seedify è fornire una vasta gamma di piante e fiori di alta qualità, accompagnati da consigli esperti per la loro cura. Vogliamo che ogni cliente trovi esattamente ciò di cui ha bisogno, che si tratti di una pianta da appartamento facile da gestire o di un fiore raro e speciale per un'occasione unica.
            </p>
            <div class="about-us-image-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/botanic_collection.jpg" alt="Immagine di diverse piante e fiori">
            </div>

            <div class="about-us-section-title-breakline"></div>

            <h4>Cosa facciamo</h4>
            <p>
                Su Seedify, troverete una selezione accurata di piante da interno ed esterno, fiori recisi e composizioni floreali. Offriamo inoltre guide dettagliate e consigli su come prendersi cura delle vostre piante, garantendo che ogni acquisto diventi una lunga e felice esperienza verde.
            </p>
            <div class="about-us-image-container">
                <img src="${pageContext.request.contextPath}/common/assets/img/welcome.jpg" alt="Immagine di una composizione floreale">
            </div>

            <div class="about-us-section-title-breakline"></div>
            <p class="rubik-500">
                Grazie per aver visitato Seedify! Siamo entusiasti di condividere con voi la nostra passione per le piante e di aiutarvi a trasformare i vostri spazi con un tocco di verde.
            </p>
        </div>
    </div>

    <jsp:include page="/common/components/main-footer/main-footer.jsp"/>
</body>
</html>
