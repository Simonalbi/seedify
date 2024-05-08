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

function enableSubmitButton(group) {
    const submitButtons = document.querySelectorAll(`input[type="submit"].${group}`)
    const submitButton = document.getElementById("submit-button");

    submitButton.disabled = false;
}

function disableSubmitButton(group) {
    const submitButton = document.getElementById("submit-button");
    submitButton.disabled = true;
}

document.addEventListener("input", (event) => {
    const classesArray = Array.from(event.target.classList);
    const group = classesArray.find(c => c.endsWith("-input-box-group"));

    let isFormFilled = true;
    let isFormValid = true;

    console.log(group);
    const inputBoxes = document.getElementsByClassName("input-box");
    for (let i = 0; i < inputBoxes.length; i++) {
        const input = inputBoxes[i].querySelector("input");
        if (input.classList.contains(group)) {
            isFormFilled = isFormFilled && input.value !== "";
            isFormValid = isFormValid && input.checkValidity();
        }
    }

    if (isFormFilled && isFormValid) {
        enableSubmitButton(group);
    } else {
        disableSubmitButton(group);
    }
});
