/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Conexao;
import control.DaoAcesso;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 *
 * @author ZnzDarck
 */
public class Access extends javax.swing.JFrame {

    /**
     * Creates new form Access
     */
    public Access() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acesso(s)");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listAccess = new javax.swing.JList<>();
        btnAccessNovo = new javax.swing.JButton();
        btnAccessInativar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(listAccess);

        btnAccessNovo.setText("Novo");
        btnAccessNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccessNovoActionPerformed(evt);
            }
        });

        btnAccessInativar.setText("Inativar");
        btnAccessInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccessInativarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAccessNovo)
                    .addComponent(btnAccessInativar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAccessInativar, btnAccessNovo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAccessNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnAccessInativar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccessNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccessNovoActionPerformed
        AccessEdit ae = new AccessEdit();
        super.dispose();
        ae.setVisible(true);
// TODO add your handling code here:
        
    }//GEN-LAST:event_btnAccessNovoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conexao = new Conexao("admin","1234");
        conexao.setDriver("com.mysql.cj.jdbc.Driver");
        //conexao.conectar();
        daoAcesso = new DaoAcesso(conexao.conectar());  
        atualizaLista();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void btnAccessInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccessInativarActionPerformed
        try{
            ResultSet res = daoAcesso.getByAcesso(listAccess.getSelectedValue());
            while(res.next()){
                model.Access u = new model.Access(res.getString("nivel"));
                u.setId(res.getLong("idACCESS"));
                daoAcesso.update(u);
                atualizaLista();
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAccessInativarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Access.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Access.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Access.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Access.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Access().setVisible(true);
            }
        });
    }
    private void atualizaLista(){
        try{
            ResultSet res = daoAcesso.getAll();
            DefaultListModel<String> temp = new DefaultListModel<>();
            while(res.next()){
                temp.addElement(res.getString("nivel"));
            }
            listAccess.setModel(temp);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccessInativar;
    private javax.swing.JButton btnAccessNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listAccess;
    // End of variables declaration//GEN-END:variables
    private Conexao conexao = null;
    private DaoAcesso daoAcesso = null;
}
