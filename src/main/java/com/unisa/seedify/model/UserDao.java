package com.unisa.seedify.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao implements GenericDao<UserBean> {
    public static final String TABLE_NAME = "utenti";

    private static UserDao instance = null;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public synchronized void doSave(UserBean userBean) throws SQLException {
        String query = "INSERT INTO " + UserDao.TABLE_NAME +
                       " (email, password, foto_profilo, nome, cognome, ruolo)" +
                       " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userBean.getEmail());
            preparedStatement.setString(2, userBean.getPassword());
            preparedStatement.setBytes(3, userBean.getProfilePicture());
            preparedStatement.setString(4, userBean.getName());
            preparedStatement.setString(5, userBean.getSurname());
            preparedStatement.setString(6, userBean.getRole().toString());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public synchronized void doDelete(UserBean userBean) throws SQLException {
        String query = "DELETE FROM " + UserDao.TABLE_NAME +
                " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userBean.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(UserBean userBean) throws SQLException {
        String query = "UPDATE " + UserDao.TABLE_NAME +
                " set password = ?, foto_profilo = ?, nome = ?, cognome = ?, ruolo = ?" +
                " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userBean.getPassword());
            preparedStatement.setBytes(2, userBean.getProfilePicture());
            preparedStatement.setString(3, userBean.getName());
            preparedStatement.setString(4, userBean.getSurname());
            preparedStatement.setString(5, userBean.getRole().toString());
            preparedStatement.setString(6, userBean.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public UserBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + UserDao.TABLE_NAME +
                " WHERE email = ?";

        UserBean userBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userBean = new UserBean();
                    userBean.setEmail(resultSet.getString("email"));
                    userBean.setPassword(resultSet.getString("password"));
                    userBean.setProfilePicture(resultSet.getBytes("foto_profilo"));
                    userBean.setName(resultSet.getString("nome"));
                    userBean.setSurname(resultSet.getString("cognome"));
                    userBean.setRole(UserBean.Roles.fromString(resultSet.getString("ruolo")));
                }
            }
        }

        return userBean;
    }

    private int getEntityAmount(UserBean.Roles role) {
        String query = "SELECT COUNT(*) AS entity_count FROM " + UserDao.TABLE_NAME +
                       " WHERE ruolo = ?";

        int entityCount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, role.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entityCount = resultSet.getInt("entity_count");
                }
            }
        } catch (SQLException ignored) {}

        return entityCount;
    }

    private List<UserBean> getAllEntity(UserBean.Roles role) {
        String query = "SELECT * FROM " + UserDao.TABLE_NAME +
                       " WHERE ruolo = ?";

        List<UserBean> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, role.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UserBean userBean = new UserBean();
                    userBean.setEmail(resultSet.getString("email"));
                    userBean.setPassword(resultSet.getString("password"));
                    userBean.setProfilePicture(resultSet.getBytes("foto_profilo"));
                    userBean.setName(resultSet.getString("nome"));
                    userBean.setSurname(resultSet.getString("cognome"));
                    userBean.setRole(UserBean.Roles.fromString(resultSet.getString("ruolo")));
                    customers.add(userBean);
                }
            }
        } catch (SQLException ignored) {}

        return customers;
    }

    public int getTotalEmployees() {
        return this.getEntityAmount(UserBean.Roles.EMPLOYEE);
    }

    public int getTotalCustomers() {
        return this.getEntityAmount(UserBean.Roles.CUSTOMER);
    }

    public List<UserBean> getAllEmployee() {
        return this.getAllEntity(UserBean.Roles.EMPLOYEE);
    }

    public List<UserBean> getAllCustomers() {
        return this.getAllEntity(UserBean.Roles.CUSTOMER);
    }
}