export { getAjaxRequestObject, getBaseOriginName, resolveResource, getProductCard };

/**
 * Gets an AJAX request object.
 * @returns {XMLHttpRequest|ActiveXObject|null} The AJAX request object or null if not supported.
 */
function getAjaxRequestObject(){
    var request = null;
    try {
        // Firefox 1+, Chrome 1+, Safari 1.2+, IE7+, Opera 8+, Edge 12+
        request = new XMLHttpRequest();
    } catch (e) {
        // IE 6 and older
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Your browser does not support AJAX!");
                return null;
            }
        }
    }
    return request;
}

/**
 * Gets the base origin name.
 * @returns {String} The base origin name.
 */
function getBaseOriginName(){
    return `${window.location.origin}/seedify_war`;
}

/**
 * Resolves a resource URL to its actual URL.
 * @param {String} resourceUrl The resource URL.
 * @returns {Object} The resolution object.
 */
function resolveResource(resourceUrl) {
    let resolution = {
        image: null
    }

    if (resourceUrl.startsWith("image/")) {
        const resourceParams = resourceUrl.replace("image/", "");
        resolution.image = `${getBaseOriginName()}/resources-servlet?${resourceParams}`;
    }

    return resolution;
}

/**
 * Gets the product card.
 * @param {String} name The product name.
 * @param {Number} price The product price.
 * @param {String} image The product image.
 * @returns {HTMLDivElement} The product card.
 */
function getProductCard(name, price, image) {
    const productContainer = document.createElement("div");
    productContainer.classList.add("product-container", "ui-block", "rubik-300");

    // Creating favorite button
    //TODO: cuore pieno al click
    const favoriteButton = document.createElement("button");
    favoriteButton.classList.add("favorite-button", "material-button");

    const favoriteIcon = document.createElement("span");
    favoriteIcon.classList.add("material-icons-round", "md-18");
    favoriteIcon.innerHTML = "favorite_border";

    // Creating product image section
    const productImageSection = document.createElement("div");
    productImageSection.classList.add("product-image-section");

    const productImageContainer = document.createElement("div")
    productImageContainer.classList.add("product-image-container");

    const productImage = document.createElement("img");
    productImage.src = image;

    // Creating product info section
    const productInfoSection = document.createElement("div");
    productInfoSection.classList.add("product-info-section");

    const productInfoContainer = document.createElement("div");
    productInfoContainer.classList.add("product-info-container");

    const productInfo = document.createElement("div");
    productInfo.classList.add("product-info");

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
