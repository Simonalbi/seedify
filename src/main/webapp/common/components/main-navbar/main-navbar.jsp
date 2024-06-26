<%@ page import="com.unisa.seedify.model.UserBean" %>

<%
	UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
%>

<div id="sidebar-opacity-layer" onclick="hideSideBar()"></div>
<aside id="sidebar" class="rubik-300">
	<div id="sidebar-content">
		<div id="account-details-container">
			<% if (userBean != null) { %>
				<div class="profile-picture">
					<img src="${pageContext.request.contextPath}/resources-servlet?resourceType=profile_picture">
				</div>
				<div id="account-details">
					<span><%= userBean.getName() %> (<%= userBean.getRole().toString() %>)</span>
					<span><%= userBean.getEmail()%></span>
				</div>
			<% } else { %>
				<lord-icon src="https://cdn.lordicon.com/kthelypq.json" trigger="hover" colors="primary:#000000"></lord-icon>
			<% } %>
		</div>
		<div class="sidebar-separator"></div>
		<div id="options-container">
			<div class="sidebar-option">
				<a href="${pageContext.request.contextPath}/home/home.jsp">
					<span class="material-icons-round md-18">home</span>
					<span class="option-label">Home</span>
				</a>
			</div>
			<div class="sidebar-separator"></div>
			<div class="sidebar-option">
				<a href="#">
					<span class="material-icons-round md-18">shopping_bag</span>
					<span class="option-label">Prodotti</span>
				</a>
			</div>
			<div class="sidebar-separator"></div>
			<div class="sidebar-option">
				<a href="#">
					<span class="material-icons-round md-18">groups</span>
					<span class="option-label">Community</span>
				</a>
			</div>
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
				<a href="#">
					<span class="material-icons-round md-18">home</span>
					<span class="option-label">Home</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
		<div class="menu-option">
			<div class="menu-option-content-container">
				<a href="#">
					<span class="material-icons-round md-18">shopping_bag</span>
					<span class="option-label">Prodotti</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
		<div class="menu-option">
			<div class="menu-option-content-container">
				<a href="#">
					<span class="material-icons-round md-18">groups</span>
					<span class="option-label">Community</span>
				</a>
				<div class="selection-underline"></div>
			</div>
		</div>
	</div>
	<div id="actions-container">
		<div class="action">
			<lord-icon src="https://cdn.lordicon.com/mfmkufkr.json" trigger="hover" colors="primary:#000000"></lord-icon>
			<div class="notification">
				<span>0</span>
			</div>
		</div>
		<div class="action">
			<% if (userBean != null) { %>
				<div class="profile-picture">
					<img src="${pageContext.request.contextPath}/resources-servlet?resourceType=profile_picture">
				</div>
			<% } else { %>
				<lord-icon src="https://cdn.lordicon.com/kthelypq.json" trigger="hover" colors="primary:#000000"></lord-icon>
			<% } %>
		</div>
	</div>
</nav>