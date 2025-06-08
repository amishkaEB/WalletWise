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
