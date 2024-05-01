function showPassword() {
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