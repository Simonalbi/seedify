<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>

<%
    String inputLabel = request.getParameter("label");
    String inputType = request.getParameter("type");
    String inputPlaceholder = request.getParameter("placeholder");
    String inputTag = request.getParameter("tag");
    String inputPattern = request.getParameter("pattern");
    String inputGroup = request.getParameter("group");
    String inputEnabled = request.getParameter("enabled");
    String inputRows = request.getParameter("rows");
    String inputCols = request.getParameter("cols");
    String inputErrorMessage = request.getParameter("errorMessage");

    String textAreaMaxLength = request.getParameter("textAreaMaxLength");

    HashMap<String, String> options = new HashMap<>();
    String rawInputOptions = request.getParameter("options");
    if (rawInputOptions != null) {
        Pattern pattern = Pattern.compile("\\(\\w+, \\w+\\)");
        Matcher matcher = pattern.matcher(rawInputOptions);
        while (matcher.find()) {
            String group = matcher.group();
            String[] rawParams = group.substring(1,group.length() - 1).split(",");
            options.put(rawParams[0].trim(), rawParams[1].trim());
        }
    }
%>

<div class="input-box">
  <% if (!"submit".equals(inputType)) { %>
    <div class="input-box-label-container">
      <label for="${param.id}"><%= inputLabel %></label>
    </div>
    <div class="input-box-input-container <% if ("select".equals(inputTag)) { %>select-dropdown<% } %>">
      <% if ("select".equals(inputTag)) { %>
        <select id="${param.id}"
                name="${param.name}"
                class="<% if (inputGroup != null) { %>${param.group}-input-box-group<% } %>">
                <% for (String optionName : options.keySet()) { %>
                  <option value="<%= options.get(optionName) %>"><%= optionName %></option>
                <% } %>
        </select>
      <% } else if ("textarea".equals(inputTag)) { %>
        <textarea id="${param.id}"
                  name="${param.name}"
                  class="<% if (inputGroup != null) { %>${param.group}-input-box-group<% } %>"
                  placeholder="<% if (inputPlaceholder != null) { %>${param.placeholder}<% } %>"
                  <% if (inputRows != null) { %>rows="${param.rows}"<% } %>
                  <% if (inputCols != null) { %>cols="${param.cols}"<% } %>
                  <% if (textAreaMaxLength != null) { %>maxlength="${param.textAreaMaxLength}"<% } %>>
        </textarea>
      <% } else if ("file".equals(inputTag)) { %>
        <div class="file-drag-and-drop-container">
          <span><%= inputPlaceholder %></span>
          <input id="${param.id}"
                 class="<% if (inputGroup != null) { %>${param.group}-input-box-group<% } %>"
                 type="file"
                 name="${param.name}"
                 hidden
          />
          <div id="${param.id}-file-list"></div>
        </div>
      <% } else if (inputTag == null) { %>
        <input id="${param.id}"
               class="<% if (inputGroup != null) { %>${param.group}-input-box-group<% } %>"
               type="<% if (inputType != null) { %>${param.type}<% } %>"
               name="${param.name}"
               placeholder="${param.placeholder}"
               <% if (inputPattern != null) { %>pattern="${param.pattern}"<% } %>
        />
        <% } if ("password".equals(inputType)) { %>
          <span id="${param.id}-toggle-icon" class="password-toggle-icon password-icon material-icons-round md-18" onClick="togglePassword('${param.id}')">visibility</span>
        <% } %>
    </div>
    <% if (!("file".equals(inputTag))) { %>
      <div class="input-box-error-container">
        <% if (inputErrorMessage != null) { %>
          <p class="error-text">${param.errorMessage}</p>
        <% } else { %>
          <br>
        <% } %>
      </div>
    <% } %>
  <% } else { %>
    <div class="input-box-input-container">
      <input id="${param.id}"
             class="<% if (inputGroup != null) { %>${param.group}-input-box-group<% } %>"
             type="submit"
             value="${param.value}"
             <% if ("false".equals(inputEnabled)) { %>disabled<% } %>
      />
    </div>
  <% } %>
</div>
