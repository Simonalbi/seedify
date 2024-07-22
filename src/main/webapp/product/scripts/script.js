import { getProductCard, sendAddToCartRequest, sendAddToFavoriteRequest, sendRemoveFromFavoriteRequest } from "../../common/general/scripts/products.js";
import { getBaseOriginName, resolveResource, sendAjaxRequest } from "../../common/general/scripts/script.js";
import { addToScrollableContainer } from "../../common/components/scrollable-container/scripts/script.js";
import { toast } from "../../common/general/scripts/toast.js";

window.sendAddToCartRequest = function(productId, quantity) {
    sendAddToCartRequest(productId, quantity, null);
}

window.addOrRemoveFromFavorites = function(favoriteButton, productId) {
    const iconContent = favoriteButton.getElementsByTagName("span")[0].innerHTML;

    if (iconContent === "favorite") {
        sendRemoveFromFavoriteRequest(productId, favoriteButton);
    } else if (iconContent === "favorite_border") {
        sendAddToFavoriteRequest(productId, favoriteButton);
    }
}

window.copyProductUrlToClipboard = function (productId) {
    if (navigator.clipboard && navigator.clipboard.writeText) {
        navigator.clipboard.writeText(`${getBaseOriginName()}/resources-servlet?resource_type=product_page&product_id=${productId}`)
            .then(() => {
                toast("Link copiato negli appunti", "SUCCESS");
            })
            .catch(err => {});
    } else {
        console.error("Clipboard API non supportata");
    }
}
function requestCategoryProducts(category) {
    sendAjaxRequest(
        "GET",
        `${getBaseOriginName()}/product-servlet?action=get_related_products&fields=immagine,nome,prezzo,id_prodotto,preferito&category=${category}`,
        null,
        {
            200: function (response) {
                const products = JSON.parse(response);
                products.forEach(function (product) {
                    addToScrollableContainer(
                        "related-products-scrollable-container",
                        getProductCard (
                            product['nome'],
                            product['prezzo'],
                            resolveResource(product['immagine']).image,
                            product['id_prodotto'],
                            JSON.parse(product['preferito'].toLowerCase())
                        )
                    );
                });
            }
        })
}

document.addEventListener('DOMContentLoaded', () => {
    const category = document.getElementById("product-category").innerHTML;
    requestCategoryProducts(category);
});
