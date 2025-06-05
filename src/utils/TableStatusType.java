package utils;

import java.awt.Color;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;

public class TableStatusType extends JLabel {

    public StatusType getType() {
        return type;
    }

    public TableStatusType() {
        setForeground(Color.WHITE);
    }

    private StatusType type;

    public void setType(StatusType type) {
        this.type = type;
        if (type != null) {
            setText(type.toString());
        } else {
            setText(" ");
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color color;
            if (type == StatusType.INCOME) {
                color = new Color(0, 180, 90);
            } else if (type == StatusType.EXPENSE) {
                color = new Color(200, 50, 50); 
            } else {
                color = new Color(70, 130, 255);
            }

            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.dispose();
        }
        super.paintComponent(g);
    }

}
