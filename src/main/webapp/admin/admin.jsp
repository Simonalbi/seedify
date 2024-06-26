<%@ page import="com.unisa.seedify.model.UserBean" %>
<%@ page import="com.unisa.seedify.model.UserDao" %>
<%@ page import="com.unisa.seedify.model.OrderDao" %>
<%@ page import="com.unisa.seedify.model.ProductDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- TODO Filter page access -->
<!-- TODO Navbar style -->
<!-- TODO Replace stats and other data with real data -->

<%
  UserBean userBean = (UserBean) request.getSession().getAttribute("user");

  UserDao userDao = UserDao.getInstance();
  OrderDao orderDao = OrderDao.getInstance();
  ProductDao productDao = ProductDao.getInstance();
%>

<html>
  <head>
    <jsp:include page="/common/general/metadata.jsp"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/styles/style.css">

    <script src="${pageContext.request.contextPath}/admin/scripts/script.js" defer></script>
  </head>
  <body>
    <%@ include file="/common/components/main-navbar/main-navbar.jsp" %>
    <div class="main-page-content">
      <div id="info-container">
        <div class="ui-block" id="welcome-message-container">
          <div id="profile-picture-container">
            <div class="profile-picture">
              <img src="${pageContext.request.contextPath}/resources-servlet?resourceType=profile_picture" alt="Foto profilo">
            </div>
          </div>
          <div id="admin-message-container">
            <span class="rubik-400" id="admin-message">Ciao <%= userBean.getName() %> benvenuto nella admin dashboard!</span>
          </div>
        </div>
        <div class="ui-block" id="stats-container">
          <div id="stats-title">
            <h5 class="rubik-400">Statistiche</h5>
          </div>
          <div id="stats">
            <div class="stat">
              <span class="material-icons-round md-18">badge</span>
              <span class="rubik-300">Dipendenti: <span class="stat-value rubik-400"><%= userDao.getEmployeesAmount() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">people</span>
              <span class="rubik-300">Utenti: <span class="stat-value rubik-400"><%= userDao.getUsersAmount() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">local_shipping</span>
              <span class="rubik-300">Ordini: <span class="stat-value rubik-400"><%= orderDao.getOrdersAmount() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">inventory_2</span>
              <span class="rubik-300">Prodotti: <span class="stat-value rubik-400"><%= productDao.getProductsAmount() %></span></span>
            </div>
          </div>
        </div>
      </div>
      <div class="ui-block" id="table-container">
        <nav id="main-table-navbar">
          <select name="table-selector" id="table-selector" onchange="getTableData()">
            <option value="employees">Dipendenti</option>
            <option value="users">Utenti</option>
            <option value="orders">Ordini</option>
            <option value="products">Prodotti</option>
          </select>
          <div id="search-container">
            <input type="text" name="search" id="search-text" placeholder="Cerca...">
            <span class="material-icons-round md-18">search</span>
          </div>
        </nav>
        <div id="main-table">
          <div id="table-loading-overlay">
            <img src="${pageContext.request.contextPath}/common/assets/img/loader.svg">
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
