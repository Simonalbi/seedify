import { getBaseOriginName, resolveResource, sendAjaxRequest } from '../../common/general/scripts/script.js';
import { showEditProduct } from '../../common/components/edit-product/scripts/script.js';
import { toast } from "../../common/general/scripts/toast.js";

window.getTableData = getTableData;

/**
 * Shows the edit credit card modal.
 */
function showLoadingOverlay() {
    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'visible';
}

/**
 * Hides the edit credit card modal.
 */
function hideLoadingOverlay() {
    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'hidden';
}

/**
 * Sends a delete request to the server.
 * @param {HTMLButtonElement} target - The button that triggered the event.
 */
function sendDeleteRequest(target) {
    showLoadingOverlay();

    // http://.../<servlet>?action=<action>&entity_primary_key=<primary_key>
    const url = `${getBaseOriginName()}/${target.value}`;
    sendAjaxRequest(
        "DELETE",
        url,
        null,
        {
            200: function () {
                getTableData();
                hideLoadingOverlay();
            }
        }
    )
}

/**
 * Builds a table element from the given data.
 * @param {Object} tableData - The data to build the table from.
 * @param {Function} onEdit - The function to call when the edit button is clicked.
 * @param {string} recordIdentifier - The identifier of the record.
 */
function buildTable(tableData, onEdit, recordIdentifier) {
    const canEdit = tableData['canEdit'];
    const canDelete = tableData['canDelete'];
    const haveActions = canEdit || canDelete;
    const blackList = ["entity_primary_key"]

    const table = document.createElement('table');

    const thead = document.createElement('thead');
    thead.classList.add('rubik-300');

    const tbody = document.createElement('tbody');
    tbody.classList.add('rubik-300');

    const hr = document.createElement('tr');
    if (haveActions) {
        const th = document.createElement('th');
        th.textContent = 'Azioni';
        hr.appendChild(th);
    }

    for (let key in tableData.data[0]) {
        if (!blackList.includes(key)) {
            const th = document.createElement('th');
            key = key.replaceAll("_", " ");
            th.textContent = key.charAt(0).toUpperCase() + key.slice(1);
            hr.appendChild(th);
        }
    }

    thead.appendChild(hr);

    for (const record of tableData.data) {
        const tr = document.createElement('tr');
        if (haveActions) {
            const td = document.createElement('td');
            td.classList.add("table-actions")

            if (canEdit) {
                const editActionValue = `${tableData['editCall']}&entity_primary_key=${record['entity_primary_key']}`;

                const editAction = document.createElement('button');
                editAction.classList.add("material-button")
                editAction.value = editActionValue;

                const span = document.createElement('span');
                span.classList.add("material-icons-round", "md-18");
                span.value = editActionValue;
                span.innerHTML = "edit";

                if (onEdit !== null) {
                    editAction.onclick = function (event) {
                        onEdit(record[recordIdentifier]);
                    }
                }
                editAction.appendChild(span);

                td.appendChild(editAction);
            }

            if (canDelete) {
                const deleteActionValue = `${tableData['deleteCall']}&entity_primary_key=${record['entity_primary_key']}`;

                const deleteAction = document.createElement('button');
                deleteAction.classList.add("material-button")
                deleteAction.value = deleteActionValue;

                const span = document.createElement('span');
                span.classList.add("material-icons-round", "md-18");
                span.value = deleteActionValue
                span.innerHTML = "delete"

                deleteAction.onclick = function (event) {
                    sendDeleteRequest(event.target)
                }
                deleteAction.appendChild(span);

                td.appendChild(deleteAction);
            }

            tr.appendChild(td);
        }

        for (const key in record) {
            if (blackList.includes(key)) {
                continue;
            }

            const td = document.createElement('td');
            const resolvedResource = resolveResource(record[key]);
            if (resolvedResource.image !== null) {
                const img = document.createElement('img');
                img.src = resolvedResource.image;
                img.classList.add('table-image');
                td.appendChild(img);
            } else {
                td.textContent = record[key];
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }

    table.appendChild(thead);
    table.appendChild(tbody);

    return table;
}

/**
 * Updates the table with the given data.
 * @param {Object} tableData - The data to update the table width.
 */
function updateTable(tableData) {
    const mainTable = document.getElementById('main-table');

    let oldTableElement = mainTable.querySelector('table');
    if (oldTableElement === null) {
        oldTableElement = mainTable.querySelector('p');
    }

    let newTableElement = null;
    if (tableData['data'].length !== 0) {
        let onEdit = null;
        let recordIdentifier = "";
        if (tableData['data_name'] === "all_saved_products") {
            onEdit = showEditProduct;
            recordIdentifier = "entity_primary_key";
        } else if (tableData['data_name'] === "user_credit_cards") {
            onEdit = showEditCreditCard;
        }

        newTableElement = buildTable(tableData, onEdit, recordIdentifier);
    } else {
        newTableElement = document.createElement('p');
        newTableElement.classList.add('rubik-300', 'no-results-message');
        newTableElement.textContent = 'Nessun risultato trovato!';
    }

    if (oldTableElement) {
        mainTable.replaceChild(newTableElement, oldTableElement);
    } else {
        mainTable.appendChild(newTableElement);
    }
}

/**
 * Gets the data to populate the table.
 */
function getTableData() {
    const requestParams = document.getElementById('table-selector').value.split("-");

    showLoadingOverlay();

    const url = `${getBaseOriginName()}/user-servlet?action=${requestParams[0]}&fields=${requestParams[1]}`;
    sendAjaxRequest(
        "GET",
        url,
        null,
        {
            200: function (response) {
                const tableData = JSON.parse(response);
                updateTable(tableData);
                hideLoadingOverlay();
            },
            defaultCallback: function () {
                toast("Errore durante il recupero dei dati", "ERROR");
                hideLoadingOverlay();
            }
        }
    )
}

getTableData()
