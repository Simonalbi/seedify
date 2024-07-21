<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="edit-credit-card-overlay">
    <div id="edit-credit-card-main-container">
        <div id="edit-credit-card-close-button">
            <span class="material-icons-round md-18" onclick="hideCreditCardForm()">close</span>
        </div>
        <form id="edit-credit-card-form" action="${pageContext.request.contextPath}/credit-card-servlet?action=add_credit_card" method="post">
            <div class="form-row rubik-300">
                <div id="edit-credit-card-data-container" >
                    <div id="edit-credit-card-name-box" class="dark">
                        <jsp:include page="/common/components/input-box/input-box.jsp">
                            <jsp:param name="label" value="Nome:" />
                            <jsp:param name="id" value="edit-credit-card-name-input-box" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="name" value="name" />
                            <jsp:param name="pattern" value="[A-Za-z\s]+" />
                            <jsp:param name="errorMessage" value="Nome non valido" />
                            <jsp:param name="group" value="edit-credit-card" />
                        </jsp:include>
                    </div>
                    <div id="edit-credit-card-surname-box" class="dark">
                        <jsp:include page="/common/components/input-box/input-box.jsp">
                            <jsp:param name="label" value="Cognome:" />
                            <jsp:param name="id" value="edit-credit-card-surname-input-box" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="name" value="surname" />
                            <jsp:param name="pattern" value="[A-Za-z\s]+" />
                            <jsp:param name="errorMessage" value="Cognome non valido" />
                            <jsp:param name="group" value="edit-credit-card" />
                        </jsp:include>
                    </div>
                </div>
            </div>
            <div id="edit-credit-card-number-box" class="dark form-row rubik-300">
                <jsp:include page="/common/components/input-box/input-box.jsp">
                    <jsp:param name="label" value="Numero Carta:" />
                    <jsp:param name="id" value="edit-credit-card-number-input-box" />
                    <jsp:param name="type" value="text" />
                    <jsp:param name="name" value="credit_card_number" />
                    <jsp:param name="pattern" value="\d{16}" />
                    <jsp:param name="errorMessage" value="Numero carta non valido" />
                    <jsp:param name="group" value="edit-credit-card" />
                </jsp:include>
            </div>
            <div class="form-row rubik-300">
                <div id="edit-credit-card-info-container">
                    <div id="edit-credit-card-cvv-box" class="dark">
                        <jsp:include page="/common/components/input-box/input-box.jsp">
                            <jsp:param name="label" value="CVV:" />
                            <jsp:param name="id" value="edit-credit-card-cvv-input-box" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="name" value="cvv" />
                            <jsp:param name="pattern" value="\d{3}" />
                            <jsp:param name="errorMessage" value="CVV non valido" />
                            <jsp:param name="group" value="edit-credit-card" />
                        </jsp:include>
                    </div>
                    <div id="edit-credit-card-expiration-date-box" class="dark">
                        <jsp:include page="/common/components/input-box/input-box.jsp">
                            <jsp:param name="label" value="Data di Scadenza:" />
                            <jsp:param name="id" value="edit-credit-card-expiration-date-input-box" />
                            <jsp:param name="type" value="date" />
                            <jsp:param name="name" value="expiration_date" />
                            <jsp:param name="errorMessage" value="Data non valida" />
                            <jsp:param name="group" value="edit-credit-card" />
                        </jsp:include>
                    </div>
                </div>
            </div>
            <div id="edit-credit-card-submit-box" class="dark rubik-400">
                <jsp:include page="/common/components/input-box/input-box.jsp">
                    <jsp:param name="id" value="edit-credit-card-submit-button" />
                    <jsp:param name="type" value="submit" />
                    <jsp:param name="value" value="Salva" />
                    <jsp:param name="enabled" value="false" />
                    <jsp:param name="group" value="edit-credit-card" />
                </jsp:include>
            </div>
        </form>
    </div>
</div>