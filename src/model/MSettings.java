package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnection;

public class MSettings {

    public Object[] getSettings() {
        String query = "SELECT * FROM Settings LIMIT 1";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Object[]{
                    rs.getString("Symbol"),
                    rs.getBoolean("SymbolFirst"),
                    rs.getBoolean("Seperator"),
                    rs.getString("StartScreen")
                };
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateSettings(String symbol, boolean symbolFirst, boolean separator, String startScreen) {
        String query = "UPDATE Settings SET Symbol = ?, SymbolFirst = ?, Seperator = ?, StartScreen = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, symbol);
            ps.setBoolean(2, symbolFirst);
            ps.setBoolean(3, separator);
            ps.setString(4, startScreen);

            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error updating settings: " + e.getMessage());
        }
    }

};
