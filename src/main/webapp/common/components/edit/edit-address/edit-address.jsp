<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="edit-address-overlay">
  <div id="edit-address-main-container">
    <div id="edit-address-close-button">
      <span class="material-icons-round md-18" onclick="hideAddressOverlay()">close</span>
    </div>
    <form id="edit-address-form" action="${pageContext.request.contextPath}/address-servlet?action=add_address" method="post">
      <div id="edit-address-street-box" class="dark form-row rubik-300">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="label" value="Via" />
          <jsp:param name="id" value="edit-address-street-input-box" />
          <jsp:param name="type" value="text" />
          <jsp:param name="name" value="street" />
          <jsp:param name="pattern" value="[A-Za-z0-9\s]+" />
          <jsp:param name="errorMessage" value="Via non valida" />
          <jsp:param name="group" value="edit-address" />
        </jsp:include>
      </div>
      <div class="dark form-row rubik-300">
        <div id="edit-address-street-data-container">
          <div id="edit-address-city-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Citt&agrave" />
              <jsp:param name="id" value="edit-address-city-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="city" />
              <jsp:param name="pattern" value="[A-Za-z\s]+" />
              <jsp:param name="errorMessage" value="Citt&agrave; non valida" />
              <jsp:param name="group" value="edit-address" />
            </jsp:include>
          </div>
          <div id="edit-address-cap-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Cap" />
              <jsp:param name="id" value="edit-address-cap-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="zip_code" />
              <jsp:param name="pattern" value="[0-9]{5}" />
              <jsp:param name="errorMessage" value="Cap non valido" />
              <jsp:param name="group" value="edit-address" />
            </jsp:include>
          </div>
          <div id="edit-address-province-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Provincia" />
              <jsp:param name="id" value="edit-address-province-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="province" />
              <jsp:param name="pattern" value="[A-Z]{2}" />
              <jsp:param name="errorMessage" value="Provincia non valida" />
              <jsp:param name="group" value="edit-address" />
            </jsp:include>
          </div>
        </div>
      </div>
      <div class="dark form-row rubik-300">
        <div id="edit-address-user-info-container">
          <div id="edit-address-first-name-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Nome" />
              <jsp:param name="id" value="edit-address-first-name-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="first_name" />
              <jsp:param name="pattern" value="[A-Za-z\s]+" />
              <jsp:param name="errorMessage" value="Nome non valido" />
              <jsp:param name="group" value="edit-address" />
            </jsp:include>
          </div>
          <div id="edit-address-last-name-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Cognome" />
              <jsp:param name="id" value="edit-address-surname-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="last_name" />
              <jsp:param name="pattern" value="[A-Za-z\s]+" />
              <jsp:param name="errorMessage" value="Cognome non valido" />
              <jsp:param name="group" value="edit-address" />
            </jsp:include>
          </div>
        </div>
      </div>
      <div id="edit-address-phone-box" class="dark form-row rubik-300">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="label" value="Telefono" />
          <jsp:param name="id" value="edit-address-phone-input-box" />
          <jsp:param name="type" value="text" />
          <jsp:param name="name" value="phone" />
          <jsp:param name="pattern" value="\+39\d{10}" />
          <jsp:param name="errorMessage" value="Telefono non valido" />
          <jsp:param name="group" value="edit-address" />
        </jsp:include>
      </div>
      <div id="edit-address-notes-box" class="dark form-row rubik-300">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="label" value="Note" />
          <jsp:param name="tag" value="textarea" />
          <jsp:param name="id" value="edit-address-notes-input-box" />
          <jsp:param name="name" value="notes" />
          <jsp:param name="placeholder" value="Aggiungi delle note..." />
          <jsp:param name="errorMessage" value="Note non valide" />
          <jsp:param name="group" value="edit-address" />
          <jsp:param name="textAreaMaxLength" value="150" />
        </jsp:include>
      </div>
      <div id="edit-address-submit-box" class="dark rubik-400">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="id" value="edit-address-submit-button" />
          <jsp:param name="type" value="submit" />
          <jsp:param name="value" value="Salva" />
          <jsp:param name="enabled" value="false" />
          <jsp:param name="group" value="edit-address" />
        </jsp:include>
      </div>
    </form>
  </div>
</div>