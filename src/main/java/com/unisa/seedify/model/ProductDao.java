package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends BaseDao implements GenericDao<ProductBean> {
    private static final String TABLE_NAME = "prodotti";

    private static ProductDao instance = null;

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    @Override
    public void doSave(ProductBean productBean) throws SQLException {
        String query = "INSERT INTO " + ProductDao.TABLE_NAME +
                       " (nome, immagine, prezzo, quantita, stagionalita, quantita_acqua, tipologia_pianta, descrizione, data_aggiunta)" +
                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, productBean.getName());
            preparedStatement.setBytes(2, productBean.getImage());
            preparedStatement.setFloat(3, productBean.getPrice());
            preparedStatement.setInt(4, productBean.getQuantity());
            preparedStatement.setString(5, productBean.getSeason().toString());
            preparedStatement.setString(6, productBean.getRequiredWater().toString());
            preparedStatement.setString(7, productBean.getPlantType());
            preparedStatement.setString(8, productBean.getDescription());
            preparedStatement.setDate(9, productBean.getAddedDate());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDelete(ProductBean productBean) throws SQLException {
            String query = "DELETE FROM " + ProductDao.TABLE_NAME +
                           " WHERE codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productBean.getProductId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(ProductBean productBean) throws SQLException {
        String query = "UPDATE " + ProductDao.TABLE_NAME +
                       " SET nome = ?, immagine = ?, prezzo = ?, quantita = ?, stagionalita = ?, quantita_acqua = ?, tipologia_pianta = ?, descrizione = ?, data_aggiunta = ?" +
                       " WHERE codice_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, productBean.getName());
            preparedStatement.setBytes(2, productBean.getImage());
            preparedStatement.setFloat(3, productBean.getPrice());
            preparedStatement.setInt(4, productBean.getQuantity());
            preparedStatement.setString(5, productBean.getSeason().toString());
            preparedStatement.setString(6, productBean.getRequiredWater().toString());
            preparedStatement.setString(7, productBean.getPlantType());
            preparedStatement.setString(8, productBean.getDescription());
            preparedStatement.setDate(9, productBean.getAddedDate());
            preparedStatement.setInt(10, productBean.getProductId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ProductBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int productId = (int) primaryKey.getKey("codice_prodotto");

        String query = "SELECT * FROM " + ProductDao.TABLE_NAME +
                       " WHERE codice_prodotto = ?";

        ProductBean productBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productBean = new ProductBean();
                    productBean.setProductId(resultSet.getInt("codice_prodotto"));
                    productBean.setName(resultSet.getString("nome"));
                    productBean.setImage(resultSet.getBytes("immagine"));
                    productBean.setPrice(resultSet.getFloat("prezzo"));
                    productBean.setQuantity(resultSet.getInt("quantita"));
                    productBean.setSeason(ProductBean.Seasons.valueOf(resultSet.getString("stagionalita")));
                    productBean.setRequiredWater(ProductBean.RequiredWater.valueOf(resultSet.getString("quantita_acqua")));
                    productBean.setPlantType(resultSet.getString("tipologia_pianta"));
                    productBean.setDescription(resultSet.getString("descrizione"));
                    productBean.setAddedDate(resultSet.getDate("data_aggiunta"));
                }
            }
        }
        return productBean;
    }
}
