package com.unisa.seedify.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoritesDao extends BaseDao implements GenericDao<FavoritesBean>, DetailedDao<FavoritesBean, ProductBean> {
    private static final String TABLE_NAME = "preferiti";

    @Override
    public void doSave(FavoritesBean favoritesBean) throws SQLException {
        String query = "INSERT INTO " + FavoritesDao.TABLE_NAME +
                       " (email, codice_prodotto) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(1, favoritesBean.getUser().getEmail());

                for (ProductBean productBean: favoritesBean.getProducts()) {
                    preparedStatement.setInt(2, productBean.getProductId());

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
    public void doDelete(FavoritesBean favoritesBean) throws SQLException {
        String query = "DELETE FROM " + FavoritesDao.TABLE_NAME +
                       " WHERE email = ? ";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, favoritesBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(FavoritesBean favoritesBean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void doSaveOne(FavoritesBean favoritesBean, ProductBean productBean) throws SQLException {
        favoritesBean.getProducts().add(productBean);

        String query = "INSERT INTO " + FavoritesDao.TABLE_NAME +
                       " (email, codice_prodotto) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, favoritesBean.getUser().getEmail());
            preparedStatement.setInt(2, productBean.getProductId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDeleteOne(FavoritesBean favoritesBean, ProductBean productBean) throws SQLException {
        favoritesBean.getProducts().remove(productBean);

        String query = "DELETE FROM " + FavoritesDao.TABLE_NAME +
                       " WHERE email = ? AND codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, favoritesBean.getUser().getEmail());
            preparedStatement.setInt(2, productBean.getProductId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdateOne(FavoritesBean favoritesBean, ProductBean productBean) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }
}
