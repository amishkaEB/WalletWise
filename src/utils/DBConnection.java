package utils;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost/walletwise";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final DBConnection instance = new DBConnection();
    private Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public static DBConnection getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}