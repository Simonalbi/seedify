package com.unisa.seedify.model;

import java.sql.*;

public class CreditCardDao extends BaseDao implements GenericDao<CreditCardBean> {
    public static final String TABLE_NAME = "carte_di_credito";

    private static CreditCardDao instance = null;

    private CreditCardDao() {
    }

    public static CreditCardDao getInstance() {
        if (instance == null) {
            instance = new CreditCardDao();
        }
        return instance;
    }

    @Override
    public void doSave(CreditCardBean creditCardBean) throws SQLException {
        String query = "INSERT INTO " + CreditCardDao.TABLE_NAME +
                       "(numero_carta, cvv, scadenza, nome, cognome) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, encrypt(creditCardBean.getCardNumber()));
            preparedStatement.setString(2, encrypt(creditCardBean.getCvv()));
            preparedStatement.setDate(3, creditCardBean.getExpirationDate());
            preparedStatement.setString(4, creditCardBean.getName());
            preparedStatement.setString(5, creditCardBean.getSurname());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    creditCardBean.setCardId(generatedId);
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void doDelete(CreditCardBean creditCardBean) throws SQLException {
        String query = "DELETE FROM " + CreditCardDao.TABLE_NAME +
                       " WHERE codice_carta = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, creditCardBean.getCardId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(CreditCardBean creditCardBean) throws SQLException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public CreditCardBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int cardCodce = (Integer) primaryKey.getKey("codice_carta");

        String query = "SELECT * FROM " + CreditCardDao.TABLE_NAME +
                       " WHERE codice_carta = ?";

        CreditCardBean creditCardBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cardCodce);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditCardBean = new CreditCardBean();
                    creditCardBean.setCardId(resultSet.getInt("codice_carta"));
                    creditCardBean.setCardNumber("••••••••••••" + decrypt(resultSet.getString("numero_carta")).substring(12));
                    creditCardBean.setCvv("•••");
                    creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                    creditCardBean.setName(resultSet.getString("nome"));
                    creditCardBean.setSurname(resultSet.getString("cognome"));
                }
            }
        }

        return creditCardBean;
    }
}
