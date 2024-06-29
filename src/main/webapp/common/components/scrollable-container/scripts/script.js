export { addToScrollableContainer };

const SCROLL_AMOUNT = 400;

/**
 * Adds an element to a scrollable container.
 * @param {String} id The ID of the scrollable container.
 * @param {HTMLElement} element The element to add.
 */
function addToScrollableContainer(id, element) {
    const container = document.getElementById(id);

    try {
        container.getElementsByTagName("h6")[0].remove();
        container.style.justifyContent = "flex-start";
    } catch (ignored) {}
    container.appendChild(element);
}

function scrollLeft(scrollableContainer) {
    scrollableContainer.scrollBy({
        left: -SCROLL_AMOUNT,
        behavior: 'smooth'
    });
}

function scrollRight(scrollableContainer) {
    scrollableContainer.scrollBy({
        left: SCROLL_AMOUNT,
        behavior: 'smooth'
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const previousButtons = document.getElementsByClassName('previous-button');
    const nextButtons = document.getElementsByClassName('next-button');

    Array.from(previousButtons).forEach(button => button.addEventListener(
        'click',
        function () {
            const scrollableContainer = button.closest('.scrollable-container').querySelector('.scrollable-elements-container');
            scrollLeft(scrollableContainer)
        }
    ));

    Array.from(nextButtons).forEach(button => button.addEventListener(
        'click',
        function () {
            const scrollableContainer = button.closest('.scrollable-container').querySelector('.scrollable-elements-container');
            scrollRight(scrollableContainer)
        }
    ));
});
