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
}
