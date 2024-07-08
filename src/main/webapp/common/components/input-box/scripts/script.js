function togglePassword(formId) {
    const passwordForm = document.getElementById(formId);
    const passwordIcon = document.getElementById(`${formId}-toggle-icon`);
    if (passwordForm.type === "password") {
        passwordForm.type = "text";
        passwordIcon.innerHTML = "visibility_off";
    } else {
        passwordForm.type = "password";
        passwordIcon.innerHTML = "visibility";
    }
}

function enableSubmitButton(group) {
    const submitButton = document.querySelectorAll(`input[type="submit"].${group}`)[0]
    submitButton.disabled = false;
}

function disableSubmitButton(group) {
    const submitButton = document.querySelectorAll(`input[type="submit"].${group}`)[0]
    submitButton.disabled = true;
}

// TODO Permettere di inserire un solo file per volta
/**
 * Handle the files that have been dropped or selected
 * @param {Array} files - The list of files that have been dropped or selected
 * @param {HTMLElement} fileListContainer - The container where the list of files will be displayed
 */
function handleFiles(files, fileListContainer) {
    files.forEach(file => {
        const listItem = document.createElement('div');
        listItem.textContent = file.name;
        fileListContainer.appendChild(listItem);
    });
}

document.addEventListener("input", (event) => {
    const classesArray = Array.from(event.target.classList);
    const group = classesArray.find(c => c.endsWith("-input-box-group"));

    let isFormFilled = true;
    let isFormValid = true;

    const inputBoxes = document.getElementsByClassName("input-box");
    for (let i = 0; i < inputBoxes.length; i++) {
        const input = inputBoxes[i].querySelector("input, textarea");
        if (input.classList.contains(group)) {
            isFormFilled = isFormFilled && input.value !== "";
            isFormValid = isFormValid && input.checkValidity();
        }
    }

    if (isFormFilled && isFormValid) {
        enableSubmitButton(group);
    } else {
        disableSubmitButton(group);
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const dropZones = document.getElementsByClassName("file-drag-and-drop-container");

    Array.from(dropZones).forEach(dropZone => {
        const dropZoneFileInput = dropZone.querySelector('input[type="file"]');
        const fileListContainer = document.getElementById(`${dropZoneFileInput.id}-file-list`)

        dropZone.addEventListener('click', () => {
            dropZoneFileInput.click();
        });

        dropZone.addEventListener('dragover', (e) => {
            e.preventDefault();
            dropZone.classList.add('dragover');
        });

        dropZone.addEventListener('dragleave', () => {
            dropZone.classList.remove('dragover');
        });

        dropZone.addEventListener('drop', (e) => {
            e.preventDefault();
            dropZone.classList.remove('dragover');
            const files = Array.from(e.dataTransfer.files);
            handleFiles(files, fileListContainer);
        });

        dropZoneFileInput.addEventListener('change', () => {
            const files = Array.from(dropZoneFileInput.files);
            handleFiles(files, fileListContainer);
        });
    });
});