export { toast };

const MAX_TOASTS = 3;

/**
 * The different types of toasts that can be displayed.
 * @type {{SUCCESS: string, ERROR: string, WARNING: string}}
 */
const ToastIcons = {
    SUCCESS: "check_circle_outline",
    WARNING: "report_problem",
    ERROR: "highlight_off"
};

/**
 * Draws the toasts on the screen.
 * @param {Array} toasts - The toasts to draw
 */
function drawToasts(toasts) {
    toasts.forEach((toast, index) => {
        toast.style.top = `calc(20px + ${index * 65}px)`;
    });
}

/**
 * Adds a new toast to the screen.
 * @param {HTMLElement} newToast - The toast to add
 */
function addNewToast(newToast) {
    let toasts = Array.from(document.getElementsByClassName("toast"));
    toasts.push(newToast);

    if (toasts.length > MAX_TOASTS) {
        toasts[0].remove();
        toasts.shift();
    }

    drawToasts(toasts);
    document.body.appendChild(newToast);

    setTimeout(() => {
        newToast.remove();
        drawToasts(Array.from(document.getElementsByClassName("toast")));
    }, 3000);
}

/**
 * Displays a toast message on the screen.
 * @param {string} message - The message to display
 * @param {string} type - The type of toast to display
 */
function toast(message, type) {
    const newToast = document.createElement("div");
    newToast.className = `toast toast-${type.toLowerCase()} rubik-300`;

    const toastIcon = document.createElement("span");
    toastIcon.classList.add("material-icons-round", "md-18");
    toastIcon.innerHTML = ToastIcons[type];

    const toastMessage = document.createElement("span");
    toastMessage.innerText = message;

    newToast.appendChild(toastIcon);
    newToast.appendChild(toastMessage);

    addNewToast(newToast);
}
