package com.unisa.seedify.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationsDao extends BaseDao implements GenericDao<LocationsBean>, DetailedDao<LocationsBean, AddressBean> {
    private static final String TABLE_NAME = "locazione";

    @Override
    public void doSave(LocationsBean locationsBean) throws SQLException {
        String query = "INSERT INTO " + LocationsDao.TABLE_NAME +
                       " (email, codice_indirizzo) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(1, locationsBean.getUser().getEmail());

                for (AddressBean addressBean : locationsBean.getAddress()) {
                    preparedStatement.setInt(2, addressBean.getAddressId());

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
    public void doDelete(LocationsBean locationsBean) throws SQLException {
        String query = "DELETE FROM " + LocationsDao.TABLE_NAME +
                       " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, locationsBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(LocationsBean locationsBean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void doSaveOne(LocationsBean locationsBean, AddressBean addressBean) throws SQLException {
        locationsBean.getAddress().add(addressBean);

        String query = "INSERT INTO " + LocationsDao.TABLE_NAME +
                       " (email, codice_indirizzo) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, locationsBean.getUser().getEmail());
            preparedStatement.setInt(2, addressBean.getAddressId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDeleteOne(LocationsBean locationsBean, AddressBean addressBean) throws SQLException {
        locationsBean.getAddress().remove(addressBean);

        String query = "DELETE FROM " + LocationsDao.TABLE_NAME +
                       " WHERE email = ? AND codice_indirizzo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, locationsBean.getUser().getEmail());
            preparedStatement.setInt(2, addressBean.getAddressId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdateOne(LocationsBean locationsBean, AddressBean addressBean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }
}
