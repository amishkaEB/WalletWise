package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import model.MMenu;
import controller.EventMenuSelected;

public class Menu extends javax.swing.JPanel {
    
    private EventMenuSelected event;
    
    public void addEventMenuSelected (EventMenuSelected event){
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }
    
    private void init() {
        listMenu1.addItem(new MMenu("1", "Dashboard", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("2", "Income", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("3", "Expense", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("4", "Analytics", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("5", "Transactions", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("", " ", MMenu.MenuType.EMPTY));

        listMenu1.addItem(new MMenu("", "Manage Application", MMenu.MenuType.TITLE));
        listMenu1.addItem(new MMenu("", " ", MMenu.MenuType.EMPTY));
        listMenu1.addItem(new MMenu("5", "Category", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("9", "More", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("8", "Logout", MMenu.MenuType.MENU));
        listMenu1.addItem(new MMenu("", "", MMenu.MenuType.EMPTY));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new utils.ListMenu<>();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Vivaldi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N
        jLabel1.setText("WalletWise");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(47, Short.MAX_VALUE))
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(Color.decode("#242323"));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());

        g2.dispose();

        super.paintComponent(g);
    }
    
    private int x;
    private int y;
    
    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                fram.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
            
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private utils.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
