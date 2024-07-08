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
PRODUCTS_AMOUNT = 50
CREDIT_CARDS_AMOUNT = 70

# ------------------- RANDOM FUNCTIONS -------------------
def generateRandomFirstName() -> str:
    firstNames = ["John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Isabella", "Alexander", "Ava"]
    return random.choice(firstNames)

def generateRandomLastName() -> str:
    lastNames = ["Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"]
    return random.choice(lastNames)

def generateRandomProvince() -> str:
    provinces = ["MI", "RM", "NA", "TO", "FI", "BO", "PA", "GE", "BA", "CA"]
    return random.choice(provinces)

def generateRandomCity(provincia: str) -> str:
    if provincia == "MI":
        return random.choice(["Milano", "Monza", "Bergamo", "Como", "Brescia"])
    elif provincia == "RM":
        return random.choice(["Roma", "Latina", "Frosinone", "Rieti", "Viterbo"])
    elif provincia == "NA":
        return random.choice(["Napoli", "Salerno", "Avellino", "Benevento", "Caserta"])
    else:
        return "Città casuale"
    
def generateRandomCAP() -> str:
    return ''.join(random.choices('0123456789', k=5))

def generateRandomStreet() -> str:
    streets = ["Via Roma", "Via Milano", "Via Verdi", "Corso Italia", "Piazza Duomo", "Largo Garibaldi", "Via Dante"]
    return random.choice(streets)

def generateRandomPhoneNumber() -> str:
    return "+39" + ''.join(random.choices('0123456789', k=10))

def generateRandomNotes() -> str:
    notes = ["Nessuna nota", "Piano terra", "Accesso dal cortile", "Chiudere il cancello", "Consegna preferibile dopo le 15:00"]
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
    productNamesOptions = ["Prodotto A", "Prodotto B", "Prodotto C", "Prodotto D", "Prodotto E"]
    seasonalityOptions = ["INVERNO", "PRIMAVERA", "ESTATE", "AUTUNNO"]
    waterQuantityOptions = ["POCA", "NORMALE", "MOLTA"]
    plantTypeOptions = ["Pianta da interno", "Pianta da esterno", "Pianta grassa", "Pianta fiorita"]
    descriptionOptions = ["Descrizione A", "Descrizione B", "Descrizione C", "Descrizione D", "Descrizione E"]
    
    connection = mysql.connector.connect(**dbConfig)
    try:
        cursor = connection.cursor()

        for _ in range(numEntries):
            name = random.choice(productNamesOptions)
            price = round(random.uniform(5, 100), 2)
            quantity = random.randint(1, 50)
            seasonality = random.choice(seasonalityOptions)
            waterAmount = random.choice(waterQuantityOptions)
            plantType = random.choice(plantTypeOptions)
            description = random.choice(descriptionOptions)
            dateAdded = datetime.date.today()

            imagePath = f"./images/products/{random.randint(1, 15)}.jpeg"
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
            cardNumber = generateRandomCardNumber()
            cvv = generateRandomCVV()
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
