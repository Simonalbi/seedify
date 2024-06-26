<div id="sidebar-opacity-layer" onclick="hideSideBar()"></div>
<aside id="sidebar" class="rubik-300">
	<div id="sidebar-content">
		<div id="account-details-container">
			<div class="profile-picture">
				<%--TODO Add link to home page--%>
				<img src="${pageContext.request.contextPath}/common/assets/img/profile/client/3.png">
			</div>
			<div id="account-details">
				<%--TODO Add real data--%>
				<span>Name (Role)</span>
				<span>justemail@gmail.com</span>
			</div>
		</div>
		<div class="sidebar-separator"></div>
		<div id="options-container">
			<div class="sidebar-option">
				<%--TODO Add link to home page--%>
				<a href="#">
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
			<div class="profile-picture">
				<%--TODO Add link to home page--%>
				<img src="${pageContext.request.contextPath}/common/assets/img/profile/client/3.png">
			</div>
		</div>
	</div>
</nav>