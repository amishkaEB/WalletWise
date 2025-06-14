package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");
        

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
}

