package view;

import components.MessageBox;
import controller.CHome;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import model.MCard;
import model.MHome;
import utils.StatusType;

public class VHome extends javax.swing.JPanel {

    MHome model = new MHome();
    CHome controller = new CHome(model);
    
    String income,expense,transfer;
    

    public VHome() {
        initComponents();
        tableLoad();
        styleDarkComboBox(comboTime);
        
        handleCardsAndSet();
        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(51, 51, 51));

        jScrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            private Color thumbColor = new Color(30, 30, 30); 
            private Color trackColor = new Color(60, 60, 60); 

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(30, 30, 30);
                this.trackColor = new Color(60, 60, 60);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                g.setColor(thumbColor);
                g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(trackColor);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));

    }

    java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);

    public void handleCardsAndSet() {
        valuesLoad();

        cardIncome.setData(new MCard(new ImageIcon(getClass().getResource("/assets/stock.png")), "Total Income", income));
        cardExpense.setData(new MCard(new ImageIcon(getClass().getResource("/assets/flag.png")), "Total Expence", expense));
        cardSavings.setData(new MCard(new ImageIcon(getClass().getResource("/assets/profit.png")), "Total Transfer", transfer));
    }
    
    private void valuesLoad() {
        try {
            for (Object[] row : controller.getHomeValues(comboTime.getSelectedItem().toString())) {
                String type = (String) row[0];
                String displayAmount = (String) row[1];
                
                if ("Income".equalsIgnoreCase(type)) {
                    income = displayAmount;
                } else if ("Expense".equalsIgnoreCase(type)) {
                    expense = displayAmount;
                } else {
                    transfer = displayAmount;
                }
                
               
            }
        } catch (Exception ex) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Database Fatch Failed. Please try again.",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
            System.err.println(ex.getMessage());
        }
    }

    private void tableLoad() {
        DefaultTableModel tableModel = (DefaultTableModel) latestTransactionsTable.getModel();
        tableModel.setRowCount(0);

        try {
            for (Object[] row : controller.getTransactions()) {
                latestTransactionsTable.addRow(row);
            }

        } catch (Exception ex) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Database Fatch Failed. Please try again.",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
            System.err.println(ex.getMessage());
        }

        jScrollPane1.setViewportView(latestTransactionsTable);

        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(51, 51, 51));

        jScrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            private Color thumbColor = new Color(30, 30, 30);
            private Color trackColor = new Color(60, 60, 60);

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(30, 30, 30);
                this.trackColor = new Color(60, 60, 60);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                g.setColor(thumbColor);
                g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(trackColor);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cardIncome = new components.Card();
        cardExpense = new components.Card();
        cardSavings = new components.Card();
        panelBorder1 = new components.PanelBorder();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        latestTransactionsTable = new utils.LatestTransactionsTable();
        comboTime = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cardIncome.setColor1(new java.awt.Color(0, 200, 100));
        cardIncome.setColor2(new java.awt.Color(0, 120, 60));
        jPanel1.add(cardIncome);

        cardExpense.setColor1(new java.awt.Color(200, 50, 50));
        cardExpense.setColor2(new java.awt.Color(120, 20, 20));
        jPanel1.add(cardExpense);

        cardSavings.setAutoscrolls(true);
        cardSavings.setColor1(new java.awt.Color(70, 130, 255));
        cardSavings.setColor2(new java.awt.Color(40, 90, 200));
        jPanel1.add(cardSavings);

        panelBorder1.setBackground(new java.awt.Color(51, 51, 51));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Latest Transactions");
        jTextField1.setBorder(null);
        jTextField1.setOpaque(true);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));

        latestTransactionsTable.setForeground(new java.awt.Color(204, 204, 204));
        latestTransactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "From", "To", "Amount", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        latestTransactionsTable.setFocusable(false);
        latestTransactionsTable.setGridColor(new java.awt.Color(51, 51, 51));
        latestTransactionsTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(latestTransactionsTable);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        comboTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "This week", "This month", "This year" }));
        comboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTimeActionPerformed
        handleCardsAndSet();
    }//GEN-LAST:event_comboTimeActionPerformed

    private void styleDarkComboBox(JComboBox<?> comboBox) {
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(0, 0, 0));
        comboBox.setFocusable(false);
        comboBox.setOpaque(true);

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                label.setOpaque(true);
                label.setForeground(Color.WHITE);
                label.setBackground(isSelected ? new Color(70, 70, 70) : new Color(20, 20, 20));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                if (list != null) {
                    list.setBorder(BorderFactory.createEmptyBorder());
                    list.setBackground(new Color(20, 20, 20));
                }
                return label;
            }
        });

        if (comboBox.isEditable()) {
            Component editor = comboBox.getEditor().getEditorComponent();
            if (editor instanceof JTextField tf) {
                tf.setBackground(new Color(20, 20, 20));
                tf.setForeground(Color.WHITE);
                tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.Card cardExpense;
    private components.Card cardIncome;
    private components.Card cardSavings;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private utils.LatestTransactionsTable latestTransactionsTable;
    private components.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
