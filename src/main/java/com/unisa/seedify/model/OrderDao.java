package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao extends BaseDao implements GenericDao<OrderBean> {
    private static final String TABLE_NAME = "ordini";

    @Override
    public void doSave(OrderBean orderBean) throws SQLException {
        String query = "INSERT INTO " + OrderDao.TABLE_NAME +
                       " (codice_indirizzo, email, numero_carta, cvv, scadenza, nome, cognome, data_ordine, data_consegna, prezzo_totale) " +
                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderBean.getAddress().getAddressId());
            preparedStatement.setString(2, orderBean.getUser().getEmail());
            preparedStatement.setString(3, orderBean.getCreditCard().getCardNumber());
            preparedStatement.setString(4, orderBean.getCreditCard().getCvv());
            preparedStatement.setDate(5, orderBean.getCreditCard().getExpirationDate());
            preparedStatement.setString(6, orderBean.getCreditCard().getName());
            preparedStatement.setString(7, orderBean.getCreditCard().getSurname());
            preparedStatement.setDate(8, orderBean.getOrderDate());
            preparedStatement.setDate(9, orderBean.getDeliveryDate());
            preparedStatement.setFloat(10, orderBean.getTotalPrice());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDelete(OrderBean orderBean) throws SQLException {
        String query = "DELETE FROM " + OrderDao.TABLE_NAME +
                       " WHERE codice_ordine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderBean.getOrderId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(OrderBean orderBean) throws SQLException {
        String query = "UPDATE " + OrderDao.TABLE_NAME +
                       " SET data_consegna = ?" +
                       " WHERE codice_ordine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, orderBean.getDeliveryDate());
            preparedStatement.setInt(2, orderBean.getOrderId());

            preparedStatement.executeUpdate();
        }
    }
}
