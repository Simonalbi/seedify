/*
private void test() {
        DataSource dataSource;
        try {
            Context initialContext = new InitialContext();
            Context enviromentContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) enviromentContext.lookup("jdbc/seedify");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        String path = "C:\\Users\\ferre\\IdeaProjects\\seedify\\src\\main\\webapp\\common\\assets\\img\\profile\\employee\\11.png";
        String query = "UPDATE utenti SET foto_profilo = ? WHERE email = 'v.ferrentino10@studenti.unisa.it';";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             FileInputStream fis = new FileInputStream(path)) {

            preparedStatement.setBinaryStream(1, fis, (int) new File(path).length());
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        EntityPrimaryKey entityPrimaryKey = new EntityPrimaryKey();
        entityPrimaryKey.addKey("email", "s.albino2@studenti.unisa.it");

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\ferre\\Desktop\\file_del_cazzo.png")) {
            UserBean userBean = UserDao.getInstance().doRetrive(entityPrimaryKey);
            fos.write(userBean.getProfilePicture());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
*/

def main():
    db_config = {
        'user': 'your_username',
        'password': 'your_password',
        'host': 'your_host',
        'database': 'your_database'
    }

    path = r"C:\Users\ferre\IdeaProjects\seedify\src\main\webapp\common\assets\img\profile\employee\11.png"
    query = "UPDATE utenti SET foto_profilo = %s WHERE email = 'v.ferrentino10@studenti.unisa.it'"

    try:
        connection = mysql.connector.connect(**db_config)

        if connection.is_connected():
            cursor = connection.cursor()

            with open(path, 'rb') as file:
                binary_data = file.read()

            cursor.execute(query, (binary_data,))
            connection.commit()

            select_query = "SELECT foto_profilo FROM utenti WHERE email = 's.albino2@studenti.unisa.it'"
            cursor.execute(select_query)
            user_profile_picture = cursor.fetchone()[0]

            output_path = r"C:\Users\ferre\Desktop\file_del_cazzo.png"
            with open(output_path, 'wb') as output_file:
                output_file.write(user_profile_picture)

    except Error as e:
        print(f"Error: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

if __name__ == "__main__":
    main()