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

    public boolean deleteCategory(int id) {
        return model.deleteCategory(id);
    }
    
    public boolean insertCategory(String catName, String categoryColor) {
        return model.insertCategory(catName, categoryColor);
    }
    
    public boolean updateCategory(String catName, String categoryColor, int editIndex) {
        return model.updateCategory(catName, categoryColor, editIndex);
    }
}
