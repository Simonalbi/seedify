<jsp:include page="/common/general/metadata.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/components/input-box/style.css">

<script src="${pageContext.request.contextPath}/common/components/input-box/script.js" defer></script>

<div class="input-box">
  <% if (!"submit".equals(request.getParameter("type"))) { %>
    <label for="${param.id}">${param.label}</label>
    <input id="${param.id}" type="${param.type}" name="${param.name}" placeholder="${param.placeholder}"/>
    <% if ("password".equals(request.getParameter("type"))) { %>
      <span id="${param.id}-toggle-icon" class="password-toggle-icon password-icon material-icons md-18" onClick="togglePassword('${param.id}')">visibility</span>
    <% } %>
  <% } else { %>
    <input type="submit" value="${param.value}" <% if ("false".equals(request.getParameter("enabled"))) { %> disabled <% } %> />
  <% } %>
</div>
