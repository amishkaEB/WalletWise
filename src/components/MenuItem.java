package components;

import java.awt.*;
import model.MMenu;

public class MenuItem extends javax.swing.JPanel {

    private boolean selected;
    private boolean over;

    public MenuItem(MMenu data) {
        initComponents();
        setOpaque(false);
        if (data.getType() == MMenu.MenuType.MENU) {
            lbIcon.setIcon(data.toIcon());
            lbName.setText(data.getName());
        } else if (data.getType() == MMenu.MenuType.TITLE) {
            lbIcon.setText(data.getName());
            lbIcon.setFont(new Font("verdana", 1, 12));
            lbName.setVisible(false);
        } else {
            lbName.setText(" ");
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public void setOver(boolean over){
        this.over = over;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));

        lbName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Menu Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if(selected){
                g2.setColor(new Color(255, 255, 255, 50));
            } else {
                g2.setColor(new Color(255, 255, 255, 30));
            }
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
