import {getAjaxRequestObject, getBaseOriginName, resolveResource} from '../../common/general/scripts/script.js';

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
    nameParagraph.classList.add("product-name", "rubik-600");
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

function getCategory(title, products) {
    const productsCategoryContainer = document.createElement("div");
    productsCategoryContainer.classList.add("products-category-container");

    const categoryTitle = document.createElement("div");
    categoryTitle.classList.add("category-title");

    const h6Title = document.createElement("h6");
    h6Title.classList.add("rubik-400");
    h6Title.innerHTML = title;

    const categoryProducts = document.createElement("div");
    categoryProducts.classList.add("category-products");

    productsCategoryContainer.appendChild(categoryTitle);
    productsCategoryContainer.appendChild(categoryProducts);

    categoryTitle.appendChild(h6Title);

    products.forEach(function (obj) {
        categoryProducts.appendChild(
            getProductCard (
                obj['nome'],
                obj['prezzo'],
                resolveResource(obj['immagine']).image
            )
        );
    })

    return productsCategoryContainer;
}

function renderAllProducts(products) {
    const allProductsContainer = document.getElementById("all-products-container");

    for (let category in products) {
        allProductsContainer.appendChild(
            getCategory(
                category,
                products[category]
            )
        );

        const categorySeparator = document.createElement("div");
        categorySeparator.classList.add("category-separator");

        allProductsContainer.appendChild(categorySeparator);
    }
}

// TODO Add error if code != 200 in -> ALL <- AJAX REQUESTS
function requestAllProducts() {
    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
            if (ajaxTableDataRequest.status === 200) {
                //TODO aggiungere prodotti nella barra dei prodotti recenti
                const products = JSON.parse(ajaxTableDataRequest.responseText);
                renderAllProducts(products);
            }
        }
    }

    const url = `${getBaseOriginName()}/product-servlet?action=get_all_products&fields=immagine,nome,prezzo,tipologia&filter=tipologia`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

requestAllProducts();