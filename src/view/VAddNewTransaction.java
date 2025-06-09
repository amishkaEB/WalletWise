package view;

import com.toedter.calendar.JDateChooser;
import components.MessageBox;
import controller.CAddTransactions;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import model.MAddTransaction;

public class VAddNewTransaction extends javax.swing.JDialog {

    boolean isNew = true;
    int editId;

    List<Map<String, Object>> expenseCategories = new ArrayList<>();
    List<Map<String, Object>> incomeCategories = new ArrayList<>();
    List<Map<String, Object>> accounts = new ArrayList<>();

    public VAddNewTransaction(java.awt.Frame parent, boolean modal) throws Exception {
        this(parent, modal, 0, null, null, null, null, null);
    }

    public VAddNewTransaction(java.awt.Frame parent, boolean modal, int id, String type, Date date, String from, String to, String amount) throws Exception {
        super(parent, modal);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        initComponents();
        styleInitial();

        if (id != 0 && type != null && date != null && from != null && to != null && amount != null) {
            isNew = false;
            editId = id;
            comboFrom.setSelectedItem(from);
            comboType.setSelectedItem(type);
            comboTo.setSelectedItem(to);
            inputDate.setDate(date);
            inputAmount.setText(amount);
            btnLabel.setText("Edit");
        }
    }

    private void styleInitial() throws Exception {
        styleDarkComboBox(comboType);
        styleDarkComboBox(comboFrom);
        styleDarkComboBox(comboTo);

        if (inputDate.getDate() == null) {
            inputDate.setDate(new Date());
        }

        //inputDate = new com.toedter.calendar.JDateChooser();
        //inputDate.setDateFormatString("dd-MM-yyyy");
        JTextField editor = (JTextField) inputDate.getDateEditor().getUiComponent();
        editor.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        SwingUtilities.updateComponentTreeUI(inputDate);

        MAddTransaction model = new MAddTransaction();
        CAddTransactions controller = new CAddTransactions(model);

        Map<String, List<Map<String, Object>>> categories = controller.getAllCategories();

        if (categories != null) {
            expenseCategories = categories.getOrDefault("ExpenseCategories", new ArrayList<>());
            incomeCategories = categories.getOrDefault("IncomeCategories", new ArrayList<>());
            accounts = categories.getOrDefault("Accounts", new ArrayList<>());
        }

        comboFrom.removeAllItems();
        comboTo.removeAllItems();

        inputDate.setDateFormatString("yyyy-MM-dd");

        for (Map<String, Object> row : accounts) {
            comboFrom.addItem(row.get("name").toString());
        }

        for (Map<String, Object> row : expenseCategories) {
            comboTo.addItem(row.get("name").toString());
        }

        PlainDocument doc = (PlainDocument) inputAmount.getDocument();
        doc.setDocumentFilter(new PositiveNumberFilter());
        inputDate.setCalendar(Calendar.getInstance());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new components.PanelBorder();
        panelBorder2 = new components.PanelBorder();
        comboType = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inputDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        comboFrom = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboTo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        panelBorder3 = new components.PanelBorder();
        inputAmount = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnBack = new components.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new components.PanelBorder();
        btnLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(153, 153, 153));

        panelBorder2.setBackground(new java.awt.Color(0, 0, 0));

        comboType.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income", "Transfer" }));
        comboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date :");

        inputDate.setForeground(new java.awt.Color(255, 255, 255));
        inputDate.setMinSelectableDate(new java.util.Date(-62135785732000L));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("From :");

        comboFrom.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income", "Transfer" }));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("To :");

        comboTo.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income", "Transfer" }));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Amount :");

        panelBorder3.setBackground(new java.awt.Color(51, 51, 51));

        inputAmount.setBackground(new java.awt.Color(51, 51, 51));
        inputAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        inputAmount.setForeground(new java.awt.Color(255, 255, 255));
        inputAmount.setBorder(null);

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputAmount)
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputDate, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(comboFrom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboTo))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Back");

        javax.swing.GroupLayout btnBackLayout = new javax.swing.GroupLayout(btnBack);
        btnBack.setLayout(btnBackLayout);
        btnBackLayout.setHorizontalGroup(
            btnBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnBackLayout.setVerticalGroup(
            btnBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAdd.setBackground(new java.awt.Color(153, 255, 153));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnLabel.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLabel.setText("Add");

        javax.swing.GroupLayout btnAddLayout = new javax.swing.GroupLayout(btnAdd);
        btnAdd.setLayout(btnAddLayout);
        btnAddLayout.setHorizontalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAddLayout.setVerticalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked

        dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    public class PositiveNumberFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.insert(offset, string);
            if (isValidInput(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.replace(offset, offset + length, string);
            if (isValidInput(sb.toString())) {
                super.replace(fb, offset, length, string, attr);
            }
        }

        private boolean isValidInput(String text) {
            return text.matches("\\d*\\.?\\d*") && !text.equals(".");
        }
    }

    public Integer getIdByName(String name, List<Map<String, Object>> categories) {
        for (Map<String, Object> row : categories) {
            if (row.get("name").toString().equalsIgnoreCase(name)) {
                return (Integer) row.get("id");
            }
        }
        return null;
    }

    private void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTypeActionPerformed
        comboFrom.removeAllItems();
        comboTo.removeAllItems();

        if (comboType.getSelectedItem() == "Expense") {
            for (Map<String, Object> row : accounts) {
                comboFrom.addItem(row.get("name").toString());
            }

            for (Map<String, Object> row : expenseCategories) {
                comboTo.addItem(row.get("name").toString());
            }
        } else if (comboType.getSelectedItem() == "Income") {
            for (Map<String, Object> row : incomeCategories) {
                comboFrom.addItem(row.get("name").toString());
            }

            for (Map<String, Object> row : accounts) {
                comboTo.addItem(row.get("name").toString());
            }
        } else if (comboType.getSelectedItem() == "Transfer") {
            for (Map<String, Object> row : accounts) {
                comboFrom.addItem(row.get("name").toString());
            }

            for (Map<String, Object> row : accounts) {
                comboTo.addItem(row.get("name").toString());
            }
        }
    }//GEN-LAST:event_comboTypeActionPerformed

    MAddTransaction model = new MAddTransaction();
    CAddTransactions controller = new CAddTransactions(model);

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        String type = comboType.getSelectedItem().toString();
        String from = comboFrom.getSelectedItem().toString();
        String to = comboTo.getSelectedItem().toString();
        String amount = inputAmount.getText();
        String date;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(inputDate.getDate());
        } catch (Exception ex) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Please enter a valid date",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
            return;
        }

        Integer fromId = null, toId = null;

        if ("Expense".equals(type)) {
            fromId = getIdByName(from, accounts);
            toId = getIdByName(to, expenseCategories);
        } else if ("Income".equals(type)) {
            fromId = getIdByName(from, incomeCategories);
            toId = getIdByName(to, accounts);
        } else if ("Transfer".equals(type)) {
            fromId = getIdByName(from, accounts);
            toId = getIdByName(to, accounts);
        }

        if (type.equals("Transfer") && from.equals(to)) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Same account transfer",
                    "<html>Please select different accounts<br>to make transfer.</html>",
                    "Back",
                    Color.YELLOW);

            validateShow.setVisible(true);
            return;
        }

        if (amount != null && !amount.isEmpty()) {
            try {
                if (isNew) {
                    controller.insertTransaction(type, fromId, toId, amount, date);
                    MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                            "Succesfull",
                            "Transaction Recorded.",
                            "Back",
                            Color.GREEN);

                    validateShow.setVisible(true);
                } else {
                    controller.updateTransaction(editId, type, fromId, toId, amount, date);
                    MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                            "Succesfull",
                            "Transaction Updated.",
                            "Back",
                            Color.GREEN);

                    validateShow.setVisible(true);
                }

                dispose();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Error Occured",
                        "Database connection Failed. Please try again.",
                        "Back",
                        Color.RED);

                validateShow.setVisible(true);
            }
        } else {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Please enter a amount",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
        }


    }//GEN-LAST:event_btnAddMouseClicked

    java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);

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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VAddNewTransaction dialog;
                try {
                    dialog = new VAddNewTransaction(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnAdd;
    private components.PanelBorder btnBack;
    private javax.swing.JLabel btnLabel;
    private javax.swing.JComboBox<String> comboFrom;
    private javax.swing.JComboBox<String> comboTo;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JTextField inputAmount;
    private com.toedter.calendar.JDateChooser inputDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private components.PanelBorder panelBorder1;
    private components.PanelBorder panelBorder2;
    private components.PanelBorder panelBorder3;
    // End of variables declaration//GEN-END:variables
}
