package com.pgbezerra.sample.database;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java15";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "1234567";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (Exception e) {
            throw new DatabaseException("Error on connect");
        }
    }

}
