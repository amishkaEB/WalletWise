package controller;

import java.util.List;
import model.MExpenseCategory;

public class CExpenseCategory {
    private final MExpenseCategory model;

    public CExpenseCategory(MExpenseCategory model) {
        this.model = model;
    }

    public List<Object[]> getAllCategories() {
        return model.getAllCategories();
    }

    public void deleteCategory(int id) {
        model.deleteCategory(id);
    }
}
