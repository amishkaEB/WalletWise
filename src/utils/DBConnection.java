package utils;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost/walletwise";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static DBConnection instance;

    private DBConnection() {
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
