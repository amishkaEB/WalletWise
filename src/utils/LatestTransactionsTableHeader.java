package utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LatestTransactionsTableHeader extends JLabel{

    public LatestTransactionsTableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(new Color(51,51,51));
        setFont(new Font("verdana", 1, 12));
        setForeground(new Color(200,200,200));
        setBorder(new EmptyBorder(10,5,10,5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(100,100,100));
        g.drawLine(0, getHeight() -1, getWidth(), getHeight() -1);
    }
    
    
    
}
