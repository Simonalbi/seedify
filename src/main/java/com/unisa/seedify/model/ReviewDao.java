package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDao extends BaseDao implements GenericDao<ReviewBean> {
    public static final String TABLE_NAME = "recensioni";

    private static ReviewDao instance = null;

    private static final ProductDao productDao = ProductDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private ReviewDao() {
    }
    
    public static ReviewDao getInstance() {
        if (instance == null) {
            instance = new ReviewDao();
        }
        return instance;
    }

    @Override
    public void doSave(ReviewBean reviewBean) throws SQLException {
        String query = "INSERT INTO " + ReviewDao.TABLE_NAME +
                       " (email, codice_prodotto, commento, numero_stelle, data_aggiunta) " +
                       " VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reviewBean.getUser().getEmail());
            preparedStatement.setInt(2, reviewBean.getProduct().getProductId());
            preparedStatement.setString(3, reviewBean.getComment());
            preparedStatement.setInt(4, reviewBean.getStarRating());
            preparedStatement.setDate(5, reviewBean.getDateAdded());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDelete(ReviewBean reviewBean) throws SQLException {
        String query = "DELETE FROM " + ReviewDao.TABLE_NAME +
                       " WHERE codice_prodotto = ? AND email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reviewBean.getProduct().getProductId());
            preparedStatement.setString(2, reviewBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(ReviewBean reviewBean) throws SQLException {
        String query = "UPDATE " + ReviewDao.TABLE_NAME +
                       " SET commento = ?, numero_stelle = ? " +
                       " WHERE codice_prodotto = ? AND email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reviewBean.getComment());
            preparedStatement.setInt(2, reviewBean.getStarRating());
            preparedStatement.setInt(3, reviewBean.getProduct().getProductId());
            preparedStatement.setString(4, reviewBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ReviewBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int productId = (int) primaryKey.getKey("codice_prodotto");
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + ReviewDao.TABLE_NAME +
                       " WHERE codice_prodotto = ? AND email = ?";

        ReviewBean reviewBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reviewBean = new ReviewBean();

                    EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                    userPrimaryKey.addKey("email", email);
                    UserBean userBean = userDao.doRetrive(userPrimaryKey);
                    reviewBean.setUser(userBean);

                    EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
                    productPrimaryKey.addKey("codice_prodotto", productId);
                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);
                    reviewBean.setProduct(productBean);

                    reviewBean.setComment(resultSet.getString("commento"));
                    reviewBean.setStarRating(resultSet.getInt("numero_stelle"));
                    reviewBean.setDateAdded(resultSet.getDate("data_aggiunta"));
                }
            }
        }

        return reviewBean;
    }
}
