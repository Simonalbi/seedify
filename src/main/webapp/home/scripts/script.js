import { addToScrollableContainer } from "../../common/components/scrollable-container/scripts/script.js";
import { getAjaxRequestObject, getBaseOriginName, resolveResource } from "../../common/general/scripts/script.js";
import { getProductCard } from "../../common/general/scripts/products.js";

document.addEventListener('DOMContentLoaded', () => {
    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
            if (ajaxTableDataRequest.status === 200) {
                const products = JSON.parse(ajaxTableDataRequest.responseText);
                products.forEach(function (product) {
                    addToScrollableContainer(
                        "latest-products-scrollable-container",
                        getProductCard (
                            product['nome'],
                            product['prezzo'],
                            resolveResource(product['immagine']).image
                        )
                    )
                })
            }
        }
    }

    const url = `${getBaseOriginName()}/product-servlet?action=get_latest_products&fields=immagine,nome,prezzo`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
});