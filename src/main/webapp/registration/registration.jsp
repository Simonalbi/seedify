<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/registration/style.css">

  <script src="${pageContext.request.contextPath}/registration/script.js" defer></script>
</head>
<body>
<div id="main-container">
  <div id="left-container">
    <div id="slogan-container" class="oleo-400">
      <p class="rubik-300">Il tuo giardino virtuale inizia qui!</p>
    </div>
    <div id="image-container">
      <!-- https://unsplash.com/it/foto/donna-in-t-shirt-girocollo-nera-che-tiene-pianta-verde-I8fwQMjR67M -->
      <img id="background-image" src="${pageContext.request.contextPath}/common/assets/img/planting.jpg" alt="">
      <img id="logo" src="${pageContext.request.contextPath}/common/assets/img/logo/logo_500x500.png" alt="">
    </div>
  </div>
  <div id="right-container">
    <div id="registration-container">
      <div id="title-container">
        <h1 class="rubik-600">Registrati su Seedify</h1>
      </div>
      <div id="subtitle-container">
        <p class="rubik-300">Hai già un account? <a href="${pageContext.request.contextPath}/home/home.jsp">Accedi</a></p>
      </div>
      <div id="form-container">
        <form action="#" method="post">
          <div class="form-row">
            <div id="name-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/text-input-box.jsp">
                <jsp:param name="id" value="name-input-box" />
                <jsp:param name="name" value="name" />
                <jsp:param name="label" value="Nome" />
              </jsp:include>
            </div>
            <div id="surname-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/text-input-box.jsp">
                <jsp:param name="id" value="surname-input-box" />
                <jsp:param name="name" value="surname" />
                <jsp:param name="label" value="Cognome" />
              </jsp:include>
            </div>
          </div>
          <div class="form-row">
            <div id="email-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/email-input-box.jsp">
                <jsp:param name="id" value="email-input-box" />
                <jsp:param name="name" value="email" />
                <jsp:param name="label" value="Email" />
              </jsp:include>
            </div>
          </div>
          <div class="form-row">
            <div id="password-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/password-input-box.jsp">
                <jsp:param name="id" value="password-input-box" />
                <jsp:param name="name" value="password" />
                <jsp:param name="label" value="Password" />
              </jsp:include>
            </div>
            <div id="password-confirmation-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/password-input-box.jsp">
                <jsp:param name="id" value="password-confirmation-input-box" />
                <jsp:param name="name" value="password-confirmation" />
                <jsp:param name="label" value="Conferma password" />
              </jsp:include>
            </div>
          </div>
          <div id="password-security-level-container">
            <div id="security-details-container">
              <p id="details-title" class="rubik-500">Crea una password sicura!</p>
              <div id="length-constraint" class="detail">
                <p class="rubik-400">
                  <span class="material-icons md-18 incomplete-step">close</span>
                  Utilizza almeno 8 caratteri
                </p>
              </div>
              <div id="letters-constraint" class="detail">
                <p class="rubik-400">
                  <span class="material-icons md-18 incomplete-step">close</span>
                  Utilizza lettere maiuscole e minuscole
                </p>
              </div>
              <div id="number-constraint" class="detail">
                <p class="rubik-400">
                  <span class="material-icons md-18 incomplete-step">close</span>
                  Utilizza almeno un numero
                </p>
              </div>
              <div id="special-characters-constraint" class="detail">
                <p class="rubik-400">
                  <span class="material-icons md-18 incomplete-step">close</span>
                  Utilizza almeno un carattere speciale tra $, #, &, ., %, !, *, @
                </p>
              </div>
              <div id="match-constraint" class="detail">
                <p class="rubik-400">
                  <span class="material-icons md-18 incomplete-step">close</span>
                  Le password corrispondono
                </p>
              </div>
            </div>
            <div id="animation-container">
              <div class="dirt-container">
                <div class="dirt left-dirt"></div>
                <div class="dirt right-dirt"></div>
                <lord-icon id="sunflower" src="https://cdn.lordicon.com/ucjpmssl.json" trigger="loop"></lord-icon>
              </div>
            </div>
          </div>
          <div id="submit-row">
            <div id="submit-box" class="dark rubik-400">
              <jsp:include page="/common/components/forms/submit-input-box.jsp">
                <jsp:param name="value" value="Registrati" />
              </jsp:include>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
