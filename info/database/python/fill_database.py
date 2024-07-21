from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from Crypto.Random import get_random_bytes
import base64

import mysql.connector
import random
import datetime
import hashlib

DATABASE_NAME = "seedify"
dbConfig = {
    "user": "root",
    "password": "06102003",
    "host": "localhost",
}

USERS_AMOUNT = 100
ADDRESSES_AMOUNT = 90
PRODUCTS_AMOUNT = 300
CREDIT_CARDS_AMOUNT = 70

ENCRYPTION_KEY = '8!\)r9bg$/hH?[RcF5cqBc4d9bm01h%A'

def encrypt(data, key):
  if len(key) not in (16, 24, 32):
    raise ValueError("Encryption key must be 16, 24, or 32 bytes long")
  
  secret_key = key.encode()
  iv = get_random_bytes(AES.block_size)
  cipher = AES.new(secret_key, AES.MODE_CBC, iv)
  padded_data = pad(data.encode(), AES.block_size)
  encrypted_data = cipher.encrypt(padded_data)
  combined_data = iv + encrypted_data
  encoded_data = base64.b64encode(combined_data).decode('utf-8')

  return encoded_data

# ------------------- RANDOM FUNCTIONS -------------------
def generateRandomFirstName() -> str:
    firstNames = [
        "John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Isabella", "Alexander", "Ava",
        "Liam", "Mia", "Noah", "Charlotte", "Lucas", "Amelia", "Mason", "Harper", "Ethan", "Evelyn",
        "Logan", "Abigail", "Elijah", "Emily", "Benjamin", "Ella", "Lucas", "Madison", "Henry", "Scarlett",
        "Sebastian", "Aria", "Jack", "Grace", "Owen", "Chloe", "Aiden", "Lily", "Samuel", "Sofia"
    ]
    return random.choice(firstNames)

def generateRandomLastName() -> str:
    lastNames = [
        "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
        "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
        "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King",
        "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter"
    ]
    return random.choice(lastNames)

def generateRandomProductName() -> str:
    productNamesOptions = [
        "Abete rosso", "Acacia", "Agrifoglio", "Alloro", "Aneto", "Arancio", "Basilico", "Betulla", "Bonsai", "Bosso",
        "Bucaneve", "Calendula", "Camomilla", "Canna", "Cappero", "Ciliegio", "Cipresso", "Clematide", "Corbezzolo", "Cotoneaster",
        "Edera", "Elicriso", "Erba cipollina", "Felce", "Fiordaliso", "Fragola", "Frassino", "Gelsomino", "Gelsomino notturno", "Geranio",
        "Ginepro", "Girasole", "Ibisco", "Iris", "Lauroceraso", "Lavanda", "Lilla", "Limoncello", "Magnolia", "Menta",
        "Mimosa", "Mirto", "Mughetto", "Narciso", "Oleandro", "Olivo", "Orchidea", "Ortensia", "Pino", "Pisello odoroso",
        "Primula", "Quercia", "Ribes", "Rosa", "Rosmarino", "Salice", "Salvia", "Tiglio", "Tulipano", "Ulivo",
        "Verbena"
    ]
    return random.choice(productNamesOptions)

def generateRandomPlantType() -> str:
    plantTypeOptions = [
        "Pianta da interno", "Pianta da esterno", "Pianta grassa", "Pianta fiorita",
        "Pianta arbustiva", "Pianta rampicante", "Pianta ornamentale", "Pianta da frutto",
        "Pianta acquatica", "Pianta erbacea", "Pianta perenne", "Pianta annuale",
        "Pianta biennale", "Pianta medicinale", "Pianta aromatiche", "Pianta carnivora",
        "Pianta esotica", "Pianta succulenta", "Pianta tropicale", "Pianta alpina",
        "Pianta sempreverde", "Pianta decidua", "Pianta boschiva", "Pianta da siepe",
        "Pianta tappezzante", "Pianta ombrosa", "Pianta da balcone", "Pianta palustre",
        "Pianta da appartamento", "Pianta bonsai", "Pianta rampicante da interno", "Pianta rampicante da esterno",
        "Pianta pendente", "Pianta a foglia larga", "Pianta a foglia stretta", "Pianta a foglia variegata",
        "Pianta a fiore unico", "Pianta a fiori multipli", "Pianta profumata", "Pianta autoctona",
        "Pianta rara", "Pianta commestibile", "Pianta da spezia", "Pianta decorativa"
    ]
    return random.choice(plantTypeOptions)

def generateRandomDescription() -> str:
    descriptionOptions = [
        "Questa pianta da interno è perfetta per aggiungere un tocco di verde al tuo soggiorno. Richiede poca manutenzione e cresce bene in ambienti con luce indiretta.",
        "Una pianta da esterno resistente che può sopportare diverse condizioni climatiche. Ideale per giardini e spazi aperti, fiorisce splendidamente in primavera.",
        "Pianta grassa ideale per chi ha poco tempo da dedicare al giardinaggio. Richiede pochissima acqua e può prosperare in quasi tutti gli ambienti.",
        "Questa pianta fiorita aggiunge colore e vita a qualsiasi stanza. Con fiori vibranti che durano a lungo, è un'ottima scelta per decorare il tuo spazio.",
        "Perfetta per creare una siepe naturale nel tuo giardino, questa pianta arbustiva è facile da curare e cresce rapidamente.",
        "Le piante rampicanti come questa sono ideali per coprire muri o pergolati. Crescono velocemente e richiedono poche cure.",
        "Pianta ornamentale molto apprezzata per il suo aspetto elegante. Può essere coltivata in vaso o direttamente nel terreno.",
        "Questa pianta da frutto non solo è bella, ma produce anche frutti deliziosi. Perfetta per giardini di tutte le dimensioni.",
        "Le piante acquatiche come questa sono ideali per stagni o acquari. Richiedono acqua pulita e una buona esposizione alla luce.",
        "Una pianta erbacea che aggiunge un tocco naturale a qualsiasi giardino. Cresce rapidamente e richiede poca manutenzione.",
        "Le piante perenni come questa offrono fioriture anno dopo anno. Sono resistenti e facili da curare.",
        "Questa pianta annuale fiorisce splendidamente e aggiunge colore al tuo giardino ogni anno. Ideale per bordure e aiuole.",
        "Le piante biennali completano il loro ciclo vitale in due anni, offrendo fioriture spettacolari. Perfette per chi ama il cambiamento.",
        "Pianta medicinale conosciuta per le sue proprietà benefiche. Può essere utilizzata in vari modi per migliorare la salute.",
        "Le piante aromatiche come questa aggiungono profumo e sapore ai tuoi piatti. Facili da coltivare sia in giardino che in vaso.",
        "Pianta carnivora affascinante che cattura insetti. Una scelta interessante per chi ama le piante insolite.",
        "Le piante esotiche come questa portano un tocco di tropici nella tua casa. Richiedono un ambiente caldo e umido.",
        "Pianta succulenta ideale per terrari e giardini rocciosi. Richiede poca acqua e cresce lentamente.",
        "Le piante tropicali sono perfette per creare un'oasi verde in casa. Richiedono umidità e calore costanti.",
        "Pianta alpina resistente alle basse temperature. Perfetta per giardini rocciosi o come bordura.",
        "Questa pianta sempreverde mantiene il suo colore tutto l'anno. Ideale per giardini e paesaggi permanenti.",
        "Le piante decidue perdono le foglie in autunno e rinascono in primavera. Offrono un ciclo di vita affascinante.",
        "Pianta boschiva che aggiunge un tocco di foresta al tuo giardino. Cresce bene in ombra parziale.",
        "Le piante da siepe come questa sono ideali per creare barriere naturali. Crescono rapidamente e sono facili da potare.",
        "Pianta tappezzante perfetta per coprire il suolo in giardino. Cresce velocemente e richiede poca manutenzione.",
        "Pianta ombrosa che prospera in condizioni di scarsa luce. Ideale per angoli bui del giardino.",
        "Pianta da balcone che fiorisce magnificamente in spazi ristretti. Facile da coltivare in vasi o fioriere.",
        "Pianta palustre ideale per zone umide e bordi di stagni. Aggiunge un tocco naturale ai giardini acquatici.",
        "Le piante da appartamento come questa migliorano la qualità dell'aria e aggiungono bellezza agli spazi interni.",
        "Pianta bonsai che richiede cura e attenzione. Perfetta per chi ama il giardinaggio dettagliato.",
        "Le piante rampicanti da interno sono perfette per aggiungere verticalità agli spazi chiusi. Crescono bene con poca luce.",
        "Pianta rampicante da esterno ideale per coprire pergolati o muri. Cresce rapidamente e offre fioriture abbondanti.",
        "Pianta pendente che aggiunge un tocco decorativo a qualsiasi ambiente. Perfetta per vasi sospesi.",
        "Le piante a foglia larga offrono un aspetto tropicale e lussureggiante. Richiedono una buona esposizione alla luce.",
        "Pianta a foglia stretta elegante e raffinata. Ideale per giardini formali e spazi moderni.",
        "Le piante a foglia variegata aggiungono colore e interesse visivo con le loro foglie multicolori.",
        "Pianta a fiore unico che produce fiori grandi e spettacolari. Perfetta per diventare il punto focale del giardino.",
        "Le piante a fiori multipli offrono una fioritura continua e abbondante. Ideali per aiuole e bordure.",
        "Pianta profumata che riempie l'aria di fragranza. Ideale per giardini sensoriali e bordure.",
        "Pianta autoctona che cresce naturalmente nella tua regione. Richiede meno cura e si adatta meglio al clima locale.",
        "Pianta rara che aggiunge un tocco unico al tuo giardino. Perfetta per collezionisti e appassionati.",
        "Pianta commestibile che produce frutti o foglie da utilizzare in cucina. Aggiunge bellezza e funzionalità al giardino.",
        "Pianta da spezia che arricchisce i tuoi piatti con sapori unici. Facile da coltivare in vaso o giardino.",
        "Pianta decorativa che aggiunge un tocco di eleganza a qualsiasi ambiente. Perfetta per interni ed esterni."
    ]
    return random.choice(descriptionOptions)

def generateRandomDate() -> datetime.date:
    today = datetime.date.today()
    twoMonthsAgo = today - datetime.timedelta(days=60)
    randomDays = random.randint(0, 60)  # Numero casuale di giorni da 0 a 60
    randomDate = twoMonthsAgo + datetime.timedelta(days=randomDays)
    return randomDate

def generateRandomProvince() -> str:
    provinces = [
        "MI", "RM", "NA", "TO", "FI", "BO", "PA", "GE", "BA", "CA",
        "VE", "VR", "TS", "BG", "BS", "CO", "PG", "PE", "AQ", "TE",
        "SA", "LE", "CZ", "RC", "CS", "CT", "SR", "TA", "SS", "OR",
        "NU", "TR", "PC", "PR", "RE", "MO", "RN", "AN", "AP", "MC"
    ]
    return random.choice(provinces)

def generateRandomCity(provincia: str) -> str:
    if provincia == "MI":
        return random.choice(["Milano", "Monza", "Bergamo", "Como", "Brescia"])
    elif provincia == "RM":
        return random.choice(["Roma", "Latina", "Frosinone", "Rieti", "Viterbo"])
    elif provincia == "NA":
        return random.choice(["Napoli", "Salerno", "Avellino", "Benevento", "Caserta"])
    elif provincia == "TO":
        return random.choice(["Torino", "Novara", "Asti", "Alessandria", "Cuneo"])
    elif provincia == "FI":
        return random.choice(["Firenze", "Prato", "Pistoia", "Arezzo", "Livorno"])
    elif provincia == "BO":
        return random.choice(["Bologna", "Modena", "Ferrara", "Ravenna", "Forlì"])
    elif provincia == "PA":
        return random.choice(["Palermo", "Catania", "Messina", "Trapani", "Siracusa"])
    elif provincia == "GE":
        return random.choice(["Genova", "Savona", "La Spezia", "Imperia", "Chiavari"])
    elif provincia == "BA":
        return random.choice(["Bari", "Taranto", "Brindisi", "Lecce", "Foggia"])
    elif provincia == "CA":
        return random.choice(["Cagliari", "Sassari", "Nuoro", "Oristano", "Olbia"])
    else:
        return "Città casuale"
    
def generateRandomCAP() -> str:
    return ''.join(random.choices('0123456789', k=5))

def generateRandomStreet() -> str:
    streets = [
        "Via Roma", "Via Milano", "Via Verdi", "Corso Italia", "Piazza Duomo", "Largo Garibaldi", "Via Dante",
        "Via Manzoni", "Via Petrarca", "Via Leopardi", "Corso Vittorio Emanuele", "Piazza San Marco", "Via della Libertà",
        "Via Mazzini", "Via Fermi", "Via Marconi", "Via Galilei", "Via Colombo", "Via Giotto", "Via Raffaello",
        "Via Tiziano", "Via Bernini", "Via Botticelli", "Via Caravaggio", "Via Donatello", "Via Michelangelo",
        "Via Tintoretto", "Via Tiepolo", "Via Bellini", "Via Rossini", "Via Puccini", "Via Verga", "Via Pirandello",
        "Via Svevo", "Via Carducci", "Via Pascoli", "Via Ungaretti", "Via Montale"
    ]
    return random.choice(streets)

def generateRandomPhoneNumber() -> str:
    return "+39" + ''.join(random.choices('0123456789', k=10))

def generateRandomNotes() -> str:
    notes = [
        "Piano terra", "Accesso dal cortile", "Chiudere il cancello", "Consegna preferibile dopo le 15:00",
        "Lasciare il pacco in portineria", "Suonare il campanello", "Non lasciare incustodito", "Contattare prima della consegna",
        "Ingresso sul retro", "Attenzione al cane", "Consegna urgente", "Consegna al vicino se assente", "Pacco fragile",
        "Non piegare", "Consegna solo al destinatario", "Codice di accesso 1234", "Lasciare il pacco alla porta",
        "Consegna al garage", "Chiudere bene la porta", "Lasciare il pacco nella cassetta delle lettere",
        "Consegna a partire dalle 9:00", "Non suonare il campanello", "Consegna al primo piano", "Lasciare sul balcone",
        "Consegna solo in mano", "Evitare rumori forti", "Pacco pesante", "Utilizzare il montacarichi", "Parcheggiare nel cortile",
        "Attendere risposta al citofono", "Chiamare al cellulare all'arrivo", "Non lasciare pacco incustodito", 
        "Suonare campanello una volta", "Non disturbare la mattina", "Attendere conferma di ricezione"
    ]
    return random.choice(notes)

def generateRandomCardNumber() -> str:
    return ''.join(random.choices('0123456789', k=16))

def generateRandomCVV() -> str:
    return ''.join(random.choices('0123456789', k=3))

def getRandomAddressCode(cursor) -> int:
    cursor.execute("SELECT codice_indirizzo FROM indirizzi ORDER BY RAND() LIMIT 1")
    addressId = cursor.fetchone()[0]
    return addressId

def getRandomUserEmail(cursor) -> str:
    cursor.execute("SELECT email FROM utenti WHERE ruolo != 'AMMINISTRATORE' ORDER BY RAND() LIMIT 1")
    email = cursor.fetchone()[0]
    return email

def getRandomCreditCard(cursor, email: str) -> tuple:
    cursor.execute("SELECT numero_carta, cvv, scadenza, nome, cognome FROM memorizzazioni WHERE email = %s ORDER BY RAND() LIMIT 1", (email,))
    creditCard = cursor.fetchone()
    return creditCard

def generateRandomOrderDate() -> datetime.date:
    startDate = datetime.date(2023, 1, 1)
    endDate = datetime.date.today()
    randomDate = startDate + datetime.timedelta(days=random.randint(0, (endDate - startDate).days))
    return randomDate

def generateRandomDeliveryDate(order_date: datetime.date) -> datetime.date:
    deliveryDate = order_date + datetime.timedelta(days=random.randint(3, 14))
    return deliveryDate

def calculateTotalPrice(cursor) -> float:
    cursor.execute("SELECT SUM(prezzo) FROM prodotti ORDER BY RAND() LIMIT 5")
    totalPrice = cursor.fetchone()[0]
    return round(totalPrice, 2)

def getRandomProducts(cursor) -> list:
    cursor.execute("SELECT codice_prodotto FROM prodotti WHERE codice_prodotto >= (SELECT FLOOR(MAX(codice_prodotto) * RAND()) FROM prodotti) LIMIT 5")
    products = cursor.fetchall()
    random_products = []
    for product_id in products:
        random_products.append((product_id[0], random.randint(1, 5)))
    return random_products

def generateRandomExpiryDate() -> str:
    today = datetime.date.today()
    year = today.year + random.randint(1, 10)
    month = random.randint(1, 12)
    day = random.randint(1, 28)
    expiryDate = datetime.date(year, month, day)
    return expiryDate

# ------------------- DATABASE FUNCTIONS -------------------
def createDatabase(sqlScriptPath: str) -> None:
    connection = mysql.connector.connect(**dbConfig)
    cursor = connection.cursor()
    cursor.execute(f"DROP DATABASE {DATABASE_NAME}; CREATE DATABASE IF NOT EXISTS {DATABASE_NAME};", multi=True)
    connection.commit()
    dbConfig["database"] = DATABASE_NAME
    connection.database = DATABASE_NAME

    try:
        cursor = connection.cursor()

        with open(sqlScriptPath, 'r') as file:
            sqlScriptPath = file.read()

        for statement in sqlScriptPath.split(';'):
            if statement.strip():
                cursor.execute(statement)

        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()


def addAdmin(path: str, email: str, password: str, nome: str, cognome: str) -> None:
    connection = mysql.connector.connect(**dbConfig)

    try:
        cursor = connection.cursor()
        sha256 = hashlib.sha256(password.encode()).hexdigest()

        with open(path, 'rb') as file:
            binaryImage = file.read()

        insert_query = """
        INSERT INTO utenti (email, password, foto_profilo, nome, cognome, ruolo)
        VALUES (%s, %s, %s, %s, %s, 'AMMINISTRATORE')
        """
        cursor.execute(insert_query, (email, sha256, binaryImage, nome, cognome))
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomUsers(numEntries: int) -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        userTypeOptions = ["CLIENTE", "DIPENDENTE"]
        for i in range(numEntries):
            name = generateRandomFirstName()
            surname = generateRandomLastName()
            email = f"utente{i}@example.com"
            password = f"{name.capitalize()}.{i}"
            sha256 = hashlib.sha256(password.encode()).hexdigest()

            imagePath = f"./images/profile/users/{random.randint(1, 10)}.png"
            with open(imagePath, 'rb') as file:
                binaryImage = file.read()

            insert_query = """
            INSERT INTO utenti (email, password, foto_profilo, nome, cognome, ruolo)
            VALUES (%s, %s, %s, %s, %s, %s)
            """
            cursor.execute(insert_query, (email, sha256, binaryImage, name, surname, random.choice(userTypeOptions)))
        
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomProducts(numEntries: int) -> None:
    seasonalityOptions = ["INVERNO", "PRIMAVERA", "ESTATE", "AUTUNNO"]
    waterQuantityOptions = ["POCA", "NORMALE", "MOLTA"]
    
    connection = mysql.connector.connect(**dbConfig)
    try:
        cursor = connection.cursor()

        for _ in range(numEntries):
            name = generateRandomProductName()
            price = round(random.uniform(5, 100), 2)
            quantity = random.randint(1, 50)
            seasonality = random.choice(seasonalityOptions)
            waterAmount = random.choice(waterQuantityOptions)
            plantType = generateRandomPlantType()
            description = generateRandomDescription()
            dateAdded = generateRandomDate()

            imagePath = f"./images/products/{random.randint(1, 60)}.jpeg"
            with open(imagePath, 'rb') as file:
                immage = file.read()

            insertQuery = """
            INSERT INTO prodotti (nome, immagine, prezzo, quantita, stagionalita, quantita_acqua, tipologia_pianta, descrizione, data_aggiunta)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(insertQuery, (name, immage, price, quantity, seasonality, waterAmount, plantType, description, dateAdded))
            connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomAddresses(amount: int) -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        for _ in range(amount):
            provincia = generateRandomProvince()
            citta = generateRandomCity(provincia)
            cap = generateRandomCAP()
            via = generateRandomStreet()
            nome = generateRandomFirstName()
            cognome = generateRandomLastName()
            numero_telefono = generateRandomPhoneNumber()
            note = generateRandomNotes()

            insert_query = """
            INSERT INTO indirizzi (provincia, citta, cap, via, nome, cognome, numero_di_telefono, note)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(insert_query, (provincia, citta, cap, via, nome, cognome, numero_telefono, note))
        
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomAddressAssociations() -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        cursor.execute("SELECT email FROM utenti WHERE ruolo != 'AMMINISTRATORE'")
        users = cursor.fetchall()

        cursor.execute("SELECT codice_indirizzo FROM indirizzi")
        addresses = cursor.fetchall()

        for email in users:
            numAssociations = random.randint(1, 3)

            selectedAddresses = random.sample(addresses, numAssociations)
            for address_id in selectedAddresses:
                addressId = address_id[0]

                insertQuery = """
                INSERT INTO locazione (email, codice_indirizzo)
                VALUES (%s, %s)
                """
                cursor.execute(insertQuery, (email[0], addressId))
        
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomCreditCards(numEntries: int) -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        for _ in range(numEntries):
            cardNumber = encrypt(generateRandomCardNumber(), ENCRYPTION_KEY)
            cvv = encrypt(generateRandomCVV(), ENCRYPTION_KEY)
            expiryDate = generateRandomExpiryDate()
            name = generateRandomFirstName()
            surname = generateRandomLastName()

            insert_query = """
            INSERT INTO carte_di_credito (numero_carta, cvv, scadenza, nome, cognome)
            VALUES (%s, %s, %s, %s, %s)
            """
            cursor.execute(insert_query, (cardNumber, cvv, expiryDate, name, surname))
        
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomCreditCardsAssociations() -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        cursor.execute("SELECT email FROM utenti WHERE ruolo != 'AMMINISTRATORE'")
        users = cursor.fetchall()

        cursor.execute("SELECT numero_carta, cvv, scadenza, nome, cognome FROM carte_di_credito")
        creditCards = cursor.fetchall()

        for userEmail in users:
            numAssociations = random.randint(1, 3)

            selected_cards = random.sample(creditCards, numAssociations)
            for card in selected_cards:
                cardNumber, cvv, expirationDate, name, surname = card

                insert_query = """
                INSERT INTO memorizzazioni (email, numero_carta, cvv, scadenza, nome, cognome)
                VALUES (%s, %s, %s, %s, %s, %s)
                """
                cursor.execute(insert_query, (userEmail[0], cardNumber, cvv, expirationDate, name, surname))
        
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def createRandomOrders(amount: int) -> None:
    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        for _ in range(amount):
            addressId = getRandomAddressCode(cursor)
            email = getRandomUserEmail(cursor)
            creditCardNumber, cvv, expiryDate, name, surname = getRandomCreditCard(cursor, email)
            orderDate = generateRandomOrderDate()
            deliveryDate = generateRandomDeliveryDate(orderDate)
            totalPrice = calculateTotalPrice(cursor)

            insertQuery = """
            INSERT INTO ordini (codice_indirizzo, email, numero_carta, cvv, scadenza, nome, cognome, data_ordine, data_consegna, prezzo_totale)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(insertQuery, (addressId, email, creditCardNumber, cvv, expiryDate, name, surname, orderDate, deliveryDate, totalPrice))
            orderId = cursor.lastrowid

            products = getRandomProducts(cursor)
            goodsData = []
            for productId, quantity in products:
                goodsData.append((orderId, productId, quantity))

            insert_merchandise_query = """
            INSERT INTO merce (codice_ordine, codice_prodotto, quantita)
            VALUES (%s, %s, %s)
            """
            cursor.executemany(insert_merchandise_query, goodsData)

        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def main():
    print("> Creating database...")
    createDatabase("../schema.sql")

    print("> Adding admins...")
    addAdmin("./images/profile/admin/admin_1.png", "v.ferrentino10@studenti.unisa.it", "Valentina.22", "Valentina", "Ferrentino")
    addAdmin("./images/profile/admin/admin_2.png", "s.albino2@studenti.unisa.it", "Simone.06", "Simone Francesco", "Albino")

    print("> Adding random users...")
    createRandomUsers(USERS_AMOUNT)

    print("> Adding random addresses...")
    createRandomAddresses(ADDRESSES_AMOUNT)

    print("> Creating random associations between users and addresses...")
    createRandomAddressAssociations()

    print("> Adding random products...")
    createRandomProducts(PRODUCTS_AMOUNT)

    print("> Adding random credit cards...")
    createRandomCreditCards(CREDIT_CARDS_AMOUNT)

    print("> Creating random associations between users and credit cards...")
    createRandomCreditCardsAssociations()

    print("> Creating random orders...")
    createRandomOrders(100)

if __name__ == "__main__":
    main()
