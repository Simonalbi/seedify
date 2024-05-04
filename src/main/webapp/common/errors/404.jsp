<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error 404</title>

  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/common/errors/style.css">
</head>
<body>
  <div class="page">
    <div class="error-container">
      <div id="logo-container">
        <img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300_small.png" alt="">
      </div>
      <div class="message-container">
        <div class="question">
          <h3>Cerchi qualcosa in particolare?</h3>
        </div>
        <div class="message">
          <p> Ci dispiace. L'indirizzo Web inserito corrisponde a una pagina non funzionante del nostro sito.</p>
        </div>
        <div class="redirect">
          <span class="material-icons md-18">chevron_right</span>
          <p><b>Vai alla <a href="${pageContext.request.contextPath}/home/home.jsp">home page</a> di Seedify!</b></p>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
