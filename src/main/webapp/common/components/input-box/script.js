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

document.addEventListener("input", (event) => {
    const element = event.target;
    if(element.classList.contains("wrongable")) {
        const errorTextElements = element.parentNode.getElementsByClassName("error-text");
        if (!element.checkValidity()) {
           Array.from(errorTextElements).forEach(element => {
                element.style.color = "#FF0000";
            });
            element.classList.add("invalid-input");
        } else {
           Array.from(errorTextElements).forEach(element => {
                element.style.color = "transparent";
            });
            element.classList.remove("invalid-input");
        }
    }
});
