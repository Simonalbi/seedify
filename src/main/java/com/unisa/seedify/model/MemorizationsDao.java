package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemorizationsDao extends BaseDao implements GenericDao<MemorizationsBean> {
    private static final String TABLE_NAME = "memorizzazioni";

    @Override
    public void doSave(MemorizationsBean memorizationsBeans) throws SQLException {
        String query =  "INSERT INTO " + MemorizationsDao.TABLE_NAME +
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
        String query =  "DELETE FROM " + MemorizationsDao.TABLE_NAME +
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
}
