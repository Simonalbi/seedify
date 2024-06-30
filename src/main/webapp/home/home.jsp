<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.unisa.seedify.model.UserBean" %>

<%
	UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
%>

<html lang="en">
<head>
	<jsp:include page="/common/general/metadata.jsp"/>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/home/styles/style.css">

	<script type="module" src="${pageContext.request.contextPath}/home/scripts/script.js" defer></script>
</head>
<body>
	<jsp:include page="/common/components/login/login.jsp"/>
	<jsp:include page="/common/components/main-navbar/main-navbar.jsp"/>

	<div id="main-container">
		<div id="welcome-container">
			<div id="welcome-message-container">
				<div id="slogan-container">
					<h2 id="slogan" class="oleo-400">Dove ogni seme è un sogno in attesa di fiorire</h2>
				</div>
				<% if (userBean == null) { %>
					<div id="login-container" class="oleo-400">
						<div id="sign-up-button" class="animated-round-button">
							<a href="${pageContext.request.contextPath}/registration/registration.jsp">
								<lord-icon class="round-button" src="https://cdn.lordicon.com/ysonqgnt.json" trigger="hover" colors="primary:#ffffff,secondary:#ffffff"></lord-icon>
								<span>Registrati</span>
							</a>
						</div>
						<div id="sign-in-button" class="animated-round-button" onclick="showLogin()">
							<lord-icon class="round-button" src="https://cdn.lordicon.com/lsfszdzd.json" trigger="hover" colors="primary:#ffffff,secondary:#ffffff"></lord-icon>
							<span>Accedi</span>
						</div>
					</div>
				<% } %>
			</div>
			<!-- https://unsplash.com/it/foto/frutto-rotondo-rosso-su-terreno-marrone-hTKYAYwJoSQ -->
			<img src="${pageContext.request.contextPath}/common/assets/img/growing_plant.jpg">
		</div>
		<div class="section-title">
			<h5 class="rubik-600">Prodotti più recenti</h5>
		</div>
		<div class="section-title-breakline"></div>
		<jsp:include page="/common/components/scrollable-container/scrollable-container.jsp">
			<jsp:param name="id" value="latest-products-scrollable-container" />
			<jsp:param name="loading-text" value="Caricamento prodotti..." />
		</jsp:include>

		<%--<jsp:include page="/common/components/edit-product/edit-product.jsp">
			<jsp:param name="label" value="Nome" />
			<jsp:param name="id" value="edit-product-name-input-box" />
			<jsp:param name="type" value="text" />
			<jsp:param name="name" value="name" />
			<jsp:param name="pattern" value="[A-Za-z\s]+" />
			<jsp:param name="errorMessage" value="Nome non valido" />
			<jsp:param name="group" value="product-edit" />
		</jsp:include>--%>
	</div>

	<jsp:include page="/common/components/main-footer/main-footer.jsp"/>
	<jsp:include page="/common/components/chat-button/chat-button.jsp"/>
</body>
</html>
