package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;
import utils.StatusType;

public class VTransactions extends javax.swing.JPanel {

    public VTransactions() {
        initComponents();

        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(51, 51, 51));

        jScrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            private Color thumbColor = new Color(30, 30, 30); // black thumb
            private Color trackColor = new Color(60, 60, 60); // dark gray track

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

        latestTransactionsTable.addRow(new Object[]{"05 May", "Account 01", "Salary", "$2000", StatusType.INCOME});
        latestTransactionsTable.addRow(new Object[]{"06 May", "Account 02", "Grocery", "$120", StatusType.EXPENSE});
        latestTransactionsTable.addRow(new Object[]{"07 May", "Account 01", "Bonus", "$500", StatusType.INCOME});
        latestTransactionsTable.addRow(new Object[]{"08 May", "Account 03", "Electricity Bill", "$80", StatusType.EXPENSE});
        latestTransactionsTable.addRow(new Object[]{"09 May", "Account 02", "Interest", "$150", StatusType.TRANSFER});

        comboTime.setForeground(Color.WHITE);
        comboTime.setBackground(new Color(0, 0, 0));
        comboTime.setFocusable(false);
        comboTime.setOpaque(true);

        comboTime.setRenderer(new DefaultListCellRenderer() {
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
        if (comboTime.isEditable()) {
            Component editor = comboTime.getEditor().getEditorComponent();
            if (editor instanceof JTextField tf) {
                tf.setBackground(new Color(20, 20, 20));
                tf.setForeground(Color.WHITE);
                tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHeading = new javax.swing.JTextField();
        comboTime = new javax.swing.JComboBox<>();
        panelBorder2 = new components.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        latestTransactionsTable = new utils.LatestTransactionsTable();
        btnExport = new components.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new components.PanelBorder();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        txtHeading.setEditable(false);
        txtHeading.setBackground(new java.awt.Color(0, 0, 0));
        txtHeading.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtHeading.setForeground(new java.awt.Color(204, 204, 204));
        txtHeading.setText("Transactions");
        txtHeading.setBorder(null);
        txtHeading.setOpaque(true);
        txtHeading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHeadingActionPerformed(evt);
            }
        });

        comboTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "This week", "This month", "This year" }));
        comboTime.setFocusable(false);

        panelBorder2.setBackground(new java.awt.Color(51, 51, 51));

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

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        btnExport.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Export");

        javax.swing.GroupLayout btnExportLayout = new javax.swing.GroupLayout(btnExport);
        btnExport.setLayout(btnExportLayout);
        btnExportLayout.setHorizontalGroup(
            btnExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnExportLayout.setVerticalGroup(
            btnExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        btnAdd.setBackground(new java.awt.Color(153, 255, 153));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add New");

        javax.swing.GroupLayout btnAddLayout = new javax.swing.GroupLayout(btnAdd);
        btnAdd.setLayout(btnAddLayout);
        btnAddLayout.setHorizontalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAddLayout.setVerticalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAddLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeadingActionPerformed


    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof Frame frame) {
            VAddNewTransaction dialog = new VAddNewTransaction(frame, true);
            dialog.setLocationRelativeTo(this);

            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    panelBorder2.setVisible(true);
                }

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    panelBorder2.setVisible(true);
                }
            });

            panelBorder2.setVisible(false);
            dialog.setVisible(true);
        }

    }//GEN-LAST:event_btnAddMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnAdd;
    private components.PanelBorder btnExport;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private utils.LatestTransactionsTable latestTransactionsTable;
    private components.PanelBorder panelBorder2;
    private javax.swing.JTextField txtHeading;
    // End of variables declaration//GEN-END:variables
}
