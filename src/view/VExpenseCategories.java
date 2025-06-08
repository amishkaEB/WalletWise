package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import model.MExpenseCategory;
import controller.CExpenseCategory;
import javax.swing.table.DefaultTableModel;
import utils.Validations;

public class VExpenseCategories extends javax.swing.JPanel {

    private String categoryColor;
    private boolean isNew = true;
    private int editIndex;
    MExpenseCategory model = new MExpenseCategory();
    CExpenseCategory controller = new CExpenseCategory(model);

    private void tableLoad() {

        DefaultTableModel tableModel = (DefaultTableModel) incomeCategoryTable1.getModel();
        tableModel.setRowCount(0);

        for (Object[] row : controller.getAllCategories()) {
            incomeCategoryTable1.addRow(row);
        }

        jScrollPane1.setViewportView(incomeCategoryTable1);

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

    public VExpenseCategories() {
        initComponents();

        tableLoad();

        incomeCategoryTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = incomeCategoryTable1.rowAtPoint(evt.getPoint());
                int col = incomeCategoryTable1.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 3) {
                    Object id = incomeCategoryTable1.getValueAt(row, 0);
                    categoryColor = (String) incomeCategoryTable1.getValueAt(row, 2);
                    Color color = Color.decode(categoryColor);
                    pickedColor.setBackground(color);
                    txtHeading.setText("Edit Category : " + id);
                    jLabel2.setText("Edit");
                    isNew = false;
                    editIndex = (int) id;
                    txtName.setText((String) incomeCategoryTable1.getValueAt(row, 1));
                } else if (row >= 0 && col == 4) {
                    evt.consume();

                    int id = (int) incomeCategoryTable1.getValueAt(row, 0);
                    int option = JOptionPane.showConfirmDialog(VExpenseCategories.this,
                            "Are you sure you want to delete " + (String) incomeCategoryTable1.getValueAt(row, 1) + " category?",
                            "Delete Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (option == JOptionPane.YES_OPTION) {
                        boolean deleted = controller.deleteCategory(id);
                        if (deleted) {
                            categoryColor = null;
                            pickedColor.setBackground(Color.BLACK);
                            txtName.setText("");
                            isNew = true;
                            editIndex = -1;
                            txtHeading.setText("Add New Category");
                            jLabel2.setText("Add New");
                            tableLoad();
                        } else {
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

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHeading = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        namelbl = new javax.swing.JTextField();
        panelBorder1 = new components.PanelBorder();
        txtName = new javax.swing.JTextField();
        btnColorPicker = new components.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new components.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        btnClear = new components.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        pickedColor = new components.PanelBorder();
        panelBorder2 = new components.PanelBorder();
        txtHeading1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        incomeCategoryTable1 = new utils.IncomeCategoryTable();

        setBackground(new java.awt.Color(0, 0, 0));

        txtHeading.setEditable(false);
        txtHeading.setBackground(new java.awt.Color(0, 0, 0));
        txtHeading.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtHeading.setForeground(new java.awt.Color(204, 204, 204));
        txtHeading.setText("Add New Category");
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
        namelbl.setText("Category Name :");
        namelbl.setBorder(null);
        namelbl.setOpaque(true);
        namelbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namelblActionPerformed(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(51, 51, 51));

        txtName.setBackground(new java.awt.Color(51, 51, 51));
        txtName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setBorder(null);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(namelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namelbl)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnColorPicker.setBackground(new java.awt.Color(204, 204, 204));
        btnColorPicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnColorPickerMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pick a Color");

        javax.swing.GroupLayout btnColorPickerLayout = new javax.swing.GroupLayout(btnColorPicker);
        btnColorPicker.setLayout(btnColorPickerLayout);
        btnColorPickerLayout.setHorizontalGroup(
            btnColorPickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnColorPickerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnColorPickerLayout.setVerticalGroup(
            btnColorPickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnColorPickerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
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
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnAddLayout.setVerticalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnClear.setBackground(new java.awt.Color(51, 51, 51));
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Clear");

        javax.swing.GroupLayout btnClearLayout = new javax.swing.GroupLayout(btnClear);
        btnClear.setLayout(btnClearLayout);
        btnClearLayout.setHorizontalGroup(
            btnClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnClearLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        btnClearLayout.setVerticalGroup(
            btnClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnClearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pickedColor.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pickedColorLayout = new javax.swing.GroupLayout(pickedColor);
        pickedColor.setLayout(pickedColorLayout);
        pickedColorLayout.setHorizontalGroup(
            pickedColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        pickedColorLayout.setVerticalGroup(
            pickedColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelBorder2.setBackground(new java.awt.Color(51, 51, 51));

        txtHeading1.setEditable(false);
        txtHeading1.setBackground(new java.awt.Color(51, 51, 51));
        txtHeading1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtHeading1.setForeground(new java.awt.Color(204, 204, 204));
        txtHeading1.setText("Expense Category List");
        txtHeading1.setBorder(null);
        txtHeading1.setOpaque(true);
        txtHeading1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHeading1ActionPerformed(evt);
            }
        });

        incomeCategoryTable1.setForeground(new java.awt.Color(204, 204, 204));
        incomeCategoryTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Color", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        incomeCategoryTable1.setFocusable(false);
        incomeCategoryTable1.setGridColor(new java.awt.Color(51, 51, 51));
        incomeCategoryTable1.setSelectionBackground(new java.awt.Color(51, 51, 51));
        incomeCategoryTable1.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(incomeCategoryTable1);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHeading1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHeading1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(pickedColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnColorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pickedColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnColorPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeadingActionPerformed

    private void namelblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namelblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namelblActionPerformed


    private void btnColorPickerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnColorPickerMouseClicked
        
        
        Color selectedColor = JColorChooser.showDialog(null, "Pick a Color", Color.RED);
        if (selectedColor != null) {
            String hex = String.format("#%02x%02x%02x",
                    selectedColor.getRed(),
                    selectedColor.getGreen(),
                    selectedColor.getBlue());
            categoryColor = hex;
            Color color = Color.decode(categoryColor);
            pickedColor.setBackground(color);
        }
    }//GEN-LAST:event_btnColorPickerMouseClicked


    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        categoryColor = null;
        pickedColor.setBackground(Color.BLACK);
        txtName.setText("");
        isNew = true;
        editIndex = -1;
        txtHeading.setText("Add New Category");
        jLabel2.setText("Add New");
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        String catName = txtName.getText();
        if (catName == null || categoryColor == null || catName.equals("")) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Enter Required Fields",
                    "<html>Please make sure both Category Name<br> and Color are entered to proceed with<br>category creation</html>",
                    "Back",
                    Color.YELLOW);

            validateShow.setVisible(true);

        } else if (!Validations.isLettersOnly(catName)) {
            MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                    "Category Name field Validation",
                    "<html>Please enter a valid name for category<br>(Can't have special characters)</html>",
                    "Back",
                    Color.YELLOW);

            validateShow.setVisible(true);
        } else if (isNew == true) {
            boolean inserted = controller.insertCategory(catName, categoryColor);
            if (inserted) {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Succesfully Created",
                        "Category Created.",
                        "Back",
                        Color.GREEN);

                validateShow.setVisible(true);
                categoryColor = null;
                pickedColor.setBackground(Color.BLACK);
                txtName.setText("");
                tableLoad();
                this.revalidate();
                this.repaint();
            } else {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Error Occured",
                        "Insert Query Failed. Please try again.",
                        "Back",
                        Color.RED);

                validateShow.setVisible(true);
            }
        } else {
            boolean updated = controller.updateCategory(catName, categoryColor, editIndex);
            if (updated) {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Succesfully Updated",
                        "Updated Succesfully.",
                        "Back",
                        Color.GREEN);

                validateShow.setVisible(true);
                categoryColor = null;
                pickedColor.setBackground(Color.BLACK);
                txtName.setText("");
                isNew = true;
                editIndex = -1;
                txtHeading.setText("Add New Category");
                jLabel2.setText("Add New");
                tableLoad();
                this.revalidate();
                this.repaint();
            } else {
                MessageBox validateShow = new MessageBox((java.awt.Frame) parentWindow,
                        "Error Occured",
                        "Insert Query Failed. Please try again.",
                        "Back",
                        Color.RED);

                validateShow.setVisible(true);
            }
        }


    }//GEN-LAST:event_btnAddMouseClicked

    private void txtHeading1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeading1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeading1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnAdd;
    private components.PanelBorder btnClear;
    private components.PanelBorder btnColorPicker;
    private utils.IncomeCategoryTable incomeCategoryTable1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namelbl;
    private components.PanelBorder panelBorder1;
    private components.PanelBorder panelBorder2;
    private components.PanelBorder pickedColor;
    private javax.swing.JTextField txtHeading;
    private javax.swing.JTextField txtHeading1;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
