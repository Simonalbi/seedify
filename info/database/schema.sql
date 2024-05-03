DROP DATABASE IF EXISTS seedify;
CREATE SCHEMA seedify;
USE seedify;

CREATE TABLE prodotti (
	codice_prodotto VARCHAR(7) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    immagine MEDIUMBLOB NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    quantita INT NOT NULL, 
    stagionalita ENUM("inverno", "primavera", "estate", "autunno") NOT NULL,
    quantita_acqua INT NOT NULL,
    tipologia_pianta VARCHAR(50) NOT NULL,
    descrizione VARCHAR(250) NOT NULL,
    data_aggiunta DATE NOT NULL,
    
    PRIMARY KEY(codice_prodotto)
);

CREATE TABLE indirizzi (
	codice_indirizzo VARCHAR(7) NOT NULL,
    provincia VARCHAR(50) NOT NULL,
    citta VARCHAR(50) NOT NULL,
    cap INT(5) NOT NULL,
    via VARCHAR(80) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    numero_di_telefono VARCHAR(15) NOT NULL,
    note VARCHAR(250) NOT NULL,
    
    PRIMARY KEY(codice_indirizzo)
);

CREATE TABLE utenti (
	email VARCHAR(100) NOT NULL,
    password VARCHAR(64) NOT NULL,
    foto_profilo MEDIUMBLOB NOT NULL,
    tipologia ENUM("admin", "cliente", "dipendente") NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    telefono VARCHAR(15),
    
    PRIMARY KEY(email)
);

CREATE TABLE carte_di_credito (
	numero_carta VARCHAR(64) NOT NULL,
    cvv VARCHAR(64) NOT NULL,
    scadenza VARCHAR(64) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    
    PRIMARY KEY(numero_carta, cvv, scadenza, nome, cognome)
);

CREATE TABLE ordini (
	codice_ordine VARCHAR(7) NOT NULL,
    codice_indirizzo VARCHAR(7) NOT NULL,
    email VARCHAR(100) NOT NULL,
    numero_carta VARCHAR(64) NOT NULL,
    cvv VARCHAR(64) NOT NULL,
    scadenza VARCHAR(64) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    data_ordine DATE NOT NULL,
    data_consegna DATE NOT NULL,
    prezzo_totale DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY(codice_ordine),
    
    FOREIGN KEY(codice_indirizzo) REFERENCES indirizzi(codice_indirizzo) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(numero_carta, cvv, scadenza, nome, cognome) REFERENCES carte_di_credito(numero_carta, cvv, scadenza, nome, cognome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE merce (
	codice_ordine VARCHAR(7) NOT NULL,
    codice_prodotto VARCHAR(7) NOT NULL,
    
    PRIMARY KEY(codice_ordine, codice_prodotto),
    
    FOREIGN KEY(codice_ordine) REFERENCES ordini(codice_ordine) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE memorizzazione (
	email VARCHAR(100) NOT NULL,
    numero_carta VARCHAR(64) NOT NULL,
    cvv VARCHAR(64) NOT NULL,
    scadenza VARCHAR(64) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    
    PRIMARY KEY(email, numero_carta, cvv, scadenza, nome, cognome),
    
    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(numero_carta, cvv, scadenza, nome, cognome) REFERENCES carte_di_credito(numero_carta, cvv, scadenza, nome, cognome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE locazione (
	email VARCHAR(100) NOT NULL,
    codice_indirizzo VARCHAR(7) NOT NULL,
    
    PRIMARY KEY(email, codice_indirizzo),
    
    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_indirizzo) REFERENCES indirizzi(codice_indirizzo) ON UPDATE CASCADE ON DELETE CASCADE
);
