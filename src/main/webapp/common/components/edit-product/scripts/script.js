import {getBaseOriginName, sendAjaxRequest} from "../../../general/scripts/script.js";

export {showEditProduct, hideEditProduct};

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
        function (response) {
            const productData = JSON.parse(response)[0];

            console.log(productData)

            document.getElementById("edit-product-name-input-box").value = productData['nome'];
            document.getElementById("edit-product-price-input-box").value = productData['prezzo'];
            document.getElementById("edit-product-quantity-input-box").value = productData['quantità'];
            document.getElementById("edit-product-category-input-box").value = productData['tipologia'];
            document.getElementById("edit-product-season-input-box").value = productData['stagione'];
            document.getElementById("edit-product-required-water-input-box").value= productData['acqua_richiesta'];
            document.getElementById("edit-product-description-input-box").value = productData['descrizione'];

            editProductOverlay.style.visibility = "visible";
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

document.addEventListener('DOMContentLoaded', () => {
    const dropZone = document.getElementById('edit-product-image-container');
    const fileInput = document.getElementById('file-input');
    const fileList = document.getElementById('file-list');

    dropZone.addEventListener('click', () => {
        fileInput.click();
    });

    dropZone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropZone.classList.add('dragover');
    });

    dropZone.addEventListener('dragleave', () => {
        dropZone.classList.remove('dragover');
    });

    dropZone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropZone.classList.remove('dragover');
        const files = Array.from(e.dataTransfer.files);
        handleFiles(files);
    });

    fileInput.addEventListener('change', () => {
        const files = Array.from(fileInput.files);
        handleFiles(files);
    });

    function handleFiles(files) {
        files.forEach(file => {
            const listItem = document.createElement('div');
            listItem.textContent = file.name;
            fileList.appendChild(listItem);
        });
    }
});