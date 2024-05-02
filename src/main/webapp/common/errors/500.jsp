<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>500</title>

  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/common/errors/style.css">
</head>
<body>
  <div class="page">
    <div class="error-container">
      <div id="logo-container">
        <img src="${pageContext.request.contextPath}/common/assets/img/logo/horizontal-logo-small.png" alt="">
      </div>

      <div class="message-container">
        <div class="question">
          <h3>Ci dispiace!</h3>
        </div>
        <div class="message">
          <p>Si è verificato un errore quando abbiamo tentato di elaborare la richiesta.
            Stiamo lavorando al problema e ci aspettiamo di risolverlo a breve. Si prega
            di notare che se stavi effettuando un ordine, non sarà stato elaborato per il
            momento. Per favore riprova più tardi.<br>
            Ci scusiamo per l'inconveniente.
          </p>
        </div>
        <div class="redirect">
          <span class="material-icons md-18">chevron_right</span>
          <p><b><a href="${pageContext.request.contextPath}/home/home.jsp">Clicca qui</a> per ritornare alla homepage di Seedify!</b></p>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
