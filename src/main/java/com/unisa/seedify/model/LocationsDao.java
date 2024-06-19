package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationsDao extends BaseDao implements GenericDao<LocationsBean>, DetailedDao<LocationsBean, AddressBean> {
    private static final String TABLE_NAME = "locazione";

    private static LocationsDao instance = null;

    private static final AddressDao addressDao = AddressDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private LocationsDao() {
    }

    public static LocationsDao getInstance() {
        if (instance == null) {
            instance = new LocationsDao();
        }
        return instance;
    }

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

                for (AddressBean addressBean : locationsBean.getAddresses()) {
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
        locationsBean.getAddresses().add(addressBean);

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
        locationsBean.getAddresses().remove(addressBean);

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

    @Override
    public LocationsBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + LocationsDao.TABLE_NAME +
                       " WHERE email = ?";

        LocationsBean locationsBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                locationsBean = new LocationsBean();

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", email);
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                locationsBean.setUser(userBean);

                List<AddressBean> addresses = locationsBean.getAddresses();
                while (resultSet.next()) {
                    int addressId = resultSet.getInt("codice_indirizzo");

                    EntityPrimaryKey addressPrimaryKey = new EntityPrimaryKey();
                    addressPrimaryKey.addKey("codice_indirizzo", addressId);
                    AddressBean addressBean = addressDao.doRetrive(addressPrimaryKey);

                    addresses.add(addressBean);
                }

                locationsBean.setAddresses(addresses);
            }
        }

        return locationsBean;
    }
}
