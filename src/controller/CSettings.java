package controller;

import model.MSettings;

public class CSettings {
    private final MSettings model;

    public CSettings(MSettings model) {
        this.model = model;
    }

    public Object[] getSettings() throws Exception{
        return model.getSettings();
        
    }

    public void updateSettings(String symbol, boolean symbolFirst, boolean separator, String startScreen) throws Exception {
        model.updateSettings(symbol, symbolFirst, separator, startScreen);
    }
}
