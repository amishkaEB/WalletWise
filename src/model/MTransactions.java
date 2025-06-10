package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Crypto;
import utils.DBConnection;
import utils.StatusType;

public class MTransactions {

    public List<Object[]> getTransactions(String timePeriod) throws Exception {
        List<Object[]> list = new ArrayList<>();
        String filterCondition = "";
        if (timePeriod.equals("This year")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE())";
        } else if (timePeriod.equals("This month")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND MONTH(t.date) = MONTH(CURDATE())";
        } else {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND WEEK(t.date, 1) = WEEK(CURDATE(), 1)";
        }

        String query = "SELECT t.id, t.date, t.amount, t.type, "
                + "CASE WHEN t.type = 'Expense' THEN a_from.name "
                + "     WHEN t.type = 'Income' THEN ic_from.name "
                + "     ELSE a2_from.name END AS fromName, "
                + "CASE WHEN t.type = 'Expense' THEN ec_to.name "
                + "     WHEN t.type = 'Income' THEN a_to.name "
                + "     ELSE a2_to.name END AS toName "
                + "FROM Transactions t "
                + "LEFT JOIN Accounts a_from ON t.`from` = a_from.id AND t.type = 'Expense' "
                + "LEFT JOIN IncomeCategory ic_from ON t.`from` = ic_from.id AND t.type = 'Income' "
                + "LEFT JOIN Accounts a2_from ON t.`from` = a2_from.id AND t.type = 'Transfer' "
                + "LEFT JOIN ExpenseCategory ec_to ON t.`to` = ec_to.id AND t.type = 'Expense' "
                + "LEFT JOIN Accounts a_to ON t.`to` = a_to.id AND t.type = 'Income' "
                + "LEFT JOIN Accounts a2_to ON t.`to` = a2_to.id AND t.type = 'Transfer' "
                + "WHERE " + filterCondition + " "
                + "ORDER BY t.date DESC";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            StatusType statusType;
            String amount = rs.getString("amount");
            String type = rs.getString("type");

            if ("Expense".equals(type)) {
                statusType = StatusType.EXPENSE;
            } else if ("Income".equals(type)) {
                statusType = StatusType.INCOME;
            } else {
                statusType = StatusType.TRANSFER;
            }

            String decryptedAmount = Crypto.decrypt(amount);
            Object[] row = {
                rs.getInt("id"),
                rs.getDate("date"),
                rs.getString("fromName"),
                rs.getString("toName"),
                decryptedAmount,
                statusType,
                "", ""
            };
            list.add(row);
        }
        return list;
    }

    public void deleteByID(int id) throws Exception {
        String query = "DELETE FROM Transactions WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Object[]> getTransactionsForExport(String timePeriod) throws Exception {
        List<Object[]> list = new ArrayList<>();
        String filterCondition = "";
        if (timePeriod.equals("This year")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE())";
        } else if (timePeriod.equals("This month")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND MONTH(t.date) = MONTH(CURDATE())";
        } else {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND WEEK(t.date, 1) = WEEK(CURDATE(), 1)";
        }

        String query = "SELECT t.id, t.date, t.amount, t.type, "
                + "CASE WHEN t.type = 'Expense' THEN a_from.name "
                + "     WHEN t.type = 'Income' THEN ic_from.name "
                + "     ELSE a2_from.name END AS fromName, "
                + "CASE WHEN t.type = 'Expense' THEN ec_to.name "
                + "     WHEN t.type = 'Income' THEN a_to.name "
                + "     ELSE a2_to.name END AS toName "
                + "FROM Transactions t "
                + "LEFT JOIN Accounts a_from ON t.`from` = a_from.id AND t.type = 'Expense' "
                + "LEFT JOIN IncomeCategory ic_from ON t.`from` = ic_from.id AND t.type = 'Income' "
                + "LEFT JOIN Accounts a2_from ON t.`from` = a2_from.id AND t.type = 'Transfer' "
                + "LEFT JOIN ExpenseCategory ec_to ON t.`to` = ec_to.id AND t.type = 'Expense' "
                + "LEFT JOIN Accounts a_to ON t.`to` = a_to.id AND t.type = 'Income' "
                + "LEFT JOIN Accounts a2_to ON t.`to` = a2_to.id AND t.type = 'Transfer' "
                + "WHERE " + filterCondition + " "
                + "ORDER BY t.date DESC";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<TransactionRowExport> transactionList = new ArrayList<>();
        while (rs.next()) {
            StatusType statusType;
            String amount = rs.getString("amount");
            String type = rs.getString("type");
            String decryptedAmount = Crypto.decrypt(amount);

            Object[] row = {
                rs.getDate("date"),
                type,
                rs.getString("fromName"),
                rs.getString("toName"),
                decryptedAmount,
            };
            list.add(row);
        }
        return list;
    }

    public class TransactionRowExport {

        private int id;
        private Date date;
        private String fromName;
        private String toName;
        private String amount;
        private String status;

        public TransactionRowExport(int id, Date date, String fromName, String toName, String amount, String status) {
            this.id = id;
            this.date = date;
            this.fromName = fromName;
            this.toName = toName;
            this.amount = amount;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public Date getDate() {
            return date;
        }

        public String getFromName() {
            return fromName;
        }

        public String getToName() {
            return toName;
        }

        public String getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }
    }

}
