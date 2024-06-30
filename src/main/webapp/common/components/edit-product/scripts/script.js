document.addEventListener('DOMContentLoaded', () => {
    const dropZone = document.getElementById('edit-product-image-container');
    const fileInput = document.getElementById('file-input');
    const fileList = document.getElementById('file-list');

    dropZone.addEventListener('click', () => {
        fileInput.click();
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
        handleFiles(files);
    });

    fileInput.addEventListener('change', () => {
        const files = Array.from(fileInput.files);
        handleFiles(files);
    });

    function handleFiles(files) {
        files.forEach(file => {
            const listItem = document.createElement('div');
            listItem.textContent = file.name;
            fileList.appendChild(listItem);
        });
    }
});