import { getBaseOriginName } from "./script.js";

export { getProductCard };

/**
 *
 * @param productId
 * @param favoriteButton
 */
function sendAddToFavoriteRequest(productId, favoriteButton) {
    const ajaxRequest = new XMLHttpRequest();
    ajaxRequest.onreadystatechange = function () {
        if (ajaxRequest.readyState === 4) {
            if (ajaxRequest.status === 200) {
                favoriteButton.getElementsByTagName("span")[0].innerHTML = "favorite";
            }
        }
    }

    const body = {
        action: "add_to_favorites",
        product_id: productId
    };
    const url = `${getBaseOriginName()}/product-servlet`;
    ajaxRequest.open("POST", url, true);
    ajaxRequest.send(JSON.stringify(body));
}

/**
 *
 * @param productId
 * @param favoriteButton
 */
function sendRemoveFromFavoriteRequest(productId, favoriteButton) {
    const ajaxRequest = new XMLHttpRequest();
    ajaxRequest.onreadystatechange = function () {
        if (ajaxRequest.readyState === 4) {
            if (ajaxRequest.status === 200) {
                favoriteButton.getElementsByTagName("span")[0].innerHTML = "favorite_border";
            }
        }
    }

    const body = {
        action: "remove_from_favorites",
        product_id: productId
    };
    const url = `${getBaseOriginName()}/product-servlet`;
    ajaxRequest.open("POST", url, true);
    ajaxRequest.send(JSON.stringify(body));
}

/**
 *
 * @param {string} name
 * @param {number} price
 * @param {string} image
 * @param {number} productId
 * @param {boolean} isFavorite
 * @returns {HTMLDivElement}
 */
function getProductCard(name, price, image, productId, isFavorite) {
    const productContainer = document.createElement("div");
    productContainer.classList.add("product-container", "ui-block", "rubik-300");

    const favoriteButton = document.createElement("button");
    favoriteButton.classList.add("favorite-button", "material-button");
    favoriteButton.addEventListener(
        'click',
        function () {
            const iconContent = favoriteButton.getElementsByTagName("span")[0].innerHTML;
            if (iconContent === "favorite") {
                sendRemoveFromFavoriteRequest(productId, favoriteButton);
            } else if (iconContent === "favorite_border") {
                sendAddToFavoriteRequest(productId, favoriteButton);
            }
        }
    )

    const favoriteIcon = document.createElement("span");
    favoriteIcon.classList.add("material-icons-round", "md-18");
    if (isFavorite) {
        favoriteIcon.innerHTML = "favorite";
    } else {
        favoriteIcon.innerHTML = "favorite_border";
    }

    // Creating product image section
    const productImageSection = document.createElement("div");
    productImageSection.classList.add("product-image-section");

    const productImageContainer = document.createElement("div")
    productImageContainer.classList.add("product-image-container");
    productImageContainer.addEventListener('click', function() {
        window.location.href = `${getBaseOriginName()}/resources-servlet?resource_type=product_page&product_id=${productId}`;
    });

    const productImage = document.createElement("img");
    productImage.src = image;

    // Creating product info section
    const productInfoSection = document.createElement("div");
    productInfoSection.classList.add("product-info-section");

    const productInfoContainer = document.createElement("div");
    productInfoContainer.classList.add("product-info-container");

    const productInfo = document.createElement("div");
    productInfo.classList.add("product-info");
    productInfo.addEventListener('click', function() {
        window.location.href = `${getBaseOriginName()}/resources-servlet?resource_type=product_page&product_id=${productId}`;
    });

    const nameParagraph = document.createElement("p");
    nameParagraph.classList.add("product-name", "rubik-500");
    nameParagraph.innerHTML = name;

    const priceParagraph = document.createElement("p");
    priceParagraph.classList.add("product-price");
    priceParagraph.innerHTML = `${price} €`;

    const productAction = document.createElement("div");
    productAction.classList.add("product-actions");

    const cartButton = document.createElement("button");
    cartButton.classList.add("cart-button", "material-button");

    const cartIcon = document.createElement("span");
    cartIcon.classList.add("material-icons-round", "md-18");
    cartIcon.innerHTML = "add_shopping_cart";

    const addToCartText = document.createElement("span");
    addToCartText.classList.add("small-text");
    addToCartText.innerHTML = "Aggiungi al carrello";

    productContainer.appendChild(favoriteButton);
    favoriteButton.appendChild(favoriteIcon);

    productContainer.appendChild(productImageSection);
    productContainer.appendChild(productInfoSection);

    productImageSection.appendChild(productImageContainer);
    productImageContainer.appendChild(productImage);

    productInfoSection.appendChild(productInfoContainer);
    productInfoContainer.appendChild(productInfo);
    productInfoContainer.appendChild(productAction);

    productInfo.appendChild(nameParagraph);
    productInfo.appendChild(priceParagraph);

    productAction.appendChild(cartButton);
    cartButton.appendChild(cartIcon);
    cartButton.appendChild(addToCartText);

    return productContainer;
}