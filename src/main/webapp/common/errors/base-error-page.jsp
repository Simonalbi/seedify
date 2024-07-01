<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/common/general/metadata.jsp"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/common/errors/styles/style.css">
</head>
<body>
<div class="page">
  <div class="error-container">
    <div id="logo-container">
      <img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300_small.png" alt="">
    </div>
    <div class="message-container">
      <div class="question rubik-500">
        <h4>${param.errorMessage}</h4>
      </div>
      <div class="message rubik-400">
        <p>${param.errorDescription}</p>
      </div>
      <div class="redirect-message rubik-400">
        <span class="material-icons-round md-18">chevron_right</span>
        <span class="span-text"><b><a href="${pageContext.request.contextPath}/home/home.jsp">Clicca qui</a> per ritornare alla homepage di Seedify!</b></span>
      </div>
    </div>
  </div>
</div>

</body>
</html>
