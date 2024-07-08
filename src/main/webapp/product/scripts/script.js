import { sendAddToCartRequest, sendAddToFavoriteRequest, sendRemoveFromFavoriteRequest } from "../../common/general/scripts/products.js";

window.sendAddToCartRequest = function(productId, quantity) {
    sendAddToCartRequest(productId, quantity);
}

window.addOrRemoveFromFavorites = function(favoriteButton, productId) {
    const iconContent = favoriteButton.getElementsByTagName("span")[0].innerHTML;

    if (iconContent === "favorite") {
        sendRemoveFromFavoriteRequest(productId, favoriteButton);
    } else if (iconContent === "favorite_border") {
        sendAddToFavoriteRequest(productId, favoriteButton);
    }
}
