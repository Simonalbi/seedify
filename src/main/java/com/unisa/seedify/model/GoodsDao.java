package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GoodsDao extends BaseDao implements GenericDao<GoodsBean> {
    private static final String TABLE_NAME = "merce";

    @Override
    public void doSave(GoodsBean goodsBean) throws SQLException {
        String query = "INSERT INTO " + GoodsDao.TABLE_NAME +
                       " (codice_ordine, codice_prodotto, quantita) " +
                       " VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setInt(1, goodsBean.getOrder().getOrderId());

                for (GoodsItemBean goodsItemBean : goodsBean.getGoods()) {
                    preparedStatement.setInt(2, goodsItemBean.getProduct().getProductId());
                    preparedStatement.setInt(3, goodsItemBean.getQuantity());

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
    public void doDelete(GoodsBean goodsBean) throws SQLException {
        String query = "DELETE FROM " + GoodsDao.TABLE_NAME +
                       " WHERE codice_ordine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, goodsBean.getOrder().getOrderId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(GoodsBean goodsBean) throws SQLException {
        String query = "UPDATE " + GoodsDao.TABLE_NAME +
                       " SET quantita = ? " +
                       " WHERE codice_ordine = ? AND codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setInt(2, goodsBean.getOrder().getOrderId());

                for (GoodsItemBean goodsItemBean : goodsBean.getGoods()) {
                    preparedStatement.setInt(3, goodsItemBean.getProduct().getProductId());
                    preparedStatement.setInt(1, goodsItemBean.getQuantity());

                    preparedStatement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
