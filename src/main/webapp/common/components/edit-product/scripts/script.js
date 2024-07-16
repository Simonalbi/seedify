import { getBaseOriginName, sendAjaxRequest } from "../../../general/scripts/script.js";

export { showAddProductForm, showEditProductForm };

window.hideEditProduct = hideForm;

function resetForm() {
    document.getElementById("edit-product-name-input-box").value = null;
    document.getElementById("edit-product-price-input-box").value = null;
    document.getElementById("edit-product-quantity-input-box").value = null;
    document.getElementById("edit-product-category-input-box").value = null;
    document.getElementById("edit-product-season-input-box").value = null;
    document.getElementById("edit-product-required-water-input-box").value= null;
    document.getElementById("edit-product-description-input-box").value = null;
}

function showForm() {
    const editProductOverlay = document.getElementById("edit-product");
    editProductOverlay.style.visibility = "visible";
}

/**
 * Hide the edit product overlay
 */
function hideForm() {
    const editProductOverlay = document.getElementById("edit-product");
    editProductOverlay.style.visibility = "hidden";
}

/**
 * Show the add product overlay
 */
function showAddProductForm() {
    const form = document.getElementById("edit-product-form");
    form.action = `${getBaseOriginName()}/product-servlet?action=add_product`;

    resetForm();
    showForm();
}

/**
 * Show the edit product overlay
 * @param {String} productIdentifier - The product identifier.
 * @param {String} editCall - The endpoint to call to edit the product.
 */
function showEditProductForm(productIdentifier, editCall) {
    const form = document.getElementById("edit-product-form");
    form.action = `${getBaseOriginName()}/${editCall}`;

    resetForm();
    if (productIdentifier !== null) {
        sendAjaxRequest(
            "GET",
            `${getBaseOriginName()}/product-servlet?action=get_product&entity_primary_key=${productIdentifier}&fields=nome,prezzo,quantità,tipologia,stagione,acqua_richiesta,descrizione`,
            null,
            {
                200: function (response) {
                    const productData = JSON.parse(response)[0];
                    document.getElementById("edit-product-name-input-box").value = productData['nome'];
                    document.getElementById("edit-product-price-input-box").value = productData['prezzo'];
                    document.getElementById("edit-product-quantity-input-box").value = productData['quantità'];
                    document.getElementById("edit-product-category-input-box").value = productData['tipologia'];
                    document.getElementById("edit-product-season-input-box").value = productData['stagione'];
                    document.getElementById("edit-product-required-water-input-box").value= productData['acqua_richiesta'];
                    document.getElementById("edit-product-description-input-box").value = productData['descrizione'];
                }
            }
        )
    }
    showForm();
}
