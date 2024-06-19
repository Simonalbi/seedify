package com.unisa.seedify.model;

import java.sql.SQLException;

public interface DetailedDao<T, E> {
    void doSaveOne(T collection, E bean) throws SQLException;
    void doDeleteOne(T collection, E bean) throws SQLException;
    void doUpdateOne(T collection, E bean) throws SQLException;
}
