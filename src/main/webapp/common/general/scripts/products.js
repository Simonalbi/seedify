export { getProductCard };

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
    //TODO: cuore pieno al click -> action=add_to_favorites&entity_primary_key=product_id=1
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