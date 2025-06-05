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
        if (type != null){
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
            GradientPaint gn;

            if (type == StatusType.INCOME) {
                gn = new GradientPaint(0, 0, new Color(0, 200, 100), 0, getHeight(), new Color(0, 120, 60));
            } else if (type == StatusType.EXPENSE) {
                gn = new GradientPaint(0, 0, new Color(200, 50, 50), 0, getHeight(), new Color(120, 20, 20));
            } else {
                gn = new GradientPaint(0, 0, new Color(70, 130, 255), 0, getHeight(), new Color(40, 90, 200));
            }

            g2.setPaint(gn);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.dispose();
        }
        super.paintComponent(g);
    }

}
