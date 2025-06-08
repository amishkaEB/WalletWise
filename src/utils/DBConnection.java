package utils;

import java.sql.*;


public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost/walletwise?autoReconnect=true&useSSL=false";
        Connection con = DriverManager.getConnection(url, "root", "");
        return con;
    }
}



