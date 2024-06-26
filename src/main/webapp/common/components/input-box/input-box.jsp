<div class="input-box">
  <% if (!"submit".equals(request.getParameter("type"))) { %>
    <div class="input-box-label-container">
      <label for="${param.id}">${param.label}</label>
    </div>
    <div class="input-box-input-container">
      <input id="${param.id}"
             class="<% if (request.getParameter("group") != null) { %>${param.group}-input-box-group<% } %>"
             type="${param.type}"
             name="${param.name}"
             placeholder="${param.placeholder}"
             <% if (request.getParameter("pattern") != null) { %> pattern="${param.pattern}" <% } %>
      />
      <% if ("password".equals(request.getParameter("type"))) { %>
        <span id="${param.id}-toggle-icon" class="password-toggle-icon password-icon material-icons-round md-18" onClick="togglePassword('${param.id}')">visibility</span>
      <% } %>
    </div>
    <div class="input-box-error-container">
      <% if (request.getParameter("errorMessage") != null) { %>
        <p class="error-text">${param.errorMessage}</p>
      <% } else { %>
        <br>
      <% } %>
    </div>
  <% } else { %>
    <div class="input-box-input-container">
      <input id="${param.id}"
             class="<% if (request.getParameter("group") != null) { %>${param.group}-input-box-group<% } %>"
             type="submit"
             value="${param.value}"
             <% if ("false".equals(request.getParameter("enabled"))) { %> disabled <% } %>
      />
    </div>
  <% } %>
</div>
