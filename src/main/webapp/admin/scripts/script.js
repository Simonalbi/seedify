// TODO Inserire solo alcune colonne
// TODO In caso di 0 risultati far uscire un messaggio

function buildTable(tableData) {
    const canEdit = tableData['canEdit'];
    const canDelete = tableData['canDelete'];
    const haveActions = canEdit || canDelete;

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
        const th = document.createElement('th');
        key = key.replaceAll("_", " ");
        th.textContent = key.charAt(0).toUpperCase() + key.slice(1);
        hr.appendChild(th);
    }

    thead.appendChild(hr);

    for (const record of tableData.data) {
        const tr = document.createElement('tr');
        if (haveActions) {
            const td = document.createElement('td');
            td.classList.add("table-actions")

            if (canEdit) {
                const editAction = document.createElement('div');
                editAction.innerHTML = '<span class="material-icons-round md-18">edit</span>';
                td.appendChild(editAction);
            }

            if (canDelete) {
                const deleteAction = document.createElement('div');
                deleteAction.innerHTML = '<span class="material-icons-round md-18">delete</span>';
                td.appendChild(deleteAction);
            }

            tr.appendChild(td);
        }

        for (const key in record) {
            const td = document.createElement('td');
            td.textContent = record[key];
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

    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'visible';

    // TODO Costruire richiesta ajax in base al motore di ricerca
    // TODO Set timeout slide 43
    const ajaxTableDataRequest = new XMLHttpRequest();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
             if (ajaxTableDataRequest.status === 200) {
                 const tableData = JSON.parse(ajaxTableDataRequest.responseText);
                 updateTable(tableData);
                 loadingOverlay.style.visibility = 'hidden';
             }
        }
    }

    // TODO Capire come recuperare la prima parte dell'url fino a seedify_war compreso
    const url = `${window.location.origin}/seedify_war/admin-servlet?action=${requestParams[0]}&fields=${requestParams[1]}`;
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

getTableData()
