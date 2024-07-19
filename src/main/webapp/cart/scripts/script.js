import {getBaseOriginName, sendAjaxRequest} from "../../common/general/scripts/script.js";
import {goToProductPage} from "../../common/general/scripts/products.js";
import {toast} from "../../common/general/scripts/toast.js";

window.sendRemoveFromCartRequest = sendRemoveFromCartRequest;

/**
 * Sends a request to add a product to the cart.
 * @param {number} productId - The id of the product to add to the cart
 */
window.goToProductPage = function (productId) {
    goToProductPage(productId);
}

/**
 * Sends a request to add a product to the cart.
 * @param {HTMLElement} deleteFromCartButton - The button that was clicked to remove the product from the
 * @param {number} productId - The id of the product to remove from the cart
 * @param {number} quantity - The quantity of the product to remove from the cart
 * @param {number} productPrice - The price of the product to remove from the cart
 */
function sendRemoveFromCartRequest(deleteFromCartButton, productId, quantity, productPrice) {
    sendAjaxRequest(
        "DELETE",
        `${getBaseOriginName()}/cart-servlet?action=remove_from_cart&product_id=${productId}`,
        null,
        {
            200: function () {
                deleteFromCartButton.parentElement.remove();
                const counter = document.querySelector("#cart-items-counter span");

                const newProductsCount = Number(counter.innerHTML) - quantity;
                counter.innerHTML = `${newProductsCount}`;

                const cartPageSummaryContainer = document.getElementById("cart-summary-container");
                if (cartPageSummaryContainer) {
                    const totalCartProducts = document.getElementById("total-cart-products");
                    totalCartProducts.innerHTML = `${newProductsCount}`;

                    const totalCartPrice = document.getElementById("total-cart-price");
                    const newTotalCartPrice = Number(totalCartPrice.innerHTML) - (quantity * productPrice);
                    totalCartPrice.innerHTML = `${newTotalCartPrice.toFixed(2)}`;
                }

                toast("Prodotto rimosso dal carrello", "SUCCESS");
            },
            400: function () {
                toast("Non è stato possibile rimuovere il prodotto dal carrello", "ERROR");
            }
        }
    );
}

document.addEventListener('DOMContentLoaded', () => {
    const removeButtons = document.getElementsByClassName("remove-from-cart-button");

    Array.from(removeButtons).forEach(button => {
        button.addEventListener('click', (event) => {
            event.stopImmediatePropagation();
        });
    });
});
