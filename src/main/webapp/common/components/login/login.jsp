<jsp:include page="/common/general/metadata.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/components/login/style.css">

<script src="${pageContext.request.contextPath}/common/components/login/script.js" defer></script>

<div id="login-overlay" style="display: none">
  <div id="login-main-container">
    <div id="image-container">
      <img src="${pageContext.request.contextPath}/common/assets/img/login-background.jpg"/>
    </div>
    <div id="login-container">
      <lord-icon id="animated-bird" src="https://cdn.lordicon.com/xzbjjswf.json" trigger="loop" colors="primary:#b5d73c,secondary:#FFFFFF"></lord-icon>
      <div id="logo-container">
        <img src="${pageContext.request.contextPath}/common/assets/img/logo/horizontal-logo.png"/>
      </div>
      <div id="form-container">
        <form action="#" method="post">
          <div id="email-box">
            <jsp:include page="/common/components/forms/email-input-box.jsp">
              <jsp:param name="labelColor" value="#398301" />
            </jsp:include>
          </div>
          <div id="password-box">
            <jsp:include page="/common/components/forms/password-input-box.jsp">
              <jsp:param name="labelColor" value="#398301" />
            </jsp:include>
          </div>
          <div id="submit-box">
            <jsp:include page="/common/components/forms/submit-input-box.jsp">
              <jsp:param name="value" value="Accedi" />
            </jsp:include>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
