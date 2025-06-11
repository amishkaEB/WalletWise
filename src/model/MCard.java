package model;

import javax.swing.*;

public class MCard {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }


    public MCard() {
    }

    public MCard(Icon icon, String title, String values) {
        this.icon = icon;
        this.title = title;
        this.values = values;
    }
    
    
    
    private Icon icon;
    private String title;
    private String values;
}
