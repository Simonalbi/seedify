function showLogin() {
    const loginOverlay = document.getElementById("login");
    loginOverlay.style.display = "block";

    loginOverlay.addEventListener('click', function(event) {
        const loginMainContainer = document.getElementById("login-main-container");
        const isClickOutside = !loginMainContainer.contains(event.target);

        if (isClickOutside && loginOverlay.style.display !== "none") {
            loginOverlay.style.display = "none";
        }
    });
}
