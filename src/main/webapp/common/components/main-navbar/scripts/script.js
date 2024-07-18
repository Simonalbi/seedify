/**
 * Show the sidebar
 */
function showSideBar() {
    const sidebar = document.getElementById("sidebar");
    const sidebarContent = document.getElementById("sidebar-content");
    const opacityLayer = document.getElementById("sidebar-opacity-layer");

    sidebarContent.style.visibility = "visible";
    sidebar.style.width = "260px";
    opacityLayer.style.display = "block";
}

/**
 * Hide the sidebar
 */
function hideSideBar() {
    const sidebar = document.getElementById("sidebar");
    const sidebarContent = document.getElementById("sidebar-content");
    const opacityLayer = document.getElementById("sidebar-opacity-layer");

    sidebar.style.width = "0";
    sidebarContent.style.visibility = "hidden";
    opacityLayer.style.display = "none";
}

/*
 * Show the search bar
*/
function showSearchBar() {
    const searchBarOverlay = document.getElementById("search-bar-container");
    const searchBox = document.getElementById("search-bar-box");
    searchBarOverlay.style.display = "flex";
    searchBox.style.width = "100%";
}

/*
 * Hide the search bar
 */
function hideSearchBar() {
    const searchBarOverlay = document.getElementById("search-bar-container");
    const searchBox = document.getElementById("search-bar-box");
    searchBarOverlay.style.display = "none";
    searchBox.style.width = "20px";
}
