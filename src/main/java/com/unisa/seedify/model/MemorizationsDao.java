package com.unisa.seedify.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemorizationsDao extends BaseDao implements GenericDao<MemorizationsBean>, DetailedDao<MemorizationsBean, CreditCardBean> {
    public static final String TABLE_NAME = "memorizzazioni";

    private static MemorizationsDao instance = null;

    private static final UserDao userDao = UserDao.getInstance();
    private static final CreditCardDao creditCardDao = CreditCardDao.getInstance();

    private MemorizationsDao() {
    }

    public static MemorizationsDao getInstance() {
        if (instance == null) {
            instance = new MemorizationsDao();
        }
        return instance;
    }

    @Override
    public void doSave(MemorizationsBean memorizationsBeans) throws SQLException {
        String query = "INSERT INTO " + MemorizationsDao.TABLE_NAME +
                       " (email, codice_carta) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);

            try {
                preparedStatement.setString(1, memorizationsBeans.getUser().getEmail());

                for (CreditCardBean creditCardBean : memorizationsBeans.getCreditCards()) {
                    preparedStatement.setInt(2, creditCardBean.getCardId());

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
    public void doDelete(MemorizationsBean memorizationsBeans) throws SQLException {
        String query = "DELETE FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ? AND codice_carta = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try {
                preparedStatement.setString(1, memorizationsBeans.getUser().getEmail());

                for (CreditCardBean creditCardBean : memorizationsBeans.getCreditCards()) {
                    preparedStatement.setInt(2, creditCardBean.getCardId());

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
    public void doUpdate(MemorizationsBean memorizationsBeans) throws SQLException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doSaveOne(MemorizationsBean memorizationsBean, CreditCardBean creditCardBean) throws SQLException {
        memorizationsBean.getCreditCards().add(creditCardBean);

        String query = "INSERT INTO " + MemorizationsDao.TABLE_NAME +
                       " (email, codice_carta) " +
                       " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, memorizationsBean.getUser().getEmail());
            preparedStatement.setInt(2, creditCardBean.getCardId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doDeleteOne(MemorizationsBean collection, CreditCardBean bean) throws SQLException {
        collection.getCreditCards().remove(bean);

        String query = "UPDATE " + MemorizationsDao.TABLE_NAME +
                       " SET stato = 'ELIMINATO' " +
                       " WHERE email = ? AND codice_carta = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, collection.getUser().getEmail());
            preparedStatement.setInt(2, bean.getCardId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdateOne(MemorizationsBean collection, CreditCardBean bean) throws SQLException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemorizationsBean doRetrive(EntityPrimaryKey primaryKey) throws SQLException {
        String email = (String) primaryKey.getKey("email");

        String query = "SELECT * FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ? AND stato = 'ATTIVO'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            MemorizationsBean memorizationsBean = null;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                memorizationsBean = new MemorizationsBean();

                EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
                userPrimaryKey.addKey("email", email);
                UserBean userBean = userDao.doRetrive(userPrimaryKey);
                memorizationsBean.setUser(userBean);

                List<CreditCardBean> creditCards = memorizationsBean.getCreditCards();
                while (resultSet.next()) {
                    EntityPrimaryKey creditCardPrimaryKey = new EntityPrimaryKey();
                    creditCardPrimaryKey.addKey("codice_carta", resultSet.getInt("codice_carta"));
                    CreditCardBean creditCardBean = creditCardDao.doRetrive(creditCardPrimaryKey);
                    memorizationsBean.getCreditCards().add(creditCardBean);;
                }

                return memorizationsBean;
            }
        }
    }

    public ArrayList<CreditCardBean> getAllCreditCards(UserBean userBean)  {
        ArrayList<CreditCardBean> creditCards = new ArrayList<>();

        String query = "SELECT * FROM " + MemorizationsDao.TABLE_NAME +
                       " WHERE email = ? AND stato = 'ATTIVO'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userBean.getEmail());

            MemorizationsBean memorizationsBean = null;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                memorizationsBean = new MemorizationsBean();

                creditCards = (ArrayList<CreditCardBean>) memorizationsBean.getCreditCards();
                while (resultSet.next()) {
                    EntityPrimaryKey creditCardPrimaryKey = new EntityPrimaryKey();
                    creditCardPrimaryKey.addKey("codice_carta", resultSet.getInt("codice_carta"));
                    CreditCardBean creditCardBean = creditCardDao.doRetrive(creditCardPrimaryKey);
                    creditCards.add(creditCardBean);
                }

            } catch (SQLException ignored){}
        } catch (SQLException ignored){}
        return creditCards;
    }
}
