package com.unisa.seedify.model;

import java.sql.*;

public class AddressDao extends BaseDao implements GenericDao<AddressBean> {
    public static final String TABLE_NAME = "indirizzi";

    private static AddressDao instance = null;

    private AddressDao() {
    }

    public static AddressDao getInstance() {
        if (instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }

    @Override
    public void doSave(AddressBean addressBean) throws SQLException {
        String query = "INSERT INTO " + AddressDao.TABLE_NAME +
                       " (provincia, citta, cap, via, nome, cognome, numero_di_telefono, note)" +
                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, addressBean.getProvince());
            preparedStatement.setString(2, addressBean.getCity());
            preparedStatement.setString(3, addressBean.getCap());
            preparedStatement.setString(4, addressBean.getStreet());
            preparedStatement.setString(5, addressBean.getName());
            preparedStatement.setString(6, addressBean.getSurname());
            preparedStatement.setString(7, addressBean.getPhone());
            preparedStatement.setString(8, addressBean.getNote());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    addressBean.setAddressId(generatedId);
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void doDelete(AddressBean addressBean) throws SQLException {
        String query = "DELETE FROM " + AddressDao.TABLE_NAME +
                       " WHERE codice_indirizzo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, addressBean.getAddressId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(AddressBean addressBean) throws SQLException {
        String query = "UPDATE " + AddressDao.TABLE_NAME +
                       " SET provincia = ?, citta = ?, cap = ?, via = ?, nome = ?, cognome = ?, numero_di_telefono = ?, note = ? " +
                       " WHERE codice_indirizzo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, addressBean.getProvince());
            preparedStatement.setString(2, addressBean.getCity());
            preparedStatement.setString(3, addressBean.getCap());
            preparedStatement.setString(4, addressBean.getStreet());
            preparedStatement.setString(5, addressBean.getName());
            preparedStatement.setString(6, addressBean.getSurname());
            preparedStatement.setString(7, addressBean.getPhone());
            preparedStatement.setString(8, addressBean.getNote());
            preparedStatement.setInt(9, addressBean.getAddressId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public AddressBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int addressId = (int) primaryKey.getKey("codice_indirizzo");

        String query = "SELECT * FROM " + AddressDao.TABLE_NAME +
                       " WHERE codice_indirizzo = ?";

        AddressBean addressBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, addressId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    addressBean = new AddressBean();
                    addressBean.setAddressId(resultSet.getInt("codice_indirizzo"));
                    addressBean.setProvince(resultSet.getString("provincia"));
                    addressBean.setCity(resultSet.getString("citta"));
                    addressBean.setCap(resultSet.getString("cap"));
                    addressBean.setStreet(resultSet.getString("via"));
                    addressBean.setName(resultSet.getString("nome"));
                    addressBean.setSurname(resultSet.getString("cognome"));
                    addressBean.setPhone(resultSet.getString("numero_di_telefono"));
                    addressBean.setNote(resultSet.getString("note"));
                }
            }
        }

        return addressBean;
    }
}
