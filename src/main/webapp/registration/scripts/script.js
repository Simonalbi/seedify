import { getBaseOriginName, sendAjaxRequest } from "../../common/general/scripts/script.js";
import { toast } from "../../common/general/scripts/toast.js";

/**
 * Validates the step by changing the icon to a checkmark.
 * @param {string} elementId - The ID of the element to validate
 */
function validateStep(elementId) {
    const constraintElement = document.getElementById(elementId);
    const icon = constraintElement.querySelector("span");
    icon.classList.replace("incomplete-step", "complete-step");
    icon.innerHTML = "done";
}

/**
 * Invalidates the step by changing the icon to a cross icon.
 * @param {string} elementId - The ID of the element to invalidate
 */
function invalidateStep(elementId) {
    const constraintElement = document.getElementById(elementId);
    const icon = constraintElement.querySelector("span");
    icon.classList.replace("complete-step", "incomplete-step");
    icon.innerHTML = "close";
}

/**
 * Validates the password.
 * @param {string} password - The password to validate
 * @returns {boolean} - Whether the password is valid
 */
function validatePassword(password) {
    let completedSteps = 0;

    if (password.length >= 8) {
        validateStep("length-constraint");
        completedSteps++;
    } else {
        invalidateStep("length-constraint");
    }

    if (/[a-z]/.test(password) && /[A-Z]/.test(password)) {
        validateStep("letters-constraint");
        completedSteps++;
    } else {
        invalidateStep("letters-constraint");
    }

    if (/\d/.test(password)) {
        validateStep("number-constraint");
        completedSteps++;
    } else {
        invalidateStep("number-constraint");
    }

    if (/[$#&%!*@.]/.test(password)) {
        validateStep("special-characters-constraint");
        completedSteps++;
    } else {
        invalidateStep("special-characters-constraint");
    }

    const sunflowerElement = document.getElementById("sunflower");
    sunflowerElement.style.transform = `translateY(100%) scale(${1.5 * completedSteps})`;

    return completedSteps === 4;
}

/**
 * Checks if the passwords match.
 * @returns {boolean} - Whether the passwords match
 */
function checkPasswordsMatch() {
    const password = passwordBox.querySelector("input").value;
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

const passwordBox = document.getElementById("registration-password-box");
passwordBox.addEventListener("input", (event) => {
    const password = event.target.value;
    if (!validatePassword(password)) {
        event.target.setCustomValidity("Password does not meet requirements.");
    } else {
        event.target.setCustomValidity("");
    }
});

const passwordConfirmationBox = document.getElementById("registration-password-confirmation-box");
passwordConfirmationBox.addEventListener("input", (event) => {
    if (!checkPasswordsMatch()) {
        event.target.setCustomValidity("Passwords do not match.");
    } else {
        event.target.setCustomValidity("");
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const registrationForm = document.getElementById("registration-form");
    registrationForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const formData = {
            "name": document.getElementById("registration-name-input-box").value,
            "surname": document.getElementById("registration-surname-input-box").value,
            "email": document.getElementById("registration-email-input-box").value,
            "password": document.getElementById("registration-password-input-box").value
        };

        sendAjaxRequest(
            "POST",
            `${getBaseOriginName()}/registration-servlet`,
            JSON.stringify(formData),
            {
                200: function () {
                    window.location.href = `${getBaseOriginName()}/dashboard`;
                },
                400: function() {
                    toast("Dati non validi", "ERROR");
                },
                401: function (){
                    toast("Email già in uso", "ERROR");
                }
            }
        )
    })
})
