function togglePassword(formId) {
    const passwordForm = document.getElementById(formId);
    const passwordIcon = document.getElementById(`${formId}-toggle-icon`);
    if (passwordForm.type === "password") {
        passwordForm.type = "text";
        passwordIcon.innerHTML = "visibility_off";
    } else {
        passwordForm.type = "password";
        passwordIcon.innerHTML = "visibility";
    }
}