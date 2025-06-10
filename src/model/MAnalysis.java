package model;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Crypto;
import utils.DBConnection;

public class MAnalysis {

    public List<Object[]> getData(String timePeriod, String type) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Map<String, TransactionData> groupedTransactions = new HashMap<>();
        String filterCondition = "";
        String query = "";

        if (timePeriod.equals("This year")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE())";
        } else if (timePeriod.equals("This month")) {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND MONTH(t.date) = MONTH(CURDATE())";
        } else {
            filterCondition = "YEAR(t.date) = YEAR(CURDATE()) AND WEEK(t.date, 1) = WEEK(CURDATE(), 1)";
        }

        if (type.equals("Expense")) {
            query = "SELECT ec.name AS category_name, ec.color AS color, t.amount "
                    + "FROM Transactions t "
                    + "JOIN expensecategory ec ON t.to = ec.id "
                    + "WHERE t.type = 'Expense' AND " + filterCondition;
        } else {
            query = "SELECT ic.name AS category_name, ic.color AS color, t.amount "
                    + "FROM Transactions t "
                    + "JOIN incomecategory ic ON t.from = ic.id "
                    + "WHERE t.type = 'Income' AND " + filterCondition;
        }

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            String color = rs.getString(2);
            String amount = rs.getString(3);

            String decryptedAmount = Crypto.decrypt(amount);
            Object[] row = {
                name,
                color,
                decryptedAmount
            };
            list.add(row);
        }

        for (Object[] row : list) {
            String name = (String) row[0];
            String hexString = (String) row[1];
            Color color = Color.decode(hexString);
            int amount = Integer.parseInt((String) row[2]);

            TransactionData currentData = groupedTransactions.getOrDefault(name, new TransactionData(color, 0));

            currentData.amount += amount;

            groupedTransactions.put(name, currentData);
        }

        List<Object[]> groupedList = new ArrayList<>();
        for (Map.Entry<String, TransactionData> entry : groupedTransactions.entrySet()) {
            groupedList.add(new Object[]{entry.getKey(), entry.getValue().color, entry.getValue().amount});
        }

        return groupedList;

    }

    class TransactionData {

        Color color;
        int amount;

        public TransactionData(Color color, int amount) {
            this.color = color;
            this.amount = amount;
        }
    }
}
