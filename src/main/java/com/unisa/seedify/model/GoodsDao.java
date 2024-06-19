package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public GoodsBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int orderId = (int) primaryKey.getKey("codice_ordine");

        String query = "SELECT * FROM " + GoodsDao.TABLE_NAME +
                       " WHERE codice_ordine = ?";

        GoodsBean goodsBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                goodsBean = new GoodsBean();

                OrderDao orderDao = new OrderDao();
                EntityPrimaryKey orderPrimaryKey = new EntityPrimaryKey();
                orderPrimaryKey.addKey("codice_ordine", orderId);
                OrderBean orderBean = orderDao.doRetrive(orderPrimaryKey);
                goodsBean.setOrder(orderBean);

                ProductDao productDao = new ProductDao();
                List<GoodsItemBean> goods = goodsBean.getGoods();
                while (resultSet.next()) {
                    GoodsItemBean goodsItemBean = new GoodsItemBean();

                    EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
                    productPrimaryKey.addKey("codice_prodotto", resultSet.getInt("codice_prodotto"));
                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);
                    goodsItemBean.setProduct(productBean);

                    goodsItemBean.setQuantity(resultSet.getInt("quantita"));

                    goods.add(goodsItemBean);
                }

                goodsBean.setGoods(goods);
            }
        }

        return goodsBean;
    }
}
