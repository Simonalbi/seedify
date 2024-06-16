package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDao extends BaseDao implements GenericDao<CartBean> {
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
}
