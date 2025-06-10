package view;

import components.MessageBox;
import controller.CAnalysis;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.MAnalysis;

public class VAnalysis extends javax.swing.JPanel {

    MAnalysis model = new MAnalysis();
    CAnalysis controller = new CAnalysis(model);

    public VAnalysis() {
        initComponents();
        styleDarkComboBox(comboTime);
        styleDarkComboBox(comboType);

        loadData();
    }

    java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);

    void loadData() {
        try {
            List<Object[]> chartData = controller.getData(comboTime.getSelectedItem().toString(), comboType.getSelectedItem().toString());
            pieChart1.updateChart(chartData);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboTime = new javax.swing.JComboBox<>();
        comboType = new javax.swing.JComboBox<>();
        panelBorder1 = new components.PanelBorder();
        pieChart1 = new components.PieChart();

        setBackground(new java.awt.Color(0, 0, 0));

        comboTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "This week", "This month", "This year" }));
        comboTime.setFocusable(false);
        comboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTimeActionPerformed(evt);
            }
        });

        comboType.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income" }));
        comboType.setFocusable(false);
        comboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeActionPerformed(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTimeActionPerformed
        loadData();
    }//GEN-LAST:event_comboTimeActionPerformed

    private void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTypeActionPerformed
        loadData();
    }//GEN-LAST:event_comboTypeActionPerformed

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
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JComboBox<String> comboType;
    private components.PanelBorder panelBorder1;
    private components.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
