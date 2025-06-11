package view;

import components.MessageBox;
import controller.CSettings;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import model.MSettings;

public class VSettings extends javax.swing.JPanel {

    MSettings model = new MSettings();
    CSettings controller = new CSettings(model);
    private static String actualTextCurrent = "", actualTextNew = "";

    public VSettings() {
        initComponents();

        comboStart.setForeground(Color.WHITE);
        comboStart.setBackground(new Color(0, 0, 0));
        comboStart.setFocusable(false);
        comboStart.setOpaque(true);
        
        ((AbstractDocument) currentPwd.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                actualTextCurrent = new StringBuilder(actualTextCurrent).insert(offset, string).toString();
                super.insertString(fb, offset, "*".repeat(string.length()), attr);
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
                actualTextCurrent = new StringBuilder(actualTextCurrent).replace(offset, offset + length, string).toString();
                super.replace(fb, offset, length, "*".repeat(string.length()), attr);
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                actualTextCurrent = new StringBuilder(actualTextCurrent).delete(offset, offset + length).toString();
                super.remove(fb, offset, length);
            }
        });
        
        ((AbstractDocument) newPwd.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                actualTextNew = new StringBuilder(actualTextNew).insert(offset, string).toString();
                super.insertString(fb, offset, "*".repeat(string.length()), attr);
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
                actualTextNew = new StringBuilder(actualTextNew).replace(offset, offset + length, string).toString();
                super.replace(fb, offset, length, "*".repeat(string.length()), attr);
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                actualTextNew = new StringBuilder(actualTextNew).delete(offset, offset + length).toString();
                super.remove(fb, offset, length);
            }
        });

        comboStart.setRenderer(new DefaultListCellRenderer() {
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
        if (comboStart.isEditable()) {
            Component editor = comboStart.getEditor().getEditorComponent();
            if (editor instanceof JTextField tf) {
                tf.setBackground(new Color(20, 20, 20));
                tf.setForeground(Color.WHITE);
                tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }
        }

        Object[] settings;
        try {
            settings = (Object[]) controller.getSettings();
            String symbol = (String) settings[0];
            boolean symbolFirst = (boolean) settings[1];
            boolean separator = (boolean) settings[2];
            String startScreen = (String) settings[3];

            txtCurrencyName.setText(symbol);
            checkSymbolFirst.setSelected(symbolFirst);
            checkThousandSeperator.setSelected(separator);
            comboStart.setSelectedItem(startScreen);
        } catch (Exception ex) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "Database Fatch Failed. Please try again.",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHeading = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        namelbl = new javax.swing.JTextField();
        panelBorder1 = new components.PanelBorder();
        txtCurrencyName = new javax.swing.JTextField();
        namelbl1 = new javax.swing.JTextField();
        checkSymbolFirst = new javax.swing.JCheckBox();
        namelbl2 = new javax.swing.JTextField();
        checkThousandSeperator = new javax.swing.JCheckBox();
        btnSave = new components.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        namelbl3 = new javax.swing.JTextField();
        comboStart = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        namelbl4 = new javax.swing.JTextField();
        panelBorder2 = new components.PanelBorder();
        currentPwd = new javax.swing.JTextField();
        panelBorder3 = new components.PanelBorder();
        newPwd = new javax.swing.JTextField();
        namelbl5 = new javax.swing.JTextField();
        btnUpdatePwd = new components.PanelBorder();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        txtHeading.setEditable(false);
        txtHeading.setBackground(new java.awt.Color(0, 0, 0));
        txtHeading.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtHeading.setForeground(new java.awt.Color(204, 204, 204));
        txtHeading.setText("Additional Settings");
        txtHeading.setBorder(null);
        txtHeading.setOpaque(true);
        txtHeading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHeadingActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        namelbl.setEditable(false);
        namelbl.setBackground(new java.awt.Color(0, 0, 0));
        namelbl.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl.setForeground(new java.awt.Color(204, 204, 204));
        namelbl.setText("Currency Symbol :");
        namelbl.setBorder(null);
        namelbl.setOpaque(true);
        namelbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelblActionPerformed(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(51, 51, 51));

        txtCurrencyName.setBackground(new java.awt.Color(51, 51, 51));
        txtCurrencyName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtCurrencyName.setForeground(new java.awt.Color(255, 255, 255));
        txtCurrencyName.setBorder(null);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCurrencyName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtCurrencyName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(namelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namelbl)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        namelbl1.setEditable(false);
        namelbl1.setBackground(new java.awt.Color(0, 0, 0));
        namelbl1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl1.setForeground(new java.awt.Color(204, 204, 204));
        namelbl1.setText("Currency Symbol first");
        namelbl1.setBorder(null);
        namelbl1.setOpaque(true);
        namelbl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelbl1ActionPerformed(evt);
            }
        });

        checkSymbolFirst.setText("jCheckBox1");

        namelbl2.setEditable(false);
        namelbl2.setBackground(new java.awt.Color(0, 0, 0));
        namelbl2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl2.setForeground(new java.awt.Color(204, 204, 204));
        namelbl2.setText("Thousand Seperator");
        namelbl2.setBorder(null);
        namelbl2.setOpaque(true);
        namelbl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelbl2ActionPerformed(evt);
            }
        });

        checkThousandSeperator.setText("jCheckBox1");

        btnSave.setBackground(new java.awt.Color(204, 204, 204));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Save");

        javax.swing.GroupLayout btnSaveLayout = new javax.swing.GroupLayout(btnSave);
        btnSave.setLayout(btnSaveLayout);
        btnSaveLayout.setHorizontalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnSaveLayout.setVerticalGroup(
            btnSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        namelbl3.setEditable(false);
        namelbl3.setBackground(new java.awt.Color(0, 0, 0));
        namelbl3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl3.setForeground(new java.awt.Color(204, 204, 204));
        namelbl3.setText("Start Screen : ");
        namelbl3.setBorder(null);
        namelbl3.setOpaque(true);
        namelbl3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelbl3ActionPerformed(evt);
            }
        });

        comboStart.setBackground(new java.awt.Color(51, 51, 51));
        comboStart.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboStart.setForeground(new java.awt.Color(255, 255, 255));
        comboStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dashboard", "Analytics", "Transactions", "Budget", "Notifications" }));
        comboStart.setBorder(null);
        comboStart.setFocusable(false);
        comboStart.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        namelbl4.setEditable(false);
        namelbl4.setBackground(new java.awt.Color(0, 0, 0));
        namelbl4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl4.setForeground(new java.awt.Color(204, 204, 204));
        namelbl4.setText("Current Password :");
        namelbl4.setBorder(null);
        namelbl4.setOpaque(true);
        namelbl4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelbl4ActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(51, 51, 51));

        currentPwd.setBackground(new java.awt.Color(51, 51, 51));
        currentPwd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        currentPwd.setForeground(new java.awt.Color(255, 255, 255));
        currentPwd.setBorder(null);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(currentPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelBorder3.setBackground(new java.awt.Color(51, 51, 51));

        newPwd.setBackground(new java.awt.Color(51, 51, 51));
        newPwd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        newPwd.setForeground(new java.awt.Color(255, 255, 255));
        newPwd.setBorder(null);

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(newPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        namelbl5.setEditable(false);
        namelbl5.setBackground(new java.awt.Color(0, 0, 0));
        namelbl5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        namelbl5.setForeground(new java.awt.Color(204, 204, 204));
        namelbl5.setText("New Password :");
        namelbl5.setBorder(null);
        namelbl5.setOpaque(true);
        namelbl5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelbl5ActionPerformed(evt);
            }
        });

        btnUpdatePwd.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdatePwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdatePwdMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Update");

        javax.swing.GroupLayout btnUpdatePwdLayout = new javax.swing.GroupLayout(btnUpdatePwd);
        btnUpdatePwd.setLayout(btnUpdatePwdLayout);
        btnUpdatePwdLayout.setHorizontalGroup(
            btnUpdatePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdatePwdLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnUpdatePwdLayout.setVerticalGroup(
            btnUpdatePwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdatePwdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namelbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namelbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdatePwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namelbl4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namelbl5))
                .addGap(27, 27, 27)
                .addComponent(btnUpdatePwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(namelbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboStart, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(checkSymbolFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkThousandSeperator, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namelbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namelbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(444, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namelbl1)
                    .addComponent(checkSymbolFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namelbl2)
                    .addComponent(checkThousandSeperator))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namelbl3)
                    .addComponent(comboStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeadingActionPerformed

    private void namelblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelblActionPerformed

    private void namelbl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelbl1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelbl1ActionPerformed

    private void namelbl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelbl2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelbl2ActionPerformed

    java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        String symbol = txtCurrencyName.getText();
        Boolean symbolFirst = checkSymbolFirst.isSelected();
        Boolean ThousandSep = checkThousandSeperator.isSelected();
        String startScreen = (String) comboStart.getSelectedItem();

        if (symbol == null || symbol.isBlank()) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Input Error",
                    "<html>Please input a Currency Symbol.</html>",
                    "Back",
                    Color.YELLOW);

            validateShow.setVisible(true);
            return;
        }

        try {
            controller.updateSettings(symbol, symbolFirst, ThousandSep, startScreen);
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Settings Saved",
                    "<html>Succesfully Saved</html>",
                    "Back",
                    Color.GREEN);

            validateShow.setVisible(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "<html>Unexpected error occured. Please try again.</html>",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void namelbl3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelbl3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelbl3ActionPerformed

    private void namelbl4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelbl4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelbl4ActionPerformed

    private void namelbl5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelbl5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelbl5ActionPerformed

    private void btnUpdatePwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdatePwdMouseClicked
        
        if (actualTextCurrent == null || actualTextCurrent.isBlank() || actualTextNew == null || actualTextNew.isBlank()) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Input Error",
                    "<html>Please input a Currency Symbol.</html>",
                    "Back",
                    Color.YELLOW);

            validateShow.setVisible(true);
            return;
        }

        try {
            boolean isDone = controller.updatePwd(actualTextCurrent, actualTextNew);

            if (isDone) {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Settings Saved",
                        "<html>Succesfully Saved</html>",
                        "Back",
                        Color.GREEN);

                validateShow.setVisible(true);
                currentPwd.setText("");
                newPwd.setText("");
            } else {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Worng Password",
                        "<html>Wrong current Password. Try again</html>",
                        "Back",
                        Color.YELLOW);

                validateShow.setVisible(true);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Error Occured",
                    "<html>Unexpected error occured. Please try again.</html>",
                    "Back",
                    Color.RED);

            validateShow.setVisible(true);
        }

    }//GEN-LAST:event_btnUpdatePwdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnSave;
    private components.PanelBorder btnUpdatePwd;
    private javax.swing.JCheckBox checkSymbolFirst;
    private javax.swing.JCheckBox checkThousandSeperator;
    private javax.swing.JComboBox<String> comboStart;
    private javax.swing.JTextField currentPwd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField namelbl;
    private javax.swing.JTextField namelbl1;
    private javax.swing.JTextField namelbl2;
    private javax.swing.JTextField namelbl3;
    private javax.swing.JTextField namelbl4;
    private javax.swing.JTextField namelbl5;
    private javax.swing.JTextField newPwd;
    private components.PanelBorder panelBorder1;
    private components.PanelBorder panelBorder2;
    private components.PanelBorder panelBorder3;
    private javax.swing.JTextField txtCurrencyName;
    private javax.swing.JTextField txtHeading;
    // End of variables declaration//GEN-END:variables
}
