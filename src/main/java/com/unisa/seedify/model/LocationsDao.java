package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Metodo per eliminare un solo indirizzo
public class LocationsDao extends BaseDao implements GenericDao<LocationsBean> {
    private static final String TABLE_NAME = "locazione";

    @Override
    public void doSave(LocationsBean locationsBean) throws SQLException {
        String query =  "INSERT INTO " + LocationsDao.TABLE_NAME +
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
        String query =  "DELETE FROM " + LocationsDao.TABLE_NAME +
                        " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, locationsBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }
}
