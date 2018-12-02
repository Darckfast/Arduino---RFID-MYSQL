/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Conexao;
import control.DaoUsuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import model.User;

/**
 *
 * @author ZnzDarck
 */
public class Users extends javax.swing.JFrame {

    /**
     * Creates new form Users
     */
    public Users() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuário");
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
        listUsers = new javax.swing.JList<>();
        btnUserNovo = new javax.swing.JButton();
        btnUserInativar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(listUsers);

        btnUserNovo.setText("Novo");
        btnUserNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserNovoActionPerformed(evt);
            }
        });

        btnUserInativar.setText("Inativar");
        btnUserInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserInativarActionPerformed(evt);
            }
        });

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUserNovo)
                    .addComponent(btnUserInativar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUserInativar, btnUserNovo, jButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUserNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnUserInativar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{ 
            ResultSet res = daoUsuario.getByLogin(listUsers.getSelectedValue());
            while(res.next()){
                User u = new User(res.getString("login"), res.getString("password"));
                u.setId(Long.parseLong(res.getString("id_user")));
                u.setStatus(res.getString("status"));
                UserEdit ue = new UserEdit();
                ue.setDados(u);
                super.dispose();
                ue.setAgr(false);
                ue.setVisible(true);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conexao = new Conexao();
        conexao.setDriver("com.mysql.cj.jdbc.Driver");
        //conexao.conectar();
        daoUsuario = new DaoUsuario(conexao.conectar());
        atualizaLista();
       
    }//GEN-LAST:event_formWindowOpened

    private void btnUserInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserInativarActionPerformed
        try{
            ResultSet res = daoUsuario.getByLogin(listUsers.getSelectedValue());
            while(res.next()){
                User u = new User(res.getString("login"), res.getString("password"));
                u.setId(Long.parseLong(res.getString("id_user")));
                u.setStatus(res.getString("status"));
                daoUsuario.update(u);
                atualizaLista();    
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnUserInativarActionPerformed

    private void btnUserNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserNovoActionPerformed
        UserEdit ue = new UserEdit();
        super.dispose();
        ue.setAgr(true);
        ue.setVisible(true);
    }//GEN-LAST:event_btnUserNovoActionPerformed

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
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }
    private void atualizaLista(){
        try{
            ResultSet res = daoUsuario.getAllUsers();
            DefaultListModel<String> temp = new DefaultListModel<>();
            while(res.next()){
                temp.addElement(res.getString("login"));
            }
            listUsers.setModel(temp);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUserInativar;
    private javax.swing.JButton btnUserNovo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listUsers;
    // End of variables declaration//GEN-END:variables
    private Conexao conexao = null;
    private DaoUsuario daoUsuario = null;
    private User usuario;
}
