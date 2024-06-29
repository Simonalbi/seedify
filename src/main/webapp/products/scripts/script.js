import {getAjaxRequestObject, getBaseOriginName, resolveResource } from '../../common/general/scripts/script.js';
import { getProductCard } from "../../common/general/scripts/products.js";
import { addToScrollableContainer } from '../../common/components/scrollable-container/scripts/script.js';

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
                resolveResource(product['immagine']).image
            )
        );
    })

    return productsCategoryContainer;
}

function renderLatestProducts(products) {
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

        const loadingAllProductsText = document.getElementById("loading-text-container");
        loadingAllProductsText.remove();

        allProductsContainer.appendChild(categorySeparator);
    }
}

function requestLatestProducts() {
    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
            if (ajaxTableDataRequest.status === 200) {
                const products = JSON.parse(ajaxTableDataRequest.responseText);
                renderLatestProducts(products);
            }
        }
    }

    const url = `${getBaseOriginName()}/product-servlet?action=get_latest_products&fields=immagine,nome,prezzo`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

// TODO Add error if code != 200 in -> ALL <- AJAX REQUESTS
function requestAllProducts() {
    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
            if (ajaxTableDataRequest.status === 200) {
                const products = JSON.parse(ajaxTableDataRequest.responseText);
                renderAllProducts(products);
            }
        }
    }

    const url = `${getBaseOriginName()}/product-servlet?action=get_all_products&fields=immagine,nome,prezzo,tipologia&filter=tipologia`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

document.addEventListener('DOMContentLoaded', () => {
    requestLatestProducts();
    requestAllProducts();
});
