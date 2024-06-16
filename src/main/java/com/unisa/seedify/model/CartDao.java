package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDao extends BaseDao implements GenericDao<CartBean>, DetailedDao<CartBean, CartItemBean> {
    private static final String TABLE_NAME = "carrelli";

    @Override
    public void doSave(CartBean cartBean) throws SQLException {
        String query = "INSERT INTO " + CartDao.TABLE_NAME +
                       " (email, codice_prodotto, quantita) " +
                       " VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(1, cartBean.getUser().getEmail());

                for (CartItemBean cartItemBean: cartBean.getCartItems()) {
                    preparedStatement.setInt(2, cartItemBean.getProduct().getProductId());
                    preparedStatement.setInt(3, cartItemBean.getQuantity());

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
    public void doDelete(CartBean cartBean) throws SQLException {
        String query = "DELETE FROM " + CartDao.TABLE_NAME +
                       " WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cartBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(CartBean cartBean) throws SQLException {
        String query = "UPDATE " + CartDao.TABLE_NAME +
                       " SET quantita = ? " +
                       " WHERE email = ? AND codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(2, cartBean.getUser().getEmail());

                for (CartItemBean cartItemBean: cartBean.getCartItems()) {
                    preparedStatement.setInt(3, cartItemBean.getProduct().getProductId());
                    preparedStatement.setInt(1, cartItemBean.getQuantity());

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
    public void doSaveOne(CartBean cartBean, CartItemBean cartItemBean) throws SQLException {
        String query = "INSERT INTO " + CartDao.TABLE_NAME +
                       " (email, codice_prodotto, quantita) " +
                       " VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cartBean.getUser().getEmail());
            preparedStatement.setInt(2, cartItemBean.getProduct().getProductId());
            preparedStatement.setInt(3, cartItemBean.getQuantity());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDeleteOne(CartBean cartBean, CartItemBean cartItemBean) throws SQLException {
        String query = "DELETE FROM " + CartDao.TABLE_NAME +
                       " WHERE email = ? AND codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cartBean.getUser().getEmail());
            preparedStatement.setInt(2, cartItemBean.getProduct().getProductId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdateOne(CartBean cartBean, CartItemBean updatedCartItemBean) throws SQLException {
        String query = "UPDATE " + CartDao.TABLE_NAME +
                       " SET quantita = ? " +
                       " WHERE email = ? AND codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(2, cartBean.getUser().getEmail());
            preparedStatement.setInt(3, updatedCartItemBean.getProduct().getProductId());

            boolean found = false;
            for (CartItemBean cartItemBean : cartBean.getCartItems()) {
                if (cartItemBean.equals(updatedCartItemBean)) {
                    preparedStatement.setInt(1, updatedCartItemBean.getQuantity());
                    preparedStatement.executeUpdate();
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new SQLException("CartItemBean not found in CartBean");
            }
        }
    }
}
