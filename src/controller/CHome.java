
package controller;

import java.util.List;
import model.MHome;

public class CHome {
    private final MHome model;

    public CHome(MHome model) {
        this.model = model;
    }
    
    public List<Object[]> getTransactions() throws Exception {
        return model.getTransactions();
    }
    
    public String getStartScreen() throws Exception {
        return model.getStartScreen();
    }
    
    public List<Object[]> getHomeValues (String timePeriod) throws Exception{
        return model.getHomeValues(timePeriod);
    }
}
