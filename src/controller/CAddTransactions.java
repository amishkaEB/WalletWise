package controller;

import java.util.*;
import model.MAddTransaction;

public class CAddTransactions {

    private final MAddTransaction model;

    public CAddTransactions(MAddTransaction model) {
        this.model = model;
    }

    public Map<String, List<Map<String, Object>>> getAllCategories() throws Exception {
        return model.fetchAllTables();
    }

    public void insertTransaction(String type, int from, int to, String amount, String date) throws Exception {
        model.insertTransaction(type, from, to, amount, date);
    }

    public void updateTransaction(int id, String type, int from, int to, String amount, String date) throws Exception {
        model.updateTransaction(id, type, from, to, amount, date);
    }
}
