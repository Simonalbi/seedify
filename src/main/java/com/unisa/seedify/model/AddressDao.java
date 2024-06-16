package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDao extends BaseDao implements GenericDao<AddressBean> {
    private static final String TABLE_NAME = "indirizzi";

    @Override
    public void doSave(AddressBean addressBean) throws SQLException {
        String query = "INSERT INTO " + AddressDao.TABLE_NAME +
                       " (provincia, citta, cap, via, nome, cognome, numero_di_telefono, note)" +
                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

            preparedStatement.executeUpdate();
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
}
