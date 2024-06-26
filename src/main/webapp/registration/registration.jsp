<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/registration/styles/style.css">

  <script src="${pageContext.request.contextPath}/registration/scripts/script.js" defer></script>
</head>
<body>
<jsp:include page="/common/components/login/login.jsp"/>

<div id="main-container">
  <div id="left-container">
    <div id="slogan-container" class="oleo-400">
      <h6 class="rubik-300">Il tuo giardino virtuale inizia qui!</h6>
    </div>
    <div id="image-container">
      <!-- https://unsplash.com/it/foto/donna-in-t-shirt-girocollo-nera-che-tiene-pianta-verde-I8fwQMjR67M -->
      <img id="background-image" src="${pageContext.request.contextPath}/common/assets/img/planting.jpg" alt="">
      <img id="logo" src="${pageContext.request.contextPath}/common/assets/img/logo/logo_500x500.png" alt="">
    </div>
  </div>
  <div id="left-container-placeholder"></div>
  <div id="right-container">
    <div id="registration-container">
      <div id="main-title-container">
        <div id="title-container">
          <h3 class="rubik-600">Registrati su Seedify</h3>
        </div>
        <div id="subtitle-container">
          <p class="rubik-300">Hai già un account? <span id="login-button" onclick="showLogin()">Accedi</span></p>
        </div>
      </div>
      <div id="registration-form-container">
        <form action="${pageContext.request.contextPath}/registration-servlet" method="POST">
          <div id="registration-boxes-container">
            <div id="registration-name-box" class="dark rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Nome" />
                <jsp:param name="id" value="registration-name-input-box" />
                <jsp:param name="type" value="text" />
                <jsp:param name="name" value="name" />
                <jsp:param name="pattern" value="[A-Za-z\s]+" />
                <jsp:param name="errorMessage" value="Nome non valido" />
                <jsp:param name="group" value="registration" />
              </jsp:include>
            </div>
            <div id="registration-surname-box" class="dark rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Cognome" />
                <jsp:param name="id" value="registration-surname-input-box" />
                <jsp:param name="type" value="text" />
                <jsp:param name="name" value="surname" />
                <jsp:param name="pattern" value="[A-Za-z\s]+" />
                <jsp:param name="errorMessage" value="Cognome non valido" />
                <jsp:param name="group" value="registration" />
              </jsp:include>
            </div>
            <div id="registration-email-box" class="dark rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Email" />
                <jsp:param name="id" value="registration-email-input-box" />
                <jsp:param name="type" value="email" />
                <jsp:param name="name" value="email" />
                <jsp:param name="placeholder" value="nome@email.com" />
                <jsp:param name="pattern" value="[a-z0-9.]+@[a-z0-9.]+\.[a-z]+" />
                <jsp:param name="errorMessage" value="Email non valida" />
                <jsp:param name="group" value="registration" />
              </jsp:include>
            </div>
            <div id="registration-password-box" class="dark rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Password" />
                <jsp:param name="id" value="registration-password-input-box" />
                <jsp:param name="type" value="password" />
                <jsp:param name="name" value="password" />
                <jsp:param name="placeholder" value="Min. 8 caratteri" />
                <jsp:param name="group" value="registration" />
              </jsp:include>
            </div>
            <div id="registration-password-confirmation-box" class="dark rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Conferma password" />
                <jsp:param name="id" value="registration-password-confirmation-input-box" />
                <jsp:param name="type" value="password" />
                <jsp:param name="name" value="password-confirmation" />
                <jsp:param name="placeholder" value="Min. 8 caratteri" />
                <jsp:param name="group" value="registration" />
                <jsp:param name="errorMessage" value="Le password non corrispondono" />
              </jsp:include>
            </div>
          </div>
          <div id="submit-container">
            <div id="password-security-level-container">
              <div id="security-details-container">
                <p id="details-title" class="large-p rubik-500">Crea una password sicura!</p>
                <div id="length-constraint" class="detail">
                  <p class="small-p rubik-400">
                    <span class="material-icons-round md-18 incomplete-step">close</span>
                    Utilizza almeno 8 caratteri
                  </p>
                </div>
                <div id="letters-constraint" class="detail">
                  <p class="small-p rubik-400">
                    <span class="material-icons-round md-18 incomplete-step">close</span>
                    Utilizza lettere maiuscole e minuscole
                  </p>
                </div>
                <div id="number-constraint" class="detail">
                  <p class="small-p rubik-400">
                    <span class="material-icons-round md-18 incomplete-step">close</span>
                    Utilizza almeno un numero
                  </p>
                </div>
                <div id="special-characters-constraint" class="detail">
                  <p class="small-p rubik-400">
                    <span class="material-icons-round md-18 incomplete-step">close</span>
                    Utilizza almeno un carattere speciale tra $, #, &, ., %, !, *, @
                  </p>
                </div>
                <div id="match-constraint" class="detail">
                  <p class="small-p rubik-400">
                    <span class="material-icons-round md-18 incomplete-step">close</span>
                    Le password corrispondono
                  </p>
                </div>
              </div>
              <div id="animation-container">
                <div id="sunflower-container">
                  <lord-icon id="sunflower" src="https://cdn.lordicon.com/ucjpmssl.json" trigger="loop"></lord-icon>
                </div>
                <div id="dirt-container">
                  <div id="left-dirt" class="dirt"></div>
                  <div id="right-dirt" class="dirt"></div>
                </div>
              </div>
            </div>
            <div id="submit-row">
              <div id="submit-box" class="dark rubik-400">
                <jsp:include page="/common/components/input-box/input-box.jsp">
                  <jsp:param name="id" value="registration-submit-button" />
                  <jsp:param name="type" value="submit" />
                  <jsp:param name="value" value="Registrati" />
                  <jsp:param name="enabled" value="false" />
                  <jsp:param name="group" value="registration" />
                </jsp:include>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
