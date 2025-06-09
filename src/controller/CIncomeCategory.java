package controller;

import java.util.List;
import model.MIncomeCategory;

public class CIncomeCategory {
    private final MIncomeCategory model;

    public CIncomeCategory(MIncomeCategory model) {
        this.model = model;
    }

    public List<Object[]> getAllCategories() throws Exception {
        return model.getAllCategories();
    }

    public void deleteCategory(int id) throws Exception {
         model.deleteCategory(id);
    }
    
    public void insertCategory(String catName, String categoryColor) throws Exception {
         model.insertCategory(catName, categoryColor);
    }
    
    public void updateCategory(String catName, String categoryColor, int editIndex) throws Exception {
         model.updateCategory(catName, categoryColor, editIndex);
    }
}
