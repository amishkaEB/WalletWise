package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBConnection;

public class MSettings {

    public Object[] getSettings() throws Exception {
        String query = "SELECT * FROM Settings LIMIT 1";
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

        return null;
    }

    public void updateSettings(String symbol, boolean symbolFirst, boolean separator, String startScreen) throws Exception {
        String query = "UPDATE Settings SET Symbol = ?, SymbolFirst = ?, Seperator = ?, StartScreen = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, symbol);
        ps.setBoolean(2, symbolFirst);
        ps.setBoolean(3, separator);
        ps.setString(4, startScreen);

        ps.executeUpdate();

    }

};
