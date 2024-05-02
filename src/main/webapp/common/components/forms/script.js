function togglePassword() {
    var passwordForm = document.getElementById("password-input-box");
    var passwordIcon = document.getElementsByClassName("password-toggle-icon")[0];
    if (passwordForm.type === "password") {
        passwordForm.type = "text";
        passwordIcon.innerHTML = "visibility_off";
    } else {
        passwordForm.type = "password";
        passwordIcon.innerHTML = "visibility";
    }
}