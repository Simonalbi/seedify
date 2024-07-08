import { getBaseOriginName, resolveResource, sendAjaxRequest } from '../../common/general/scripts/script.js';
import { getProductCard } from "../../common/general/scripts/products.js";
import { addToScrollableContainer } from '../../common/components/scrollable-container/scripts/script.js';

/**
 * Returns a category container with its products.
 * @param {String} title
 * @param {Array} products
 * @returns {HTMLDivElement}
 */
function getCategory(title, products) {
    const productsCategoryContainer = document.createElement("div");
    productsCategoryContainer.classList.add("products-category-container");

    const categoryTitle = document.createElement("div");
    categoryTitle.classList.add("category-title");

    const h6Title = document.createElement("h6");
    h6Title.classList.add("rubik-500");
    h6Title.innerHTML = title;

    const categoryProducts = document.createElement("div");
    categoryProducts.classList.add("category-products");

    productsCategoryContainer.appendChild(categoryTitle);
    productsCategoryContainer.appendChild(categoryProducts);

    categoryTitle.appendChild(h6Title);

    products.forEach(function (product) {
        categoryProducts.appendChild(
            getProductCard (
                product['nome'],
                product['prezzo'],
                resolveResource(product['immagine']).image,
                product['id_prodotto'],
                JSON.parse(product['preferito'].toLowerCase())
            )
        );
    })

    return productsCategoryContainer;
}

/**
 * Renders the latest products.
 * @param {Array} products
 */
function renderLatestProducts(products) {
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
        )
    })
}

/**
 * Renders all the products.
 * @param {Object} products
 */
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

    const loadingAllProductsText = document.getElementById("loading-text-container");
    loadingAllProductsText.remove();
}

/**
 * Requests the latest products.
 */
function requestLatestProducts() {
    sendAjaxRequest(
        "GET",
        `${getBaseOriginName()}/product-servlet?action=get_latest_products&fields=immagine,nome,prezzo,id_prodotto,preferito`,
        null,
        {
            200: function (response) {
                const products = JSON.parse(response);
                renderLatestProducts(products);
            }
        }
    )
}

/**
 * Requests all the products.
 */
function requestAllProducts() {
    sendAjaxRequest(
        "GET",
        `${getBaseOriginName()}/product-servlet?action=get_all_products&fields=immagine,nome,prezzo,tipologia,id_prodotto,preferito&filter=tipologia`,
        null,
        {
            200: function (response) {
                const products = JSON.parse(response);
                renderAllProducts(products);
            }
        }
    )
}

document.addEventListener('DOMContentLoaded', () => {
    requestLatestProducts();
    requestAllProducts();
});
