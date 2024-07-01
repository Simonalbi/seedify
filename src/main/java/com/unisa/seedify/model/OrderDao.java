package com.unisa.seedify.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao implements GenericDao<OrderBean> {
    public static final String TABLE_NAME = "ordini";

    private static OrderDao instance = null;

    private static final AddressDao addressDao = AddressDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    @Override
    public void doSave(OrderBean orderBean) throws SQLException {
        String query = "INSERT INTO " + OrderDao.TABLE_NAME +
                       " (codice_indirizzo, email, numero_carta, cvv, scadenza, nome, cognome, data_ordine, data_consegna, prezzo_totale) " +
                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderBean.getAddress().getAddressId());
            preparedStatement.setString(2, orderBean.getUser().getEmail());
            preparedStatement.setString(3, orderBean.getCreditCard().getCardNumber());
            preparedStatement.setString(4, orderBean.getCreditCard().getCvv());
            preparedStatement.setDate(5, orderBean.getCreditCard().getExpirationDate());
            preparedStatement.setString(6, orderBean.getCreditCard().getName());
            preparedStatement.setString(7, orderBean.getCreditCard().getSurname());
            preparedStatement.setDate(8, orderBean.getOrderDate());
            preparedStatement.setDate(9, orderBean.getDeliveryDate());
            preparedStatement.setFloat(10, orderBean.getTotalPrice());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDelete(OrderBean orderBean) throws SQLException {
        String query = "DELETE FROM " + OrderDao.TABLE_NAME +
                       " WHERE codice_ordine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderBean.getOrderId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(OrderBean orderBean) throws SQLException {
        String query = "UPDATE " + OrderDao.TABLE_NAME +
                       " SET data_consegna = ?" +
                       " WHERE codice_ordine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, orderBean.getDeliveryDate());
            preparedStatement.setInt(2, orderBean.getOrderId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public OrderBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        int orderId = (int) primaryKey.getKey("codice_ordine");

        String query = "SELECT * FROM " + OrderDao.TABLE_NAME +
                       " WHERE codice_ordine = ?";

        OrderBean orderBean = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orderBean = new OrderBean();

                    orderBean.setOrderId(resultSet.getInt("codice_ordine"));

                    CreditCardBean creditCardBean = new CreditCardBean();
                    creditCardBean.setCardNumber(resultSet.getString("numero_carta"));
                    creditCardBean.setCvv(resultSet.getString("cvv"));
                    creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                    creditCardBean.setName(resultSet.getString("nome"));
                    creditCardBean.setSurname(resultSet.getString("cognome"));
                    orderBean.setCreditCard(creditCardBean);

                    EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                    userPrimaryKey.addKey("email", resultSet.getString("email"));
                    UserBean userBean = userDao.doRetrive(userPrimaryKey);
                    orderBean.setUser(userBean);

                    EntityPrimaryKey addressPrimaryKey = new EntityPrimaryKey();
                    addressPrimaryKey.addKey("codice_indirizzo", resultSet.getInt("codice_indirizzo"));
                    AddressBean addressBean = addressDao.doRetrive(addressPrimaryKey);
                    orderBean.setAddress(addressBean);

                    orderBean.setOrderDate(resultSet.getDate("data_ordine"));
                    orderBean.setDeliveryDate(resultSet.getDate("data_consegna"));
                    orderBean.setTotalPrice(resultSet.getFloat("prezzo_totale"));

                    return orderBean;
                }
            }
        }

        return orderBean;
    }

    public int getTotalOrders() {
        String query = "SELECT COUNT(*) AS orders_count FROM " + OrderDao.TABLE_NAME;

        int ordersAmount = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            if (resultSet.next()) {
                ordersAmount = resultSet.getInt("orders_count");
            }
        } catch (SQLException ignored) {}

        return ordersAmount;
    }

    public int getTotalOrders(UserBean userBean) {
        String query = "SELECT COUNT(*) AS orders_count FROM " + OrderDao.TABLE_NAME +
                       " WHERE email = ?";

        int ordersAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userBean.getEmail());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ordersAmount = resultSet.getInt("orders_count");
                }
            }
        } catch (SQLException ignored) {}

        return ordersAmount;
    }

    public List<OrderBean> getAllOrders() {
        String query = "SELECT * FROM " + OrderDao.TABLE_NAME;

        List<OrderBean> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                OrderBean orderBean = new OrderBean();

                orderBean.setOrderId(resultSet.getInt("codice_ordine"));

                CreditCardBean creditCardBean = new CreditCardBean();
                creditCardBean.setCardNumber(resultSet.getString("numero_carta"));
                creditCardBean.setCvv(resultSet.getString("cvv"));
                creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                creditCardBean.setName(resultSet.getString("nome"));
                creditCardBean.setSurname(resultSet.getString("cognome"));
                orderBean.setCreditCard(creditCardBean);

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", resultSet.getString("email"));
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                orderBean.setUser(userBean);

                EntityPrimaryKey addressPrimaryKey = new EntityPrimaryKey();
                addressPrimaryKey.addKey("codice_indirizzo", resultSet.getInt("codice_indirizzo"));
                AddressBean addressBean = addressDao.doRetrive(addressPrimaryKey);
                orderBean.setAddress(addressBean);

                orderBean.setOrderDate(resultSet.getDate("data_ordine"));
                orderBean.setDeliveryDate(resultSet.getDate("data_consegna"));
                orderBean.setTotalPrice(resultSet.getFloat("prezzo_totale"));

                orders.add(orderBean);
            }
        } catch (SQLException ignored) {}

        return orders;
    }

    public List<OrderBean> getAllOrders(UserBean userBean) {
        String query = "SELECT * FROM " + OrderDao.TABLE_NAME +
                       " WHERE email = ? ;";

        List<OrderBean> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, userBean.getEmail());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderBean orderBean = new OrderBean();

                    orderBean.setOrderId(resultSet.getInt("codice_ordine"));

                    CreditCardBean creditCardBean = new CreditCardBean();
                    creditCardBean.setCardNumber(resultSet.getString("numero_carta"));
                    creditCardBean.setCvv(resultSet.getString("cvv"));
                    creditCardBean.setExpirationDate(resultSet.getDate("scadenza"));
                    creditCardBean.setName(resultSet.getString("nome"));
                    creditCardBean.setSurname(resultSet.getString("cognome"));
                    orderBean.setCreditCard(creditCardBean);
                    orderBean.setUser(userBean);

                    EntityPrimaryKey addressPrimaryKey = new EntityPrimaryKey();
                    addressPrimaryKey.addKey("codice_indirizzo", resultSet.getInt("codice_indirizzo"));
                    AddressBean addressBean = addressDao.doRetrive(addressPrimaryKey);
                    orderBean.setAddress(addressBean);

                    orderBean.setOrderDate(resultSet.getDate("data_ordine"));
                    orderBean.setDeliveryDate(resultSet.getDate("data_consegna"));
                    orderBean.setTotalPrice(resultSet.getFloat("prezzo_totale"));

                    orders.add(orderBean);
                }
            }
        } catch (SQLException ignored) {}

        return orders;
    }
}
