package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

public class MAccounts {

    public List<Object[]> getAllCategories() {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT * FROM Accounts";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    "", ""
                };
                list.add(row);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public boolean deleteCategory(int id) {
        String query = "DELETE FROM Accounts WHERE id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean insertCategory(String catName, String categoryColor) {
        String sql = "INSERT INTO Accounts (name, color) VALUES (?, ?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, catName);
            stmt.setString(2, categoryColor);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean updateCategory(String catName, String categoryColor, int editIndex) {
        String sql = "UPDATE Accounts SET name = ?, color = ? WHERE id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, catName);
            stmt.setString(2, categoryColor);
            stmt.setInt(3, editIndex);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
