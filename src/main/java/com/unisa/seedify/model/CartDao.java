package com.unisa.seedify.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDao extends BaseDao implements GenericDao<CartBean>, DetailedDao<CartBean, CartItemBean> {
    public static final String TABLE_NAME = "carrelli";

    private static CartDao instance = null;

    private static final ProductDao productDao = ProductDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private CartDao() {
    }

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDao();
        }
        return instance;
    }

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
                    if (cartItemBean.getQuantity() == 0) {
                        this.doDeleteOne(cartBean, cartItemBean);
                    } else {
                        preparedStatement.setInt(3, cartItemBean.getProduct().getProductId());
                        preparedStatement.setInt(1, cartItemBean.getQuantity());
                        preparedStatement.executeUpdate();
                    }
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

    @Override
    public CartBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + CartDao.TABLE_NAME +
                       " WHERE email = ?";

        CartBean cartBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                cartBean = new CartBean();

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", email);
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                cartBean.setUser(userBean);

                List<CartItemBean> cartItems = cartBean.getCartItems();
                while (resultSet.next()) {
                    CartItemBean cartItemBean = new CartItemBean();

                    EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
                    productPrimaryKey.addKey("codice_prodotto", resultSet.getInt("codice_prodotto"));
                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);

                    cartItemBean.setProduct(productBean);
                    cartItemBean.setQuantity(resultSet.getInt("quantita"));
                    cartItems.add(cartItemBean);
                }

                cartBean.setCartItems(cartItems);
            }
        }
        return cartBean;
    }

    public boolean addToCart(CartBean cartBean, ProductBean productBean, int quantity) {
        if (productBean.getQuantity() < quantity) {
            return false;
        }

        CartItemBean cartItemBean = cartBean.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(productBean))
                .findFirst()
                .orElse(null);

        boolean cartReset = false;
        boolean newCartItem = cartItemBean == null;
        if (cartItemBean == null) {
            cartItemBean = new CartItemBean();
            cartItemBean.setProduct(productBean);
            cartItemBean.setQuantity(quantity);
        } else {
            if (productBean.getQuantity() < cartItemBean.getQuantity() + quantity) {
                cartItemBean.setQuantity(productBean.getQuantity());
                cartReset = true;
            } else {
                cartItemBean.setQuantity(cartItemBean.getQuantity() + quantity);
            }
        }

        boolean success = false;
        try {
            if (newCartItem) {
                this.doSaveOne(cartBean, cartItemBean);
            } else {
                this.doUpdate(cartBean);
            }
            cartBean.getCartItems().add(cartItemBean);
            success = true;
        } catch (SQLException ignored) {}

        return (success && !cartReset);

    }
}
