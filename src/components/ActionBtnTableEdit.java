package components;

public class ActionBtnTableEdit extends javax.swing.JPanel {

    public ActionBtnTableEdit() {
        initComponents();
        
        

    }

    public interface ActionListener {

        void onEdit(int row);

    }

    private ActionListener listener;
    private int row;

    public void setActionListener(ActionListener listener, int row) {
        this.listener = listener;
        this.row = row;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEdit = new components.PanelBorder();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        btnEdit.setBackground(new java.awt.Color(153, 153, 0));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Edit");

        javax.swing.GroupLayout btnEditLayout = new javax.swing.GroupLayout(btnEdit);
        btnEdit.setLayout(btnEditLayout);
        btnEditLayout.setHorizontalGroup(
            btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnEditLayout.setVerticalGroup(
            btnEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        if (listener != null) {
            listener.onEdit(row);
        }
    }//GEN-LAST:event_btnEditMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnEdit;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
