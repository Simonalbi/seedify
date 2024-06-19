package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

import java.sql.*;

public class CreditCardDao extends BaseDao implements GenericDao<CreditCardBean> {
    private static final String TABLE_NAME = "carte_di_credito";

    @Override
    public void doSave(CreditCardBean creditCardBean) throws SQLException {
        String query = "INSERT INTO " + CreditCardDao.TABLE_NAME +
                       "(numero_carta, cvv, scadenza, nome, cognome) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, creditCardBean.getCardNumber());
            preparedStatement.setString(2, creditCardBean.getCvv());
            preparedStatement.setDate(3, creditCardBean.getExpirationDate());
            preparedStatement.setString(4, creditCardBean.getName());
            preparedStatement.setString(5, creditCardBean.getSurname());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDelete(CreditCardBean creditCardBean) throws SQLException {
        String query = "DELETE FROM " + CreditCardDao.TABLE_NAME +
                       " WHERE numero_carta = ? AND cvv = ? AND scadenza = ? AND nome = ? AND cognome = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, creditCardBean.getCardNumber());
            preparedStatement.setString(2, creditCardBean.getCvv());
            preparedStatement.setDate(3, creditCardBean.getExpirationDate());
            preparedStatement.setString(4, creditCardBean.getName());
            preparedStatement.setString(5, creditCardBean.getSurname());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(CreditCardBean creditCardBean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public CreditCardBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String cardNumber = (String) primaryKey.getKey("numero_carta");
        String cvv = (String) primaryKey.getKey("cvv");
        Date expirationDate = (Date) primaryKey.getKey("scadenza");
        String name = (String) primaryKey.getKey("nome");
        String surname = (String) primaryKey.getKey("cognome");

        String query = "SELECT * FROM " + CreditCardDao.TABLE_NAME +
                       " WHERE numero_carta = ? AND cvv = ? AND scadenza = ? AND nome = ? AND cognome = ?";

        CreditCardBean creditCardBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, cvv);
            preparedStatement.setDate(3, expirationDate);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, surname);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditCardBean = new CreditCardBean();
                    creditCardBean.setCardNumber(resultSet.getString("numero_carta"));
                    creditCardBean.setCvv(resultSet.getString("cvv"));
                    creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                    creditCardBean.setName(resultSet.getString("nome"));
                    creditCardBean.setSurname(resultSet.getString("cognome"));
                }
            }
        }

        return creditCardBean;
    }
}
