package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao extends BaseDao implements GenericDao<UserBean> {
    private static final String TABLE_NAME = "utenti";

    @Override
    public synchronized void doSave(UserBean user) throws SQLException {
        String query = "INSERT INTO " + UserDao.TABLE_NAME + " (email, password, foto_profilo, nome, cognome, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBytes(3, user.getProfilePicture());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getRole().toString());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public synchronized void doDelete(UserBean user) throws SQLException {
        String query = "DELETE FROM " + UserDao.TABLE_NAME + " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getEmail());

            preparedStatement.executeUpdate();
        }
    }
}
