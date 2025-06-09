package components;

import java.awt.Color;

public class MessageBox extends javax.swing.JDialog {

    public MessageBox(java.awt.Frame parent, String head, String text, String msgOk, Color color) {
        super(parent, true);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));

        msgHead.setText(head);
        msgText.setText(text);
        msgBtn.setText(msgOk);
        
        outer.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outer = new components.PanelBorder();
        panelBorder1 = new components.PanelBorder();
        msgHead = new javax.swing.JLabel();
        msgText = new javax.swing.JLabel();
        btnOk = new components.PanelBorder();
        msgBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        outer.setBackground(new java.awt.Color(255, 51, 0));

        panelBorder1.setBackground(new java.awt.Color(51, 51, 51));

        msgHead.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        msgHead.setForeground(new java.awt.Color(255, 255, 255));
        msgHead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/8.png"))); // NOI18N
        msgHead.setText("jLabel1");

        msgText.setBackground(new java.awt.Color(255, 255, 255));
        msgText.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        msgText.setForeground(new java.awt.Color(255, 255, 255));
        msgText.setText("jLabel1");
        msgText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        msgText.setAutoscrolls(true);
        msgText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        msgText.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOkMouseClicked(evt);
            }
        });

        msgBtn.setBackground(new java.awt.Color(255, 255, 255));
        msgBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgBtn.setText("jLabel1");

        javax.swing.GroupLayout btnOkLayout = new javax.swing.GroupLayout(btnOk);
        btnOk.setLayout(btnOkLayout);
        btnOkLayout.setHorizontalGroup(
            btnOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnOkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(msgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnOkLayout.setVerticalGroup(
            btnOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnOkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(msgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(msgHead, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(msgText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(msgHead, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgText, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout outerLayout = new javax.swing.GroupLayout(outer);
        outer.setLayout(outerLayout);
        outerLayout.setHorizontalGroup(
            outerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        outerLayout.setVerticalGroup(
            outerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(outer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(outer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseClicked
        dispose();
    }//GEN-LAST:event_btnOkMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.PanelBorder btnOk;
    private javax.swing.JLabel msgBtn;
    private javax.swing.JLabel msgHead;
    private javax.swing.JLabel msgText;
    private components.PanelBorder outer;
    private components.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
