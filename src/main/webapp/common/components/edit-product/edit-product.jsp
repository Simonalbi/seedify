<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Modifica</title>
</head>
<body>
  <div id="edit-product-outline">
    <div id="edit-product-main-container">
      <div id="popup-header-container">
        <div class="title-popup rubik-400">
          <h4>Modifica Prodotto</h4>
        </div>
        <div class="close-button">
          <span class="material-icons-round md-18">close</span>
        </div>
      </div>
      <form id="edit-input-box">
        <div id="all-data-product" class="rubik-300">
          <div id="data-container">
            <div id="product-edit-name-box">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Nome" />
                <jsp:param name="id" value="product-edit-name-input-box" />
                <jsp:param name="type" value="text" />
                <jsp:param name="name" value="name" />
                <jsp:param name="pattern" value="[A-Za-z\s]+" />
                <jsp:param name="errorMessage" value="Nome non valido" />
                <jsp:param name="group" value="product-edit" />
              </jsp:include>
            </div>
            <div id="product-edit-price-box">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Prezzo" />
                <jsp:param name="id" value="product-edit-price-input-box" />
                <jsp:param name="type" value="text" />
                <jsp:param name="name" value="price" />
                <jsp:param name="pattern" value="((0)|([1-9][0-9]*)).[0-9]{2}" />
                <jsp:param name="errorMessage" value="Prezzo non valido" />
                <jsp:param name="group" value="product-edit" />
              </jsp:include>
            </div>
            <div id="product-edit-quantity-box">
              <jsp:include page="/common/components/input-box/input-box.jsp">
                <jsp:param name="label" value="Quantit&agrave;" />
                <jsp:param name="id" value="product-edit-quantity-input-box" />
                <jsp:param name="type" value="number" />
                <jsp:param name="name" value="quantity" />
                <jsp:param name="group" value="product-edit" />
              </jsp:include>
            </div>
          </div>
          <div id="info-container">
            <div id="product-edit-category-box">
                <jsp:include page="/common/components/input-box/input-box.jsp">
                  <jsp:param name="label" value="Tipologia Pianta" />
                  <jsp:param name="id" value="product-edit-quantity-input-box" />
                  <jsp:param name="type" value="text" />
                  <jsp:param name="name" value="category" />
                  <jsp:param name="pattern" value="[A-Za-z\s]+" />
                  <jsp:param name="errorMessage" value="Tipologia non valida" />
                  <jsp:param name="group" value="product-edit" />
                </jsp:include>
            </div>
            <div id="product-water-quantity-container">
              <div class="label-container">
                <label>Quantità d'acqua:</label>
              </div>
              <div class="input-edit-input-container">
                <select id="product-water-quantity">
                  <option value="poca">Poca</option>
                  <option value="normale">Normale</option>
                  <option value="tanta">Tanta</option>
                </select>
              </div>
            </div>
            <div id="product-season-container">
              <div class="label-container">
                <label>Stagionalità:</label>
              </div>
              <div class="input-edit-input-container">
                <select id="product-season">
                  <option value="PRIMAVERA">Primavera</option>
                  <option value="ESTATE">Estate</option>
                  <option value="AUTUNNO">Autunno</option>
                  <option value="INVERNO">Inverno</option>
                </select>
              </div>
            </div>
          </div>
          <div id="drop-zone-container">
            Trascina qui il file o clicca per selezionarlo
            <input type="file" id="file-input" hidden>
            <div id="file-list"></div>
          </div>
        </div>
        <div id="product-edit-description-box" class="rubik-300">
          <div class="label-container">
            <label>Descrizione:</label>
          </div>
          <textarea name="product-description" id="product-description-text" placeholder="Inserisci una descrizione..."></textarea>
        </div>
        <!--</div>
        <div id="product-description-container">
          <div class="input-edit-input-container">

          <div class="input-box-error-container">
              <span class="error-text">Descrizione non valida</span>
          </div>
        </div>-->
        <div id="submit-container" class="dark rubik-400">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="id" value="product-edit-submit-button" />
              <jsp:param name="type" value="submit" />
              <jsp:param name="value" value="Applica" />
              <jsp:param name="enabled" value="false" />
              <jsp:param name="group" value="product-edit" />
            </jsp:include>
        </div>
      </form>
    </div>
  </div>

</body>
</html>
