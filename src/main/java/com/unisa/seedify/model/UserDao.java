package com.unisa.seedify.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/storage");

        }
        catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static final String TABLE_NAME = "utenti";

    public static synchronized void doSave(UserBean user) throws SQLException {
        try (Connection connection = ds.getConnection()) {
            String query = "INSERT INTO " + UserDao.TABLE_NAME + " (email, password, nome, cognome, ruolo, fotoProfilo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getRuolo());
            statement.setString(6, user.getFotoProfilo());
            statement.executeUpdate();
        }
    }
}
