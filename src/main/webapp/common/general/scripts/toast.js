export { toast };

const ToastIcons = {
    success: "check_circle_outline",
    warning: "report_problem",
    error: "highlight_off"
};

const maxToasts = 3;
let toastQueue = [];

function toast(message, type) {
    let container = document.getElementById("toast-container");
    if (container === null) {
        container = document.createElement("div");
        container.id = "toast-container";
        container.classList.add("rubik-200");
        document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast toast-${type}`;

    const toastIcon = document.createElement("span");
    toastIcon.classList.add("material-icons-round", "md-18");
    toastIcon.innerHTML = ToastIcons[type];

    const toastMessage = document.createElement("span");
    toastMessage.innerText = message;

    toast.appendChild(toastIcon);
    toast.appendChild(toastMessage);

    container.appendChild(toast);
    toastQueue.push(toast);

    if (toastQueue.length > maxToasts) {
        const removedToast = toastQueue.shift();
        if (container.contains(removedToast)) {
            container.removeChild(removedToast);
        }
    }

    setTimeout(() => {
        toast.remove();
    }, 4000);
}
