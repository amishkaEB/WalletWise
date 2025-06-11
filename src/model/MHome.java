package model;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import utils.Crypto;
import utils.DBConnection;
import utils.StatusType;

public class MHome {

    public List<Object[]> getTransactions() throws Exception {
        List<Object[]> list = new ArrayList<>();
        String symbol = "";
        boolean symbolFirst = false, separator = false;

        String settingQuery = "SELECT * FROM Settings LIMIT 1";
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
                + "ORDER BY t.date DESC LIMIT 5";

        Connection con = DBConnection.getConnection();

        PreparedStatement settingps = con.prepareStatement(settingQuery);
        ResultSet settingrs = settingps.executeQuery();
        if (settingrs.next()) {
            symbol = settingrs.getString("Symbol");
            symbolFirst = settingrs.getBoolean("SymbolFirst");
            separator = settingrs.getBoolean("Seperator");
        }

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Object[]>> futures = new ArrayList<>();

        final String finalSymbol = symbol;
        final boolean finalSymbolFirst = symbolFirst;
        final boolean finalSeparator = separator;

        while (rs.next()) {
            final int id = rs.getInt("id");
            final Date date = rs.getDate("date");
            final String amount = rs.getString("amount");
            final String type = rs.getString("type");
            final String fromName = rs.getString("fromName");
            final String toName = rs.getString("toName");

            Callable<Object[]> task = () -> {
                StatusType statusType;
                if ("Expense".equals(type)) {
                    statusType = StatusType.EXPENSE;
                } else if ("Income".equals(type)) {
                    statusType = StatusType.INCOME;
                } else {
                    statusType = StatusType.TRANSFER;
                }

                String decryptedAmount = Crypto.decrypt(amount);
                String formattedAmount = decryptedAmount;

                if (finalSeparator) {
                    double value = Double.parseDouble(decryptedAmount);
                    formattedAmount = String.format("%,d", (long) value);
                }

                String displayAmount;
                if (finalSymbolFirst) {
                    displayAmount = finalSymbol + (finalSeparator ? " " : "") + formattedAmount;
                } else {
                    displayAmount = formattedAmount + (finalSeparator ? " " : "") + finalSymbol;
                }

                return new Object[]{date, fromName, toName, displayAmount, statusType};
            };

            futures.add(executor.submit(task));
        }

        for (Future<Object[]> future : futures) {
            list.add(future.get());
        }

        executor.shutdown();
        return list;
    }

    public String getStartScreen() throws Exception {
        String query = "SELECT StartScreen from settings";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("StartScreen");
    }

    public List<Object[]> getHomeValues(String timePeriod) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Map<String, Integer> typeAmountMap = new HashMap<>();
        
        String symbol = "";
        boolean symbolFirst = false, separator = false;

        String filterCondition;
        if ("This year".equals(timePeriod)) {
            filterCondition = "YEAR(date) = YEAR(CURDATE())";
        } else if ("This month".equals(timePeriod)) {
            filterCondition = "YEAR(date) = YEAR(CURDATE()) AND MONTH(date) = MONTH(CURDATE())";
        } else {
            filterCondition = "YEAR(date) = YEAR(CURDATE()) AND WEEK(date, 1) = WEEK(CURDATE(), 1)";
        }

        String settingQuery = "SELECT * FROM Settings LIMIT 1";
        String query = "select type, amount from transactions where " + filterCondition;

        Connection con = DBConnection.getConnection();
        PreparedStatement settingps = con.prepareStatement(settingQuery);
        ResultSet settingrs = settingps.executeQuery();
        if (settingrs.next()) {
            symbol = settingrs.getString("Symbol");
            symbolFirst = settingrs.getBoolean("SymbolFirst");
            separator = settingrs.getBoolean("Seperator");
        }
        
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String type = rs.getString(1);
            String encAmount = rs.getString(2);
            String decAmount = Crypto.decrypt(encAmount);
            int amount = Integer.parseInt(decAmount);

            typeAmountMap.put(type, typeAmountMap.getOrDefault(type, 0) + amount);
        }

        for (Map.Entry<String, Integer> entry : typeAmountMap.entrySet()) {
            String type = entry.getKey();
            int totalAmount = entry.getValue();

            String formattedAmount = String.valueOf(totalAmount);
            if (separator) {
                formattedAmount = String.format("%,d", totalAmount);
            }

            String displayAmount;
            if (symbolFirst) {
                displayAmount = symbol + (separator ? " " : "") + formattedAmount;
            } else {
                displayAmount = formattedAmount + (separator ? " " : "") + symbol;
            }

            list.add(new Object[]{type, displayAmount});
        }
        
        return list;
    }
}
