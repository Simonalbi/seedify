package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FavoritesDao extends BaseDao implements GenericDao<FavoritesBean>, DetailedDao<FavoritesBean, ProductBean> {
    private static final String TABLE_NAME = "preferiti";

    private static FavoritesDao instance = null;

    private static final ProductDao productDao = ProductDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private FavoritesDao() {
    }

    public static FavoritesDao getInstance() {
        if (instance == null) {
            instance = new FavoritesDao();
        }
        return instance;
    }

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

    @Override
    public FavoritesBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + FavoritesDao.TABLE_NAME +
                       " WHERE email = ?";

        FavoritesBean favoritesBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                favoritesBean = new FavoritesBean();

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", email);
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                favoritesBean.setUser(userBean);

                List<ProductBean> favorites = favoritesBean.getProducts();
                while (resultSet.next()) {
                    EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
                    productPrimaryKey.addKey("codice_prodotto", resultSet.getInt("codice_prodotto"));
                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);

                    favorites.add(productBean);
                }

                favoritesBean.setProducts(favorites);
            }
        }
        
        return favoritesBean;
    }

    public boolean addToFavorites(UserBean user, ProductBean product) throws SQLException {
        String query = "INSERT INTO " + FavoritesDao.TABLE_NAME +
                       " (email, codice_prodotto) " +
                       " VALUES (?, ?)";

        boolean success = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, product.getProductId());

            preparedStatement.executeUpdate();
            success = true;
        }

        return success;
    }

    public boolean removeFromFavorites(UserBean user, ProductBean product) throws SQLException {
        String query = "DELETE FROM " + FavoritesDao.TABLE_NAME +
                       " WHERE email = ? AND codice_prodotto = ?";

        boolean success = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, product.getProductId());

            preparedStatement.executeUpdate();
            success = true;
        }

        return success;
    }
}
