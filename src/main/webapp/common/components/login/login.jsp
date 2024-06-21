<div id="login" style="display: none">
  <div id="login-main-container">
    <div id="login-left-container">
      <img src="${pageContext.request.contextPath}/common/assets/img/login_background.jpg"/>
    </div>
    <div id="login-right-container">
      <lord-icon id="animated-bird" src="https://cdn.lordicon.com/xzbjjswf.json" trigger="loop" colors="primary:#b5d73c,secondary:#FFFFFF"></lord-icon>
      <div id="login-form-container">
        <div id="login-logo-container">
          <img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300.png"/>
        </div>
        <div id="login-boxes-container" >
          <form action="${pageContext.request.contextPath}/login-servlet" method="post">
            <div id="login-email-box" class="rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Email" />
                <jsp:param name="id" value="login-email-input-box" />
                <jsp:param name="type" value="email" />
                <jsp:param name="name" value="email" />
                <jsp:param name="placeholder" value="nome@email.com" />
                <jsp:param name="pattern" value="[a-z0-9.]+@[a-z0-9.]+\.[a-z]+" />
                <jsp:param name="errorMessage" value="Email non valida" />
                <jsp:param name="group" value="login" />
              </jsp:include>
            </div>
            <div id="login-password-box" class="rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Password" />
                <jsp:param name="id" value="login-password-input-box" />
                <jsp:param name="type" value="password" />
                <jsp:param name="name" value="password" />
                <jsp:param name="group" value="login" />
              </jsp:include>
            </div>
            <div id="login-submit-box" class="rubik-400">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="id" value="login-submit-button" />
                <jsp:param name="type" value="submit" />
                <jsp:param name="value" value="Accedi" />
                <jsp:param name="enabled" value="false" />
                <jsp:param name="group" value="login" />
              </jsp:include>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
