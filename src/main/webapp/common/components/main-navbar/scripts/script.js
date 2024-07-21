import { getBaseOriginName } from "../../../general/scripts/script.js";

window.showSideBar = showSideBar;
window.hideSideBar = hideSideBar;
window.showSearchBar = showSearchBar;
window.hideSearchBar = hideSearchBar;

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
    const searchBox = document.getElementById("search-bar-box");
    const closeSearchBarIcon = document.getElementById("close-search-bar-icon");

    searchBox.style.width = "80%";
    searchBox.style.opacity = "1";
    searchBox.style.transform = "scaleX(1)";
    closeSearchBarIcon.style.opacity = "1";
}

/*
 * Hide the search bar
 */
function hideSearchBar() {
    const searchBox = document.getElementById("search-bar-box");
    const closeSearchBarIcon = document.getElementById("close-search-bar-icon");

    searchBox.style.opacity = "0";
    searchBox.style.transform = "scaleX(0)";
    closeSearchBarIcon.style.opacity = "0";
}

document.addEventListener("DOMContentLoaded", function() {
    const searchBar = document.getElementById('search-bar-input-box');
    searchBar.addEventListener('keyup', function(event) {
        if (event.key === 'Enter') {
            window.location.href = `${getBaseOriginName()}/products?keywords=${searchBar.value}`
        }
    });
});
