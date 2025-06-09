
package controller;

import java.util.List;
import model.MTransactions;


public class CTransactions {
    private final MTransactions model;

    public CTransactions(MTransactions model) {
        this.model = model;
    }
    
    public List<Object[]> getTransactions(String timePeriod) throws Exception {
        return model.getTransactions(timePeriod);
    }
    
    public void deleteByID(int id) throws Exception {
         model.deleteByID(id);
    }
}
