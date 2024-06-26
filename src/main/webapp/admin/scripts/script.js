const EMPLOYEES = [
    {
        "Nome": "Mario",
        "Cognome": "Rossi",
        "Email": "mario.rossi@example.com"
    },
    {
        "Nome": "Luigi",
        "Cognome": "Bianchi",
        "Email": "luigi.bianchi@example.com"
    },
    {
        "Nome": "Giulia",
        "Cognome": "Verdi",
        "Email": "giulia.verdi@example.com"
    },
    {
        "Nome": "Marco",
        "Cognome": "Neri",
        "Email": "marco.neri@example.com"
    },
    {
        "Nome": "Sara",
        "Cognome": "Gialli",
        "Email": "sara.gialli@example.com"
    },
    {
        "Nome": "Alessandro",
        "Cognome": "Marroni",
        "Email": "alessandro.marroni@example.com"
    },
    {
        "Nome": "Francesca",
        "Cognome": "Rossi",
        "Email": "francesca.rossi@example.com"
    },
    {
        "Nome": "Giovanni",
        "Cognome": "Colombo",
        "Email": "giovanni.colombo@example.com"
    },
    {
        "Nome": "Anna",
        "Cognome": "Ricci",
        "Email": "anna.ricci@example.com"
    },
    {
        "Nome": "Paolo",
        "Cognome": "Ferrari",
        "Email": "paolo.ferrari@example.com"
    },
    {
        "Nome": "Laura",
        "Cognome": "Esposito",
        "Email": "laura.esposito@example.com"
    },
    {
        "Nome": "Davide",
        "Cognome": "Conti",
        "Email": "davide.conti@example.com"
    },
    {
        "Nome": "Martina",
        "Cognome": "Costa",
        "Email": "martina.costa@example.com"
    },
    {
        "Nome": "Stefano",
        "Cognome": "Barbieri",
        "Email": "stefano.barbieri@example.com"
    },
    {
        "Nome": "Elisa",
        "Cognome": "Moretti",
        "Email": "elisa.moretti@example.com"
    },
    {
        "Nome": "Andrea",
        "Cognome": "Greco",
        "Email": "andrea.greco@example.com"
    },
    {
        "Nome": "Chiara",
        "Cognome": "Marini",
        "Email": "chiara.marini@example.com"
    },
    {
        "Nome": "Leonardo",
        "Cognome": "Romano",
        "Email": "leonardo.romano@example.com"
    },
    {
        "Nome": "Roberta",
        "Cognome": "Rinaldi",
        "Email": "roberta.rinaldi@example.com"
    }
]

const USERS = [
    {
        "Nome": "Mario",
        "Cognome": "Rossi",
        "Email": "mario.rossi@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Luigi",
        "Cognome": "Bianchi",
        "Email": "luigi.bianchi@example.com",
        "Ordini effettuati": 2
    },
    {
        "Nome": "Giulia",
        "Cognome": "Verdi",
        "Email": "giulia.verdi@example.com",
        "Ordini effettuati": 2
    },
    {
        "Nome": "Marco",
        "Cognome": "Neri",
        "Email": "marco.neri@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Sara",
        "Cognome": "Gialli",
        "Email": "sara.gialli@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Alessandro",
        "Cognome": "Marroni",
        "Email": "alessandro.marroni@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Francesca",
        "Cognome": "Rossi",
        "Email": "francesca.rossi@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Giovanni",
        "Cognome": "Colombo",
        "Email": "giovanni.colombo@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Anna",
        "Cognome": "Ricci",
        "Email": "anna.ricci@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Paolo",
        "Cognome": "Ferrari",
        "Email": "paolo.ferrari@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Laura",
        "Cognome": "Esposito",
        "Email": "laura.esposito@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Davide",
        "Cognome": "Conti",
        "Email": "davide.conti@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Martina",
        "Cognome": "Costa",
        "Email": "martina.costa@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Stefano",
        "Cognome": "Barbieri",
        "Email": "stefano.barbieri@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Elisa",
        "Cognome": "Moretti",
        "Email": "elisa.moretti@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Andrea",
        "Cognome": "Greco",
        "Email": "andrea.greco@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Chiara",
        "Cognome": "Marini",
        "Email": "chiara.marini@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Leonardo",
        "Cognome": "Romano",
        "Email": "leonardo.romano@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Roberta",
        "Cognome": "Rinaldi",
        "Email": "roberta.rinaldi@example.com",
        "Ordini effettuati": 1
    },
    {
        "Nome": "Giorgio",
        "Cognome": "Ferri",
        "Email": "giorgio.ferri@example.com",
        "Ordini effettuati": 1
    }
]

const ORDERS = [
    {
        "Codice": "ORD001",
        "Data Ordine": "2024-06-01",
        "Consegna": "2024-06-05",
        "Num. Carta": "1234-5678-9012-3456",
        "Indirizzo": "Via Roma, 10, Milano"
    },
    {
        "Codice": "ORD002",
        "Data Ordine": "2024-06-02",
        "Consegna": "2024-06-06",
        "Num. Carta": "2345-6789-0123-4567",
        "Indirizzo": "Via Milano, 20, Roma"
    },
    {
        "Codice": "ORD003",
        "Data Ordine": "2024-06-03",
        "Consegna": "2024-06-07",
        "Num. Carta": "3456-7890-1234-5678",
        "Indirizzo": "Corso Italia, 30, Napoli"
    },
    {
        "Codice": "ORD004",
        "Data Ordine": "2024-06-04",
        "Consegna": "2024-06-08",
        "Num. Carta": "4567-8901-2345-6789",
        "Indirizzo": "Piazza Duomo, 40, Firenze"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    },
    {
        "Codice": "ORD005",
        "Data Ordine": "2024-06-05",
        "Consegna": "2024-06-09",
        "Num. Carta": "5678-9012-3456-7890",
        "Indirizzo": "Via Garibaldi, 50, Torino"
    }
]

const PRODUCTS = [
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    },
    {
        "Immagine": "",
        "Nome": "pianta",
        "Data": "2024-06-20",
        "Tipologia": "grassa",
        "Descrizione": "Pianta molto bella"
    }
]

function buildTable(tableData) {
    const canEdit = tableData.canEdit;
    const canDelete = tableData.canDelete;
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
    const loadingOverlay = document.getElementById('table-loading-overlay');
    loadingOverlay.style.visibility = 'visible';

    // TODO Request table data with AJAX and show loading circle during the request
    // Simulate a delay
    setTimeout(() => {loadingOverlay.style.visibility = 'hidden'}, 1000);

    const response = {
        canEdit: false,
        canDelete: false,
        data: []
    }

    const selectedTable = document.getElementById('table-selector').value;
    if (selectedTable === 'employees') {
        response.data = EMPLOYEES
    } else if (selectedTable === 'users') {
        response.data = USERS
    } else if (selectedTable === 'orders') {
        response.data = ORDERS
    } else if (selectedTable === 'products') {
        response.canEdit = true
        response.canDelete = true;
        response.data = PRODUCTS
    }

    updateTable(response)
}

const response = {
    canEdit: false,
    canDelete: false,
    data: EMPLOYEES
}
updateTable(response);
