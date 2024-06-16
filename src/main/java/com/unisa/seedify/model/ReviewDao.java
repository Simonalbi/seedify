package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDao extends BaseDao implements GenericDao<ReviewBean> {
    private static final String TABLE_NAME = "recensioni";

    @Override
    public void doSave(ReviewBean reviewBean) throws SQLException {
        String query =  "INSERT INTO " + ReviewDao.TABLE_NAME +
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
        String query =  "DELETE FROM " + ReviewDao.TABLE_NAME +
                        " WHERE codice_recensione = ? and codice_prodotto = ? and email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reviewBean.getReviewId());
            preparedStatement.setInt(2, reviewBean.getProduct().getProductId());
            preparedStatement.setString(3, reviewBean.getUser().getEmail());

            preparedStatement.executeUpdate();
        }
    }
}
