import { getAjaxRequestObject } from '../../common/general/scripts/script.js';

window.getTableData = getTableData;

function showLoadingOverlay() {
    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'visible';
}

function hideLoadingOverlay() {
    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'hidden';
}

function sendEditRequest(target) {
    console.log(target.value)
}

function sendDeleteRequest(target) {
    showLoadingOverlay();

    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
            if (ajaxTableDataRequest.status === 200) {
                getTableData()
                hideLoadingOverlay();
            }
        }
    }

    const params = target.value.split("&");
    const body = {
        action: params[0].replace("action=", ""),
        entity_primary_key: params[1].replace("entity_primary_key=", "")
    }

    const url = `${window.location.origin}/seedify_war/admin-servlet?${target.value}`;
    ajaxTableDataRequest.open("delete", url, true);
    ajaxTableDataRequest.send(JSON.stringify(body));
}

function buildTable(tableData) {
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
        if (!(key in blackList)) {
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
                const editActionValue = `action=${tableData['editCall']}&entity_primary_key=${record['entity_primary_key']}`;

                const editAction = document.createElement('button');
                editAction.classList.add("material-button")
                editAction.value = editActionValue;

                const span = document.createElement('span');
                span.classList.add("material-icons-round", "md-18");
                span.value = editActionValue;
                span.innerHTML = "edit";

                editAction.onclick = function (event) {
                    // TODO Edit data
                    sendEditRequest(event.target);
                }
                editAction.appendChild(span);

                td.appendChild(editAction);
            }

            if (canDelete) {
                const deleteActionValue = `action=${tableData['deleteCall']}&entity_primary_key=${record['entity_primary_key']}`;

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
            if ((key in blackList)) {
                continue;
            }

            const td = document.createElement('td');
            if (record[key].startsWith("image/")) {
                const resourceParams = record[key].replace("image/", "");
                const img = document.createElement('img');
                img.classList.add('table-image');
                img.src = `${window.location.origin}/seedify_war/resources-servlet?${resourceParams}`
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

function updateTable(tableData) {
    const mainTable = document.getElementById('main-table');

    let oldTableElement = mainTable.querySelector('table');
    if (oldTableElement === null) {
        oldTableElement = mainTable.querySelector('p');
    }

    let newTableElement = null;
    if (tableData['data'].length !== 0) {
        newTableElement = buildTable(tableData);
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

function getTableData() {
    const requestParams = document.getElementById('table-selector').value.split("-");

    showLoadingOverlay();

    // TODO Set timeout slide 43
    const ajaxTableDataRequest = getAjaxRequestObject();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
             if (ajaxTableDataRequest.status === 200) {
                 const tableData = JSON.parse(ajaxTableDataRequest.responseText);
                 updateTable(tableData);
                 hideLoadingOverlay();
             }
        }
    }

    // TODO Capire come recuperare la prima parte dell'url fino a seedify_war compreso
    const url = `${window.location.origin}/seedify_war/admin-servlet?action=${requestParams[0]}&fields=${requestParams[1]}`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

getTableData()
