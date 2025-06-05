package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

public class MAccounts {

    public List<Object[]> getAllCategories() {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT * FROM Accounts";
        try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    "",""
                };
                list.add(row);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }



    public void deleteCategory(int id) {
        String query = "DELETE FROM Accounts WHERE id = ?";
        try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
