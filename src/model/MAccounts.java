package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

public class MAccounts {

    public List<Object[]> getAllCategories() throws Exception {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT * FROM Accounts";

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
        return list;
    }

    public void deleteCategory(int id) throws Exception {
        String query = "DELETE FROM Accounts WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void insertCategory(String catName, String categoryColor) throws Exception {
        String sql = "INSERT INTO Accounts (name, color) VALUES (?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, catName);
        stmt.setString(2, categoryColor);
        stmt.executeUpdate();
    }

    public void updateCategory(String catName, String categoryColor, int editIndex) throws Exception {
        String sql = "UPDATE Accounts SET name = ?, color = ? WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, catName);
        stmt.setString(2, categoryColor);
        stmt.setInt(3, editIndex);
        stmt.executeUpdate();
    }
}
