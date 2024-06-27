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
    FOREIGN KEY(email) REFERENCES utenti(email),
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

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE preferiti (
    email VARCHAR(100) NOT NULL,
    codice_prodotto INT NOT NULL,

    PRIMARY KEY(email, codice_prodotto),

    FOREIGN KEY(email) REFERENCES utenti(email) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_prodotto) REFERENCES prodotti(codice_prodotto) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO utenti (email, password, foto_profilo, nome, cognome, ruolo)
VALUES ('v.ferrentino10@studenti.unisa.it',
        '8539d14b5b720efb143fa9bd9bf1146e8de45647b252e42fe230a707b5f9f6db',
        '...',
        'Valentina',
        'Ferrentino',
        'AMMINISTRATORE');

INSERT INTO utenti (email, password, foto_profilo, nome, cognome, ruolo)
VALUES ('s.albino2@studenti.unisa.it',
        'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090',
        '...',
        'Simone Francesco',
        'Albino',
        'AMMINISTRATORE');

INSERT INTO utenti (email, password, foto_profilo, nome, cognome, ruolo) VALUES
     ('mario@example.com', 'hashed_password', '...', 'Mario', 'Rossi', 'CLIENTE'),

     ('mario.rossi@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Mario', 'Rossi', 'DIPENDENTE'),
     ('luigi.bianchi@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Luigi', 'Bianchi', 'DIPENDENTE'),
     ('giulia.verdi@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Giulia', 'Verdi', 'DIPENDENTE'),
     ('marco.neri@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Marco', 'Neri', 'DIPENDENTE'),
     ('sara.gialli@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Sara', 'Gialli', 'DIPENDENTE'),
     ('alessandro.marroni@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Alessandro', 'Marroni', 'DIPENDENTE'),
     ('francesca.rossi@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Francesca', 'Rossi', 'DIPENDENTE'),
     ('giovanni.colombo@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Giovanni', 'Colombo', 'DIPENDENTE'),
     ('anna.ricci@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Anna', 'Ricci', 'DIPENDENTE'),
     ('paolo.ferrari@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Paolo', 'Ferrari', 'DIPENDENTE'),
     ('laura.esposito@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Laura', 'Esposito', 'DIPENDENTE'),
     ('davide.conti@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Davide', 'Conti', 'DIPENDENTE'),
     ('martina.costa@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Martina', 'Costa', 'DIPENDENTE'),
     ('stefano.barbieri@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Stefano', 'Barbieri', 'DIPENDENTE'),
     ('elisa.moretti@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Elisa', 'Moretti', 'DIPENDENTE'),
     ('andrea.greco@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Andrea', 'Greco', 'DIPENDENTE'),
     ('chiara.marini@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Chiara', 'Marini', 'DIPENDENTE'),
     ('leonardo.romano@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Leonardo', 'Romano', 'DIPENDENTE'),
     ('roberta.rinaldi@example.com', 'b302755195decc9bc98dcbdfb3126d84ffa431d500b44fa5e850f642ab5d4090', '...', 'Roberta', 'Rinaldi', 'DIPENDENTE');

INSERT INTO indirizzi (provincia, citta, cap, via, nome, cognome, numero_di_telefono, note) VALUES
    ('MI', 'Milano', '20100', 'Via Roma 1', 'Mario', 'Rossi', '+39 02 1234567', 'Residenza principale');

INSERT INTO carte_di_credito (numero_carta, cvv, scadenza, nome, cognome) VALUES
    ('1234567890123456', '123', '2025-12-31', 'Mario', 'Rossi');

INSERT INTO prodotti (nome, immagine, prezzo, quantita, stagionalita, quantita_acqua, tipologia_pianta, descrizione, data_aggiunta) VALUES
    ('Orchidea bianca', '...', 39.99, 10, 'ESTATE', 'NORMALE', 'Orchidea', 'Elegante orchidea bianca, ideale per decorare interni.', CURDATE());

INSERT INTO ordini (codice_indirizzo, email, numero_carta, cvv, scadenza, nome, cognome, data_ordine, prezzo_totale) VALUES
    (1, 'mario@example.com', '1234567890123456', '123', '2025-12-31', 'Mario', 'Rossi', CURDATE(), 39.99);

INSERT INTO merce (codice_ordine, codice_prodotto, quantita) VALUES
    (LAST_INSERT_ID(), 1, 1);
