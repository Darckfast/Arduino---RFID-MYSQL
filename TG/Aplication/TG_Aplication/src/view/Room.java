/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Conexao;
import control.DaoSala;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 *
 * @author ZnzDarck
 */
public class Room extends javax.swing.JFrame {

    /**
     * Creates new form Room
     */
    public Room() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salas");
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
        listRoom = new javax.swing.JList<>();
        btnRoomNovo = new javax.swing.JButton();
        btnRoomInativar = new javax.swing.JButton();
        btnRoomEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(listRoom);

        btnRoomNovo.setText("Novo");
        btnRoomNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomNovoActionPerformed(evt);
            }
        });

        btnRoomInativar.setText("Inativar");
        btnRoomInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomInativarActionPerformed(evt);
            }
        });

        btnRoomEditar.setText("Editar");
        btnRoomEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRoomNovo)
                    .addComponent(btnRoomInativar)
                    .addComponent(btnRoomEditar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnRoomEditar, btnRoomInativar, btnRoomNovo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRoomNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnRoomInativar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRoomEditar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conexao = new Conexao();
        conexao.setDriver("com.mysql.cj.jdbc.Driver");
        //conexao.conectar();
        daoSala = new DaoSala(conexao.conectar());
        atualizaLista();
    }//GEN-LAST:event_formWindowOpened

    private void btnRoomNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomNovoActionPerformed
        RoomEdit rm = new RoomEdit();
        super.dispose();
        rm.setAgr(true);
        rm.setVisible(true);    }//GEN-LAST:event_btnRoomNovoActionPerformed

    private void btnRoomEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomEditarActionPerformed
         try{ 
            ResultSet res = daoSala.getBySala(listRoom.getSelectedValue());
            while(res.next()){
                model.Room u = new model.Room(res.getString("nome_sala"), res.getLong("ACCESS_idACCESS"));
                u.setId(Long.parseLong(res.getString("idROOM")));
                u.setStatus(res.getString("status"));
                RoomEdit ue = new RoomEdit();
                ue.setDados(u);
                super.dispose();
                ue.setAgr(false);
                ue.setVisible(true);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnRoomEditarActionPerformed

    private void btnRoomInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomInativarActionPerformed
        try{
            ResultSet res = daoSala.getBySala(listRoom.getSelectedValue());
            while(res.next()){
                model.Room u = new model.Room(res.getString("nome_sala"), res.getLong("ACCESS_idACCESS"));
                u.setId(Long.parseLong(res.getString("idROOM")));
                u.setStatus(res.getString("status"));
                daoSala.update(u);
                atualizaLista();    
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnRoomInativarActionPerformed

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
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Room().setVisible(true);
            }
        });
    }
    private void atualizaLista(){
        try{
            ResultSet res = daoSala.getAll();
            DefaultListModel<String> temp = new DefaultListModel<>();
            while(res.next()){
                temp.addElement(res.getString("nome_sala"));
            }
            listRoom.setModel(temp);
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRoomEditar;
    private javax.swing.JButton btnRoomInativar;
    private javax.swing.JButton btnRoomNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listRoom;
    // End of variables declaration//GEN-END:variables
    private Conexao conexao = null;
    private DaoSala daoSala = null;
}

