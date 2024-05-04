<jsp:include page="/common/general/metadata.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/components/header/style.css">

<div id="header">
	<div id="logo-container">
		<img src="${pageContext.request.contextPath}/common/assets/img/logo/logo_900x300_small.png" alt="">
	</div>
		<div id="menu-container">
		  <div class="menu-option">
			  <a href="#">
				  <span class="material-icons md-18">home</span>
          Home
        </a>
				<div class="selection-underline"></div>
      </div>
			<div class="menu-separator"></div>
			<div class="menu-option">
			  <a href="#">
				  <span class="material-icons md-18">shopping_bag</span>
					Prodotti
				</a>
				<div class="selection-underline"></div>
			</div>
			<div class="menu-separator"></div>
			<div class="menu-option">
				<a href="#">
					<span class="material-icons md-18">groups</span>
					Community
				</a>
			  <div class="selection-underline"></div>
			</div>
		</div>
		<div id="actions-container">
			<div class="action">
		    <lord-icon src="https://cdn.lordicon.com/mfmkufkr.json" trigger="hover" colors="primary:#000000"></lord-icon>
				<div class="notification">
					<p>0</p>
				</div>
			</div>
			<div class="action">
				<lord-icon src="https://cdn.lordicon.com/kthelypq.json" trigger="hover" colors="primary:#000000"></lord-icon>
				<div class="notification">
					<p>0</p>
				</div>
			</div>
		</div>
</div>
