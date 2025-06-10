package controller;

import java.util.List;
import model.MAnalysis;

public class CAnalysis {
    private final MAnalysis model;

    public CAnalysis(MAnalysis model) {
        this.model = model;
    }

    public List<Object[]> getData(String timePeriod, String type) throws Exception {
        return model.getData(timePeriod, type);
    }
}
