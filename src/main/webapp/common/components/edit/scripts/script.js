export { showOverlay, hideOverlay };

/**
 * Show the edit overlay
 * @param {String} formId - The form id
 */
function showOverlay(formId) {
    const editProductOverlay = document.getElementById(formId);
    editProductOverlay.style.visibility = "visible";
}

/**
 * Hide the edit overlay
 * @param {String} formId - The form id
 */
function hideOverlay(formId) {
    const editProductOverlay = document.getElementById(formId);
    editProductOverlay.style.visibility = "hidden";
}