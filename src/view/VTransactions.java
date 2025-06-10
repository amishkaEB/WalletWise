package view;

import components.MessageBox;
import controller.CTransactions;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import model.MTransactions;
import model.MTransactions.TransactionRowExport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import utils.StatusType;

public class VTransactions extends javax.swing.JPanel {

    MTransactions model = new MTransactions();
    CTransactions controller = new CTransactions(model);

    private void tableLoad() {
        DefaultTableModel tableModel = (DefaultTableModel) transactionsTable1.getModel();
        tableModel.setRowCount(0);

        try {
            for (Object[] row : controller.getTransactions(comboTime.getSelectedItem().toString())) {
                transactionsTable1.addRow(row);
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

        jScrollPane2.setViewportView(transactionsTable1);

        jScrollPane2.setBorder(null);
        jScrollPane2.getViewport().setBackground(new Color(51, 51, 51));

        jScrollPane2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
    }

    java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);

    public VTransactions() {
        initComponents();

        tableLoad();

        transactionsTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = transactionsTable1.rowAtPoint(evt.getPoint());
                int col = transactionsTable1.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 6) {
                    int id = (int) transactionsTable1.getValueAt(row, 0);
                    Date date = (Date) transactionsTable1.getValueAt(row, 1);
                    String from = (String) transactionsTable1.getValueAt(row, 2);
                    String to = (String) transactionsTable1.getValueAt(row, 3);
                    String amount = (String) transactionsTable1.getValueAt(row, 4);
                    StatusType statusType = (StatusType) transactionsTable1.getValueAt(row, 5);
                    String type = "";
                    switch (statusType) {
                        case EXPENSE ->
                            type = "Expense";
                        case INCOME ->
                            type = "Income";
                        case TRANSFER ->
                            type = "Transfer";
                    }
                    Component source = (Component) evt.getSource();
                    Window window = SwingUtilities.getWindowAncestor(source);

                    if (window instanceof Frame frame) {
                        VAddNewTransaction dialog;
                        try {
                            dialog = new VAddNewTransaction(frame, true, id, type, date, from, to, amount);
                            dialog.setLocationRelativeTo(frame);

                            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosed(java.awt.event.WindowEvent e) {
                                    panelBorder2.setVisible(true);
                                    tableLoad();
                                }

                                @Override
                                public void windowClosing(java.awt.event.WindowEvent e) {
                                    panelBorder2.setVisible(true);
                                }
                            });

                            panelBorder2.setVisible(false);
                            dialog.setVisible(true);
                        } catch (Exception ex) {
                            System.err.println(ex.getMessage());
                        }

                    }
                } else if (row >= 0 && col == 7) {
                    evt.consume();

                    int id = (int) transactionsTable1.getValueAt(row, 0);
                    int option = JOptionPane.showConfirmDialog(VTransactions.this,
                            "Are you sure you want to delete this transaction",
                            "Delete Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            controller.deleteByID(id);
                            tableLoad();
                        } catch (Exception e) {
                            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                                    "Error Occured",
                                    "Delete Query Failed. Please try again.",
                                    "Back",
                                    Color.RED);

                            validateShow.setVisible(true);
                        }
                    }
                }
            }
        });

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
        jScrollPane2 = new javax.swing.JScrollPane();
        transactionsTable1 = new utils.TransactionsTable();
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
        comboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTimeActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(51, 51, 51));

        transactionsTable1.setForeground(new java.awt.Color(204, 204, 204));
        transactionsTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "From", "To", "Amount", "Type", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transactionsTable1.setFocusable(false);
        transactionsTable1.setGridColor(new java.awt.Color(51, 51, 51));
        transactionsTable1.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(transactionsTable1);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        btnExport.setBackground(new java.awt.Color(204, 204, 204));
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportMouseClicked(evt);
            }
        });

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
                        .addGap(20, 20, 20)
                        .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addContainerGap(51, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeadingActionPerformed


    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof Frame frame) {
            VAddNewTransaction dialog;
            try {
                dialog = new VAddNewTransaction(frame, true);
                dialog.setLocationRelativeTo(this);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        panelBorder2.setVisible(true);
                        tableLoad();
                    }

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        panelBorder2.setVisible(true);
                    }
                });

                panelBorder2.setVisible(false);
                dialog.setVisible(true);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }

    }//GEN-LAST:event_btnAddMouseClicked

    private void comboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTimeActionPerformed
        tableLoad();
    }//GEN-LAST:event_comboTimeActionPerformed

    public class exportRowTransaction {

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
        private String date;
        private String type;
        private String from;
        private String to;
        private String amount;
    }

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        try {
            List<Object[]> dataList = controller.getTransactionsForExport(comboTime.getSelectedItem().toString());
            List<exportRowTransaction> listItems = new ArrayList<exportRowTransaction>();

            for (Object[] row : dataList) {
                Date dateObj = (Date) row[0];
                String date = new SimpleDateFormat("yyyy-MM-dd").format(dateObj);
                String type = (String) row[1];
                String from = (String) row[2];
                String to = (String) row[3];
                String amount = (String) row[4];

                exportRowTransaction item = new exportRowTransaction();
                item.setDate(date);
                item.setType(type);
                item.setFrom(from);
                item.setTo(to);
                item.setAmount(amount);

                listItems.add(item);
            }

            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("CollectionBeanParam", itemsJRBean);

            InputStream input = new FileInputStream(new File("src/reports/Transactions.jrxml"));
            JasperDesign jasperDesign = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, itemsJRBean);

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            viewer.setVisible(true);

        } catch (Exception ex) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Report generation failed.",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnExportMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnAdd;
    private components.PanelBorder btnExport;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private components.PanelBorder panelBorder2;
    private utils.TransactionsTable transactionsTable1;
    private javax.swing.JTextField txtHeading;
    // End of variables declaration//GEN-END:variables
}
