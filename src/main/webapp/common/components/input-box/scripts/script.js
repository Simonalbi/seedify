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

function enableSubmitButton() {
    const submitButton = document.getElementById("submit-button");
    submitButton.disabled = false;
}

function disableSubmitButton() {
    const submitButton = document.getElementById("submit-button");
    submitButton.disabled = true;
}

document.addEventListener("input", (event) => {
    let isFormFilled = true;
    let isFormValid = true;

    const inputBoxes = document.getElementsByClassName("input-box");
    for (let i = 0; i < inputBoxes.length; i++) {
        const input = inputBoxes[i].querySelector("input");
        isFormFilled = isFormFilled && input.value !== "";
        isFormValid = isFormValid && input.checkValidity();
    }

    if (isFormFilled && isFormValid) {
        enableSubmitButton();
    } else {
        disableSubmitButton();
    }
});
