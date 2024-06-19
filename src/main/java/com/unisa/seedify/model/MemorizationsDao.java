package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

import java.sql.*;
import java.util.List;

public class MemorizationsDao extends BaseDao implements GenericDao<MemorizationsBean>, DetailedDao<MemorizationsBean, CreditCardBean> {
    private static final String TABLE_NAME = "memorizzazioni";

    private static MemorizationsDao instance = null;

    private static final UserDao userDao = UserDao.getInstance();

    private MemorizationsDao() {
    }

    public static MemorizationsDao getInstance() {
        if (instance == null) {
            instance = new MemorizationsDao();
        }
        return instance;
    }

    @Override
    public void doSave(MemorizationsBean memorizationsBeans) throws SQLException {
        String query = "INSERT INTO " + MemorizationsDao.TABLE_NAME +
                       " (email, numero_carta, cvv, scadenza, nome, cognome) " +
                       " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(1, memorizationsBeans.getUser().getEmail());

                for (CreditCardBean creditCardBean : memorizationsBeans.getCreditCards()) {
                    preparedStatement.setString(2, creditCardBean.getCardNumber());
                    preparedStatement.setString(3, creditCardBean.getCvv());
                    preparedStatement.setDate(4, creditCardBean.getExpirationDate());
                    preparedStatement.setString(5, creditCardBean.getName());
                    preparedStatement.setString(6, creditCardBean.getSurname());

                    preparedStatement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public void doDelete(MemorizationsBean memorizationsBeans) throws SQLException {
        String query = "DELETE FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ? AND numero_carta = ? AND cvv = ? AND scadenza = ? AND nome = ? AND cognome = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try {
                preparedStatement.setString(1, memorizationsBeans.getUser().getEmail());

                for (CreditCardBean creditCardBean : memorizationsBeans.getCreditCards()) {
                    preparedStatement.setString(2, creditCardBean.getCardNumber());
                    preparedStatement.setString(3, creditCardBean.getCvv());
                    preparedStatement.setDate(4, creditCardBean.getExpirationDate());
                    preparedStatement.setString(5, creditCardBean.getName());
                    preparedStatement.setString(6, creditCardBean.getSurname());

                    preparedStatement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public void doUpdate(MemorizationsBean memorizationsBeans) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void doSaveOne(MemorizationsBean memorizationsBean, CreditCardBean creditCardBean) throws SQLException {
        memorizationsBean.getCreditCards().add(creditCardBean);

        String query = "INSERT INTO " + MemorizationsDao.TABLE_NAME +
                       " (email, numero_carta, cvv, scadenza, nome, cognome) " +
                       " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, memorizationsBean.getUser().getEmail());
            preparedStatement.setString(2, creditCardBean.getCardNumber());
            preparedStatement.setString(3, creditCardBean.getCvv());
            preparedStatement.setDate(4, creditCardBean.getExpirationDate());
            preparedStatement.setString(5, creditCardBean.getName());
            preparedStatement.setString(6, creditCardBean.getSurname());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDeleteOne(MemorizationsBean collection, CreditCardBean bean) throws SQLException {
        collection.getCreditCards().remove(bean);

        String query = "DELETE FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ? AND numero_carta = ? AND cvv = ? AND scadenza = ? AND nome = ? AND cognome = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, collection.getUser().getEmail());
            preparedStatement.setString(2, bean.getCardNumber());
            preparedStatement.setString(3, bean.getCvv());
            preparedStatement.setDate(4, bean.getExpirationDate());
            preparedStatement.setString(5, bean.getName());
            preparedStatement.setString(6, bean.getSurname());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdateOne(MemorizationsBean collection, CreditCardBean bean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public MemorizationsBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            MemorizationsBean memorizationsBean = null;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                memorizationsBean = new MemorizationsBean();

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", email);
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                memorizationsBean.setUser(userBean);

                List<CreditCardBean> creditCards = memorizationsBean.getCreditCards();
                while (resultSet.next()) {
                    CreditCardBean creditCardBean = new CreditCardBean();
                    creditCardBean.setCardNumber(resultSet.getString("numero_carta"));
                    creditCardBean.setCvv(resultSet.getString("cvv"));
                    creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                    creditCardBean.setName(resultSet.getString("nome"));
                    creditCardBean.setSurname(resultSet.getString("cognome"));

                    memorizationsBean.getCreditCards().add(creditCardBean);
                }

                return memorizationsBean;
            }
        }
    }
}
