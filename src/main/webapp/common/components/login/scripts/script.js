import {getBaseOriginName, sendAjaxRequest} from "../../../general/scripts/script.js";
import { toast } from "../../../general/scripts/toast.js";

export { showLogin };

window.showLogin = showLogin;
window.hideLogin = hideLogin;

/**
 * Show login overlay
 */
function showLogin() {
    const loginOverlay = document.getElementById("login");
    loginOverlay.style.display = "block";

    loginOverlay.addEventListener('click', function(event) {
        const loginMainContainer = document.getElementById("login-main-container");
        const isClickOutside = !loginMainContainer.contains(event.target);

        if (isClickOutside && loginOverlay.style.display !== "none") {
            hideLogin();
        }
    });
}

/**
 * Hide login overlay
 */
function hideLogin() {
    const loginOverlay = document.getElementById("login");
    loginOverlay.style.display = "none";
}

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    loginForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const formData = {
            "email": document.getElementById("login-email-input-box").value,
            "password": document.getElementById("login-password-input-box").value
        };

        sendAjaxRequest(
            "POST",
            `${getBaseOriginName()}/login-servlet`,
            JSON.stringify(formData),
            {
                200: function () {
                  window.location.href = `${getBaseOriginName()}/dashboard`;
                },
                401: function (){
                    toast("Credeziali errate", "ERROR");
                }
            }
        )
    })
})
