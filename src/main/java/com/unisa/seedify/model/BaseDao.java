package com.unisa.seedify.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class BaseDao {
    protected static DataSource dataSource;
    static {
        try {
            Context initialContext = new InitialContext();
            Context enviromentContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) enviromentContext.lookup("jdbc/seedify");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
