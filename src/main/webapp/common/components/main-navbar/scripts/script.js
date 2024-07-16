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
 * Show and hide the search bar and search icon
*/
document.addEventListener('DOMContentLoaded', (event) => {
    const searchIcon = document.getElementById('search-icon');
    const searchBar = document.getElementById('search-bar');

    searchIcon.addEventListener('click', toggleSearchBar);

    searchBar.addEventListener('blur', () => {
        if (searchBar.value.trim() === "") {
            hideSearchBar();
        }
    });

    function toggleSearchBar() {
        if (searchBar.classList.contains('active')) {
            searchBar.classList.remove('active');
            searchBar.blur();
            searchIcon.classList.remove('hidden');
        } else {
            searchBar.classList.add('active');
            searchBar.focus();
            searchIcon.classList.add('hidden');
        }
    }

    function hideSearchBar() {
        searchBar.classList.remove('active');
        searchIcon.classList.remove('hidden');
    }
});