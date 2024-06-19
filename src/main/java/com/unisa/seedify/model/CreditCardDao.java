package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
