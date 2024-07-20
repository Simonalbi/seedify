import { getBaseOriginName, sendAjaxRequest } from "../../../../general/scripts/script.js";
import { showOverlay, hideOverlay } from "../../scripts/script.js";

export { showAddCreditCardOverlay };

window.hideCreditCardForm = function () {
    hideOverlay("edit-credit-card-overlay");
}

/**
 * Show the edit credit card overlay.
 */
function showAddCreditCardOverlay() {
    showOverlay("edit-credit-card-overlay");
}