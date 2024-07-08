import { getBaseOriginName, resolveResource, sendAjaxRequest} from "../../common/general/scripts/script.js";
import { addToScrollableContainer } from "../../common/components/scrollable-container/scripts/script.js";
import { getProductCard } from "../../common/general/scripts/products.js";

/**
 * Requests the latest products and adds them to the scrollable container.
 */
function requestLatestProducts() {
    sendAjaxRequest(
        "GET",
        `${getBaseOriginName()}/product-servlet?action=get_latest_products&fields=immagine,nome,prezzo,id_prodotto,preferito`,
        null,
        {
            200: function (response) {
                const products = JSON.parse(response);
                products.forEach(function (product) {
                    addToScrollableContainer(
                        "latest-products-scrollable-container",
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

/**
 * Requests the most purchased products and adds them to the scrollable container.
 */
function requestMostPurchasedProducts() {
    sendAjaxRequest(
        "GET",
        `${getBaseOriginName()}/product-servlet?action=get_most_purchased_products&fields=immagine,nome,prezzo,id_prodotto,preferito`,
        null,
        {
            200: function (response) {
                const products = JSON.parse(response);
                products.forEach(function (product) {
                    addToScrollableContainer(
                        "most-purchased-products-scrollable-container",
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
    requestLatestProducts();
    requestMostPurchasedProducts();
});
