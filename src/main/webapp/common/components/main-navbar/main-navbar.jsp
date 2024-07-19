<%@ page import="com.unisa.seedify.model.UserBean" %>
<%@ page import="com.unisa.seedify.model.CartBean" %>

<%
	UserBean mainNavbarUserBean = (UserBean) request.getSession(true).getAttribute("user");
	CartBean mainNavbarCartBean = (CartBean) request.getSession(true).getAttribute("cart");
%>

<div id="sidebar-opacity-layer" onclick="hideSideBar()"></div>
<aside id="sidebar" class="rubik-300">
	<div id="sidebar-content">
		<div id="account-details-container">
			<% if (mainNavbarUserBean != null) { %>
				<div class="profile-picture">
					<img src="${pageContext.request.contextPath}/resources-servlet?resource_type=profile_picture">
				</div>
				<div id="account-details">
					<p class="account-details-value"><%= mainNavbarUserBean.getName() %></p>
					<p class="account-details-value"><%= mainNavbarUserBean.getEmail()%></p>
				</div>
			<% } else { %>
				<lord-icon src="https://cdn.lordicon.com/kthelypq.json" trigger="hover" colors="primary:#000000"></lord-icon>
				<div id="account-details">
					<span>Guest</span>
				</div>
			<% } %>
		</div>
		<div class="sidebar-separator"></div>
		<div id="options-container">
			<div class="sidebar-option">
				<a href="${pageContext.request.contextPath}/home">
					<span class="material-icons-round md-18">home</span>
					<span class="option-label">Home</span>
				</a>
			</div>
			<div class="sidebar-separator"></div>
			<div class="sidebar-option">
				<a href="${pageContext.request.contextPath}/products">
					<span class="material-icons-round md-18">shopping_bag</span>
					<span class="option-label">Prodotti</span>
				</a>
			</div>
			<div class="sidebar-separator"></div>
			<div class="sidebar-option">
				<a href="${pageContext.request.contextPath}/about-us">
					<span class="material-icons-round md-18">info_outline</span>
					<span class="option-label">Chi Siamo</span>
				</a>
			</div>
			<div class="sidebar-separator"></div>
			<% if (mainNavbarUserBean != null) { %>
				<div class="sidebar-option">
					<a href="${pageContext.request.contextPath}/dashboard">
						<span class="material-icons-round md-18">display_settings</span>
						<span class="option-label">Dashboard</span>
					</a>
				</div>
				<div class="sidebar-separator"></div>
			<% } %>
			<% if (mainNavbarUserBean != null) { %>
				<div class="sidebar-option">
					<a href="${pageContext.request.contextPath}/login-servlet">
						<span class="material-icons-round md-18">logout</span>
						<span class="option-label">Esci</span>
					</a>
				</div>
				<div class="sidebar-separator"></div>
			<% } else { %>
				<div class="sidebar-option" onclick="showLogin()">
					<a>
						<span class="material-icons-round md-18">key</span>
						<span class="option-label">Accedi</span>
					</a>
				</div>
				<div class="sidebar-separator"></div>
				<div class="sidebar-option">
					<a href="${pageContext.request.contextPath}/registration">
						<span class="material-icons-round md-18">compost</span>
						<span class="option-label">Registrati</span>
					</a>
				</div>
				<div class="sidebar-separator"></div>
			<% } %>
		</div>
	</div>
</aside>
<nav id="main-navbar" class="rubik-300">
	<div id="logo-container">
		<span class="material-icons-round" id="burger-menu-icon" onclick="showSideBar()">menu</span>
		<img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300.png" alt="">
	</div>
	<div id="menu-container">
		<div class="menu-option">
			<div class="menu-option-content-container">
				<a href="${pageContext.request.contextPath}/home">
					<span class="material-icons-round md-18">home</span>
					<span class="option-label">Home</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
		<div class="menu-option">
			<div class="menu-option-content-container">
				<a href="${pageContext.request.contextPath}/products">
					<span class="material-icons-round md-18">shopping_bag</span>
					<span class="option-label">Prodotti</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
		<div class="menu-option">
			<div class="menu-option-content-container">
				<a href="${pageContext.request.contextPath}/about-us">
					<span class="material-icons-round md-18">info_outline</span>
					<span class="option-label">Chi Siamo</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
	</div>
	<div id="actions-container">
		<div class="action">
			<div id="search" onclick="showSearchBar()">
				<lord-icon src="https://cdn.lordicon.com/kkvxgpti.json" trigger="hover" colors="primary:#000000">
				</lord-icon>
			</div>
		</div>
		<div class="action">
			<%--TODO Aggiungere il link solo se l'utente è loggato--%>
			<a <% if (mainNavbarUserBean != null) { %> href="${pageContext.request.contextPath}/cart" <% } else { %> href="#" <% } %>>
				<lord-icon src="https://cdn.lordicon.com/mfmkufkr.json" trigger="hover" colors="primary:#000000"></lord-icon>
				<div class="notification" id="cart-items-counter">
					<span><%= mainNavbarCartBean != null ? mainNavbarCartBean.getTotalCartItems() : 0 %></span>
				</div>
			</a>
		</div>
		<div class="action" onclick="showSideBar()">
			<% if (mainNavbarUserBean != null) { %>
				<div class="profile-picture" >
					<img src="${pageContext.request.contextPath}/resources-servlet?resource_type=profile_picture" alt="Foto">
				</div>
			<% } else { %>
				<lord-icon src="https://cdn.lordicon.com/kthelypq.json" trigger="hover" colors="primary:#000000" ></lord-icon>
			<% } %>
		</div>
	</div>
</nav>
<div id="search-bar-container">
	<div id="search-bar-box">
		<jsp:include page="/common/components/input-box/input-box.jsp">
			<jsp:param name="id" value="search-bar" />
			<jsp:param name="type" value="text" />
			<jsp:param name="name" value="query" />
			<jsp:param name="placeholder" value="Cerca..." />
			<jsp:param name="group" value="search" />
		</jsp:include>
	</div>
	<div id="close-search-bar-icon" onclick="hideSearchBar()">
		<span class="material-icons-round md-18">close</span>
	</div>
</div>
