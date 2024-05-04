<jsp:include page="/common/components/forms/base-input-box.jsp"/>

<div class="input-box">
  <label for="${param.id}">${param.label}</label>
  <input id="${param.id}" type="password" name="${param.name}" placeholder="Min. 8 caratteri"/>
  <span id="${param.id}-toggle-icon" class="password-toggle-icon password-icon material-icons md-18" onClick="togglePassword('${param.id}')">visibility</span>
</div>
