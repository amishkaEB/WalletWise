package controller;

import java.util.List;
import model.MIncomeCategory;

public class CIncomeCategory {
    private final MIncomeCategory model;

    public CIncomeCategory(MIncomeCategory model) {
        this.model = model;
    }

    public List<Object[]> getAllCategories() {
        return model.getAllCategories();
    }

    public void deleteCategory(int id) {
        model.deleteCategory(id);
    }
}
