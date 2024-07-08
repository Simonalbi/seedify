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