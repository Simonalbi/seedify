<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="edit-product">
  <div id="edit-product-main-container">
    <div id="edit-product-close-button" onclick="hideEditProduct()">
      <span class="material-icons-round md-18">close</span>
    </div>
    <form id="edit-product-form" method="post">
      <div class="form-row rubik-300">
        <div id="edit-product-data-container">
          <div id="edit-product-name-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Nome" />
              <jsp:param name="id" value="edit-product-name-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="name" />
              <jsp:param name="pattern" value="[A-Za-z\s]+" />
              <jsp:param name="errorMessage" value="Nome non valido" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
          <div id="edit-product-price-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Prezzo" />
              <jsp:param name="id" value="edit-product-price-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="price" />
              <jsp:param name="pattern" value="((0)|([1-9][0-9]*)).[0-9]{2}" />
              <jsp:param name="errorMessage" value="Prezzo non valido" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
          <div id="edit-product-quantity-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Quantit&agrave;" />
              <jsp:param name="id" value="edit-product-quantity-input-box" />
              <jsp:param name="type" value="number" />
              <jsp:param name="name" value="quantity" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
        </div>
        <div id="edit-product-info-container">
          <div id="edit-product-category-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Tipologia Pianta" />
              <jsp:param name="id" value="edit-product-category-input-box" />
              <jsp:param name="type" value="text" />
              <jsp:param name="name" value="category" />
              <jsp:param name="pattern" value="[A-Za-z\s]+" />
              <jsp:param name="errorMessage" value="Tipologia non valida" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
          <div id="edit-product-required-water-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Quantit&agrave; d'acqua:" />
              <jsp:param name="tag" value="select" />
              <jsp:param name="options" value="[(Poca, Value1) (Normale, Value2) (Tanta, Value3)]" />
              <jsp:param name="id" value="edit-product-required-water-input-box" />
              <jsp:param name="name" value="required-water" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
          <div id="edit-product-season-box" class="dark">
            <jsp:include page="/common/components/input-box/input-box.jsp">
              <jsp:param name="label" value="Stagionalit&agrave;:" />
              <jsp:param name="tag" value="select" />
              <jsp:param name="options" value="[(Primavera, Value1) (Estate, Value2) (Autunno, Value3) (Inverno, Value4)]" />
              <jsp:param name="id" value="edit-product-season-input-box" />
              <jsp:param name="name" value="season" />
              <jsp:param name="group" value="edit-product" />
            </jsp:include>
          </div>
        </div>
        <div id="edit-product-image-container">
          Clicca qui o trascina un'immagine per caricarla
          <input type="file" id="file-input" hidden />
          <div id="file-list"></div>
        </div>
      </div>
      <div id="edit-product-description-box" class="dark form-row rubik-300">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="label" value="Descrizione" />
          <jsp:param name="tag" value="textarea" />
          <jsp:param name="id" value="edit-product-description-input-box" />
          <jsp:param name="name" value="description" />
          <jsp:param name="placeholder" value="Descrivi il prodotto..." />
          <jsp:param name="errorMessage" value="Descrizione non valida" />
          <jsp:param name="group" value="edit-product" />
        </jsp:include>
      </div>
      <div id="edit-product-submit-box" class="dark rubik-400">
        <jsp:include page="/common/components/input-box/input-box.jsp">
          <jsp:param name="id" value="edit-product-submit-button" />
          <jsp:param name="type" value="submit" />
          <jsp:param name="value" value="Applica" />
          <jsp:param name="enabled" value="true" />
          <jsp:param name="group" value="edit-product" />
        </jsp:include>
      </div>
    </form>
  </div>
</div>
