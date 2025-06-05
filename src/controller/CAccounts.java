package controller;

import java.util.List;
import model.MAccounts;

public class CAccounts {
    private final MAccounts model;

    public CAccounts(MAccounts model) {
        this.model = model;
    }

    public List<Object[]> getAllCategories() {
        return model.getAllCategories();
    }

    public void deleteCategory(int id) {
        model.deleteCategory(id);
    }
}
