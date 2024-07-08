import { getBaseOriginName, sendAjaxRequest } from "../../../general/scripts/script.js";

export { showEditProduct };

window.hideEditProduct = hideEditProduct;

/**
 * Show the edit product overlay
 * @param {String} productIdentifier - The product identifier.
 */
function showEditProduct(productIdentifier) {
    const editProductOverlay = document.getElementById("edit-product");

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

                editProductOverlay.style.visibility = "visible";
            }
        }
    )
}

/**
 * Hide the edit product overlay
 */
function hideEditProduct() {
    const editProductOverlay = document.getElementById("edit-product");
    editProductOverlay.style.visibility = "hidden";
}
