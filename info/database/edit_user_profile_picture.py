import mysql.connector

USERNAME = "root"
PASSWORD = "06102003"
HOST = "localhost"
DATABASE = "seedify"

def updateUserProfilePicture(path: str, email: str) -> None:
    dbConfig = {
        "user": USERNAME,
        "password": PASSWORD,
        "host": HOST,
        "database": DATABASE
    }

    try:
        connection = mysql.connector.connect(**dbConfig)
        cursor = connection.cursor()

        with open(path, 'rb') as file:
            binaryImage = file.read()

        cursor.execute("UPDATE utenti SET foto_profilo = %s WHERE email = %s", (binaryImage, email))
        connection.commit()
    except mysql.connector.Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

def main():
    while True:
        path = input("Inserisci il path ASSOLUTO dell'immagine > ")
        email = input("Inserisci l'email dell'utente > ")
        updateUserProfilePicture(path, email)

        if input("Vuoi modificare un'altra immagine? (s/n) > ").lower() == "n":
            break

if __name__ == "__main__":
    main()
