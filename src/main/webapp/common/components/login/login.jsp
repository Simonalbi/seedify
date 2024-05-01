<jsp:include page="/common/general/metadata.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/components/login/style.css">

<div id="login-overlay">
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
          <div class="user-box">
            <label for="email-form">Email</label>
            <input id="email-form" type="email" name="email"/>
          </div>
          <div class="user-box">
            <label for="password-form">Password</label>
            <input id="password-form" type="password" name="password"/>
            <span id="password-toggle-icon" class="material-icons md-18">visibility_off</span>
          </div>
          <div class="user-box">
            <input type="submit" value="Accedi"/>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
