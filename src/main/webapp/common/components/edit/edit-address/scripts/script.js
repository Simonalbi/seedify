import { showOverlay, hideOverlay } from "../../scripts/script.js";

export { showAddressOverlay };

window.hideAddressOverlay = function () {
    hideOverlay("edit-address-overlay");
}

/**
 * Show the edit address overlay.
 */
function showAddressOverlay() {
    showOverlay("edit-address-overlay");
}