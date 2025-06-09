package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class VCategories extends javax.swing.JPanel {

    public VCategories() {
        initComponents();
        setForm(new VExpenseCategories());

        comboCategoryType.setForeground(Color.WHITE);
        comboCategoryType.setBackground(new Color(0, 0, 0));
        comboCategoryType.setFocusable(false);
        comboCategoryType.setOpaque(true);

        comboCategoryType.setRenderer(new DefaultListCellRenderer() {
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
        if (comboCategoryType.isEditable()) {
            Component editor = comboCategoryType.getEditor().getEditorComponent();
            if (editor instanceof JTextField tf) {
                tf.setBackground(new Color(20, 20, 20));
                tf.setForeground(Color.WHITE);
                tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }
        }

    }

    private void setForm(JComponent com) {
        subPanel.removeAll();
        subPanel.setLayout(new BorderLayout());
        subPanel.add(com, BorderLayout.CENTER);
        subPanel.repaint();
        subPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboCategoryType = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 0, 0));

        subPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout subPanelLayout = new javax.swing.GroupLayout(subPanel);
        subPanel.setLayout(subPanelLayout);
        subPanelLayout.setHorizontalGroup(
            subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        subPanelLayout.setVerticalGroup(
            subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/5.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        comboCategoryType.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        comboCategoryType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense Categories", "Income Categories", "Accounts" }));
        comboCategoryType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoryTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCategoryType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboCategoryType)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboCategoryTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoryTypeActionPerformed
        switch (comboCategoryType.getSelectedItem().toString()) {
            case "Expense Categories" ->
                setForm(new VExpenseCategories());
            case "Income Categories" ->
                setForm(new VIncomeCategories());
            case "Accounts" ->
                setForm(new VAccounts());
        }
    }//GEN-LAST:event_comboCategoryTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCategoryType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel subPanel;
    // End of variables declaration//GEN-END:variables
}
