package model;

import java.sql.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import utils.Crypto;
import utils.DBConnection;

public class MAddTransaction {

    private List<Map<String, Object>> fetchTable(String tableName) {
        List<Map<String, Object>> rows = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int cols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= cols; i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                rows.add(row);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return rows;
    }

    public Map<String, List<Map<String, Object>>> fetchAllTables() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Map<String, List<Map<String, Object>>> result = Collections.synchronizedMap(new HashMap<>());

        Runnable fetchExpenseCategories = () -> {
            result.put("ExpenseCategories", fetchTable("expensecategory"));
            latch.countDown();
        };

        Runnable fetchIncomeCategories = () -> {
            result.put("IncomeCategories", fetchTable("incomecategory"));
            latch.countDown();
        };

        Runnable fetchAccounts = () -> {
            result.put("Accounts", fetchTable("accounts"));
            latch.countDown();
        };

        new Thread(fetchExpenseCategories).start();
        new Thread(fetchIncomeCategories).start();
        new Thread(fetchAccounts).start();

        latch.await();
        return result;
    }

    public void insertTransaction(String type, int from, int to, String amount, String date) throws Exception {
        String sql = "INSERT INTO Transactions (type, `from`, `to`, amount, date) VALUES (?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        String encryptedAmount = Crypto.encrypt(amount);

        stmt.setString(1, type);
        stmt.setInt(2, from);
        stmt.setInt(3, to);
        stmt.setString(4, encryptedAmount);
        stmt.setDate(5, java.sql.Date.valueOf(date));
        stmt.executeUpdate();
    }
    
    public void updateTransaction(int id, String type, int from, int to, String amount, String date) throws Exception {
        String sql = "UPDATE Transactions SET type = ?, `from` = ?, `to` = ?, amount = ?, date =? WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        String encryptedAmount = Crypto.encrypt(amount);

        stmt.setString(1, type);
        stmt.setInt(2, from);
        stmt.setInt(3, to);
        stmt.setString(4, encryptedAmount);
        stmt.setDate(5, java.sql.Date.valueOf(date));
        stmt.setInt(6, id);
        stmt.executeUpdate();
    }

}
