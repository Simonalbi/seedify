function showLogin() {
    var loginOverlay = document.getElementById("login-overlay");
    loginOverlay.style.display = "block";

    loginOverlay.addEventListener('click', function(event) {
        var loginMainContainer = document.getElementById("login-main-container");
        const isClickOutside = !loginMainContainer.contains(event.target);

        if (isClickOutside && loginOverlay.style.display !== "none") {
            loginOverlay.style.display = "none";
        }
    });
}

function togglePassword() {
    var passwordForm = document.getElementById("password-form");
    var passwordIcon = document.getElementById("password-toggle-icon");
    if (passwordForm.type === "password") {
        passwordForm.type = "text";
        passwordIcon.innerHTML = "visibility_off";
    } else {
        passwordForm.type = "password";
        passwordIcon.innerHTML = "visibility";
    }
}
