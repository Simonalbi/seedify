<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.unisa.seedify.model.UserBean" %>
<%@ page import="com.unisa.seedify.model.UserDao" %>
<%@ page import="com.unisa.seedify.model.OrderDao" %>
<%@ page import="com.unisa.seedify.model.ProductDao" %>

<!-- TODO Filter page access -->
<!-- TODO Navbar style -->
<%
  UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");

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
              <span class="rubik-300">Dipendenti: <span class="stat-value rubik-400"><%= userDao.getTotalEmployees() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">people</span>
              <span class="rubik-300">Utenti: <span class="stat-value rubik-400"><%= userDao.getTotalCustomers() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">local_shipping</span>
              <span class="rubik-300">Ordini: <span class="stat-value rubik-400"><%= orderDao.getTotalOrders() %></span></span>
            </div>
            <div class="stat">
              <span class="material-icons-round md-18">inventory_2</span>
              <span class="rubik-300">Prodotti: <span class="stat-value rubik-400"><%= productDao.getTotalProducts() %></span></span>
            </div>
          </div>
        </div>
      </div>
      <div class="ui-block" id="table-container">
        <nav id="main-table-navbar">
          <%-- TODO Add ordini effettuati all'utente --%>
          <%-- TODO Concatenare dati indirizzo --%>
          <%-- TODO Visualizzare l'immagine restituita del prodotto --%>
          <select name="table-selector" id="table-selector" onchange="getTableData()">
            <option value="get_employees-nome,cognome,email">Dipendenti</option>
            <option value="get_customers-nome,cognome,email">Utenti</option>
            <option value="get_orders-id_ordine,utente.email,data_ordine,data_consegna,carta_di_credito.numero_di_carta,indirizzo.città,indirizzo.provincia,indirizzo.cap,indirizzo.via,indirizzo.telefono,indirizzo.note">Ordini</option>
            <option value="get_products-nome,prezzo,quantità,stagione,acqua_richiesta,tipologia,descrizione">Prodotti</option>
          </select>
            <%-- TODO Inserire ajax per la ricerca --%>
          <div id="search-container">
            <input type="text" name="search" id="search-text" placeholder="Cerca...">
            <span class="material-icons-round md-18">search</span>
          </div>
        </nav>
        <div id="main-table">
          <div id="table-loading-overlay">
            <img src="${pageContext.request.contextPath}/common/assets/img/loader.svg" alt="Caricamento...">
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
