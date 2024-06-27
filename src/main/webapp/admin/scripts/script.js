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
    for (const key in tableData.data[0]) {
        const th = document.createElement('th');
        th.textContent = key;
        hr.appendChild(th);
    }

    if (haveActions) {
        const th = document.createElement('th');
        th.textContent = 'Azioni';
        hr.appendChild(th);
    }

    thead.appendChild(hr);

    for (const record of tableData.data) {
        const tr = document.createElement('tr');
        for (const key in record) {
            const td = document.createElement('td');
            td.textContent = record[key];
            tr.appendChild(td);
        }

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

        tbody.appendChild(tr);
    }

    table.appendChild(thead);
    table.appendChild(tbody);

    return table;
}

function updateTable(tableData) {
    const mainTable = document.getElementById('main-table');

    const oldTable = mainTable.querySelector('table');
    const newTable = buildTable(tableData);

    if (oldTable) {
        mainTable.replaceChild(newTable, oldTable);
    } else {
        mainTable.appendChild(newTable);
    }
}

function getTableData() {
    const requestParams = document.getElementById('table-selector').value.split("-");

    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'visible';

    // TODO Set timeout slide 43
    const ajaxTableDataRequest = new XMLHttpRequest();
    ajaxTableDataRequest.onreadystatechange = function () {
        if (ajaxTableDataRequest.readyState === 4) {
             if (ajaxTableDataRequest.status === 200) {
                 updateTable(JSON.parse(ajaxTableDataRequest.responseText));
                 loadingOverlay.style.visibility = 'hidden';
             }
        }
    }

    // TODO Capire come recuperare la prima parte dell'url fino a seedify_war compreso
    const url = `${window.location.origin}/seedify_war/admin-servlet?action=${requestParams[0]}&fields=${requestParams[1]}`;
    console.log(url);
    ajaxTableDataRequest.open("get", url, true);
    ajaxTableDataRequest.send(null);
}

getTableData()
