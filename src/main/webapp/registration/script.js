let IS_PASSWORD_VALID = false;
let PASSWORDS_MATCH = false;
FORMS_IDS = ["name-box", "surname-box", "email-box"]
for (let i = 0; i < FORMS_IDS.length; i++) {
    const form = document.getElementById(FORMS_IDS[i])
    form.addEventListener("input", () => {
        enableRegistration();
    });
}

const passwordBox = document.getElementById("password-box");
passwordBox.addEventListener("input", (event) => {
    const password = event.target.value;
    IS_PASSWORD_VALID = validatePassword(password);
    PASSWORDS_MATCH = checkPasswordsMatch();
    enableRegistration();
});

const passwordConfirmationBox = document.getElementById("password-confirmation-box");
passwordConfirmationBox.addEventListener("input", () => {
    PASSWORDS_MATCH = checkPasswordsMatch();
    enableRegistration();
});

function validateStep(elementId) {
    const constraintElement = document.getElementById(elementId);
    const icon = constraintElement.querySelector("span");
    icon.classList.replace("incomplete-step", "complete-step");
    icon.innerHTML = "done";
}

function invalidateStep(elementId) {
    const constraintElement = document.getElementById(elementId);
    const icon = constraintElement.querySelector("span");
    icon.classList.replace("complete-step", "incomplete-step");
    icon.innerHTML = "close";
}

function validatePassword(password) {
    let completeSteps = 0;

    if (password.length >= 8) {
        validateStep("length-constraint");
        completeSteps++;
    } else {
        invalidateStep("length-constraint");
    }

    if (/[a-z]/.test(password) && /[A-Z]/.test(password)) {
        validateStep("letters-constraint");
        completeSteps++;
    } else {
        invalidateStep("letters-constraint");
    }

    if (/\d/.test(password)) {
        validateStep("number-constraint");
        completeSteps++;
    } else {
        invalidateStep("number-constraint");
    }

    if (/[$#&%!*@.]/.test(password)) {
        validateStep("special-characters-constraint");
        completeSteps++;
    } else {
        invalidateStep("special-characters-constraint");
    }

    const sunflowerElement = document.getElementById("sunflower");
    sunflowerElement.style.transform = `scale(${1.5 * completeSteps})`;

    return completeSteps === 4;
}

function checkPasswordsMatch() {
    const passwordBox = document.getElementById("password-box");
    const password = passwordBox.querySelector("input").value;

    const passwordConfirmationBox = document.getElementById("password-confirmation-box");
    const confirmationPassword = passwordConfirmationBox.querySelector("input").value;

    let empty = false;
    if (password === "" || confirmationPassword === "") {
        empty = true;
    }

    let valid = false;
    if (password === confirmationPassword && !empty) {
        validateStep("match-constraint");
        valid = true;
    } else {
        invalidateStep("match-constraint");
    }

    return valid;
}

function enableRegistration() {
    let allFormsFilled = true;
    for (let i = 0; i < FORMS_IDS.length; i++) {
        const formValue = document.getElementById(FORMS_IDS[i]).querySelector("input").value
        if (formValue === "") {
            allFormsFilled = false;
            break;
        }
    }

    const submitBox = document.getElementById("submit-box");
    if (allFormsFilled && IS_PASSWORD_VALID && PASSWORDS_MATCH) {
        submitBox.querySelector("input").disabled = false;
    } else {
        submitBox.querySelector("input").disabled = true;
    }
}
