DROP DATABASE IF EXISTS seedify;
CREATE SCHEMA seedify;
USE seedify;

CREATE TABLE prodotti (
    codice_prodotto INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    immagine MEDIUMBLOB NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    quantita INT NOT NULL,
    stagionalita ENUM("INVERNO", "PRIMAVERA", "ESTATE", "AUTUNNO") NOT NULL,
    quantita_acqua ENUM("POCA", "NORMALE", "MOLTA") NOT NULL,
    tipologia_pianta VARCHAR(50) NOT NULL,
    descrizione TINYTEXT NOT NULL,
    data_aggiunta DATE NOT NULL,
    stato ENUM("ATTIVO", "ELIMINATO") NOT NULL DEFAULT "ATTIVO",

    PRIMARY KEY(codice_prodotto)
);

CREATE TABLE indirizzi (
    codice_indirizzo INT AUTO_INCREMENT NOT NULL,
    provincia VARCHAR(50) NOT NULL,
    citta VARCHAR(50) NOT NULL,
    cap CHAR(5) NOT NULL,
    via VARCHAR(80) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    numero_di_telefono VARCHAR(15) NOT NULL,
    note TINYTEXT,

    PRIMARY KEY(codice_indirizzo)
);

CREATE TABLE utenti (
    email VARCHAR(100) NOT NULL,
    password VARCHAR(64) NOT NULL,
    foto_profilo MEDIUMBLOB NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    ruolo ENUM("AMMINISTRATORE", "DIPENDENTE", "CLIENTE") NOT NULL,

    PRIMARY KEY(email)
);

CREATE TABLE carte_di_credito (
    numero_carta CHAR(16) NOT NULL,
    cvv CHAR(3) NOT NULL,
    scadenza DATE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,

    PRIMARY KEY(numero_carta, cvv, scadenza, nome, cognome)
);

CREATE TABLE ordini (
    codice_ordine INT AUTO_INCREMENT NOT NULL,
    codice_indirizzo INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    numero_carta CHAR(16) NOT NULL,
    cvv CHAR(3) NOT NULL,
    scadenza DATE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    data_ordine DATE NOT NULL,
    data_consegna DATE,
    prezzo_totale DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY(codice_ordine),

    FOREIGN KEY(codice_indirizzo) REFERENCES indirizzi(codice_indirizzo),
    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,
    FOREIGN KEY(numero_carta, cvv, scadenza, nome, cognome) REFERENCES carte_di_credito(numero_carta, cvv, scadenza, nome, cognome)
);

CREATE TABLE merce (
    codice_ordine INT NOT NULL,
    codice_prodotto INT NOT NULL,
    quantita INT NOT NULL,

    PRIMARY KEY(codice_ordine, codice_prodotto),

    FOREIGN KEY(codice_ordine) REFERENCES ordini(codice_ordine),
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto)
);

CREATE TABLE memorizzazioni (
    email VARCHAR(100) NOT NULL,
    numero_carta CHAR(16) NOT NULL,
    cvv CHAR(3) NOT NULL,
    scadenza DATE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    stato ENUM("ATTIVO", "ELIMINATO") NOT NULL DEFAULT "ATTIVO",

    PRIMARY KEY(email, numero_carta, cvv, scadenza, nome, cognome),

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,
    FOREIGN KEY(numero_carta, cvv, scadenza, nome, cognome) REFERENCES carte_di_credito(numero_carta, cvv, scadenza, nome, cognome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE locazione (
    email VARCHAR(100) NOT NULL,
    codice_indirizzo INT NOT NULL,

    PRIMARY KEY(email, codice_indirizzo),

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,
    FOREIGN KEY(codice_indirizzo) REFERENCES indirizzi(codice_indirizzo) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE recensioni (
    email VARCHAR(100) NOT NULL,
    codice_prodotto INT NOT NULL,
    commento TINYTEXT NOT NULL,
    numero_stelle TINYINT NOT NULL,
    data_aggiunta DATE NOT NULL,

    PRIMARY KEY(codice_prodotto, email),

    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,

    CONSTRAINT stelle CHECK(numero_stelle BETWEEN 1 AND 5)
);

CREATE TABLE carrelli (
    email VARCHAR(100) NOT NULL,
    codice_prodotto INT NOT NULL,
    quantita INT NOT NULL,

    PRIMARY KEY(email, codice_prodotto),

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE preferiti (
    email VARCHAR(100) NOT NULL,
    codice_prodotto INT NOT NULL,

    PRIMARY KEY(email, codice_prodotto),

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE,
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE
);
