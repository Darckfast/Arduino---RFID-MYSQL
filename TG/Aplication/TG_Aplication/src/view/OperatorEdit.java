/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Conexao;
import control.DaoAcesso;
import control.DaoCartao;
import control.DaoOperador;
import function.CPF;
import function.Email;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
/**
 *
 * @author ZnzDarck
 */
public class OperatorEdit extends javax.swing.JFrame {

    /**
     * Creates new form OperatorEdit
     */
    public OperatorEdit() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Operadores");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        txtOperatorCard = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxAccess = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnCriar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        btnReload = new javax.swing.JButton();
        txtCpf = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtOperatorCard.setEnabled(false);
        txtOperatorCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOperatorCardActionPerformed(evt);
            }
        });

        jLabel1.setText("Card *");

        jLabel2.setText("Nome *");

        jLabel3.setText("CPF");

        jLabel4.setText("E-mail");

        jLabel5.setText("Telefone");

        jLabel6.setText("* São campos obrigatórios");

        jLabel7.setText("Acesso");

        btnCriar.setText("Criar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel8.setText("ID");

        txtid.setEnabled(false);

        try {
            btnReload.setPreferredSize(new Dimension(40, 40));
            Image img = ImageIO.read(getClass().getResource("../icons/Reload.png"));
            btnReload.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        btnReload.setText(null);
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(cbxAccess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCriar)
                                    .addComponent(btnSalvar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOperatorCard, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnReload))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCriar, btnSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOperatorCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload))
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxAccess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnSalvar))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOperatorCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOperatorCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOperatorCardActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        String u = validaCampos();
        if(u.isEmpty()){
            daoOperador.insert(instanciarObjeto(), instanciarObjectoCard());
            Operator op = new Operator();
            super.dispose();
            op.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(this,
                    u,
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnCriarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conexao = new Conexao("admin","1234");
        conexao.setDriver("com.mysql.cj.jdbc.Driver");
        //conexao.conectar();
        //setAgr(false);
        daoOperador = new DaoOperador(conexao.conectar());
        daoCartao = new DaoCartao(conexao.conectar());
        daoAcesso = new DaoAcesso(conexao.conectar());
        try{
            ResultSet res = daoAcesso.getAll();        
            while(res.next()){
                cbxAccess.addItem(res.getString("nivel"));
            }   
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        if(btnCriar.isEnabled()){
            txtCpf.setEnabled(false);
            txtEmail.setEnabled(false);
            txtNome.setEnabled(false);
            txtTelefone.setEnabled(false);
            //btnCriar.setEnabled(false);
            cbxAccess.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        txtOperatorCard.setText("");
        txtCpf.setEnabled(false);
        txtEmail.setEnabled(false);
        txtNome.setEnabled(false);
        txtTelefone.setEnabled(false);
        //btnCriar.setEnabled(false);
        cbxAccess.setEnabled(false);
        SerialPort serialPort;
        
        String[] portNames = SerialPortList.getPortNames();
        
        serialPort = new SerialPort(portNames[0]);
        
        String str;
        int i = 0;
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_115200, 
                          SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);            
            do{
                str = serialPort.readString(31);
                i++;
            }while(str.trim().length() != 31 || i >= 1000);
                if(str.trim().contains("\n")){
                    JOptionPane.showMessageDialog(this,
                        "Leitura não foi feita com sucesso",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);    
                }else{
                    txtOperatorCard.setText(str.trim());
                    txtCpf.setEnabled(!txtCpf.isEnabled());
                    txtNome.setEnabled(!txtNome.isEnabled());
                    txtTelefone.setEnabled(!txtTelefone.isEnabled());
                    txtEmail.setEnabled(!txtEmail.isEnabled());
                    cbxAccess.setEnabled(!cbxAccess.isEnabled());
                    serialPort.closePort();
                }
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String al = validaCampos();
        
        if(!al.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    al,
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            model.Operator u =  instanciarObjeto();
            daoOperador.update(u.getId());
            daoOperador.insert(u, instanciarObjectoCard());
            Operator op = new Operator();
            super.dispose();
            op.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

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
            java.util.logging.Logger.getLogger(OperatorEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OperatorEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OperatorEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OperatorEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new OperatorEdit().setVisible(true);
        });
                
    }
    private model.Operator instanciarObjeto (){
        model.Operator u = new model.Operator(txtNome.getText().trim(), txtOperatorCard.getText());
        if(!txtid.getText().isEmpty()){
            u.setId(Long.parseLong(txtid.getText()));
        }
        if(!(txtCpf.getText().trim()).isEmpty()){
            u.setCpf(txtCpf.getText().replace("-","").replace(".",""));
        }
        if(!(txtEmail.getText().trim()).isEmpty()){
            u.setEmail(txtEmail.getText());
        }
        if(!(txtTelefone.getText().trim()).isEmpty()){
            u.setTelefone(Long.parseLong(txtTelefone.getText()));
        }
        return u;        
    }
    private model.Card instanciarObjectoCard (){
        model.Card c = new model.Card(txtOperatorCard.getText().trim(),  Long.parseLong(cbxAccess.getModel().getSelectedItem().toString()));
        return c;
    }
    private String validaCampos (){
        String u = "";

        if(!txtid.getText().isEmpty()){
            if(daoCartao.getUnique(Long.parseLong(txtid.getText()), txtOperatorCard.getText())){
                u += "Cartão já está cadastrado com outro operador\n";   
            }
        }else{
            try{
                if(daoCartao.getByCartao(txtOperatorCard.getText()).next()){
                    u += "Cartão já está cadastrado com outro operador\n";
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        if((txtNome.getText().trim()).isEmpty()){
            u += "Nome é necessário\n";
        }
        if(!(txtEmail.getText().trim()).isEmpty()){
            Email email = new Email();
            if(!(email.validateEmail(txtEmail.getText().trim()))){
               u += "Email inválido\n"; 
            }
        }
        String cpf = txtCpf.getText().trim().replace("-","").replace(".","");
        if (!cpf.trim().isEmpty() && !CPF.isCPF(cpf)){    
            u += "CPF inválido\n";
        }
        if (!(txtTelefone.getText().trim().replace("(", "").replace("-", "").replace(")", "").replace(" ", "")).isEmpty()){
            try{
                Long.parseLong(txtTelefone.getText().replace("(", "").replace("-", "").replace(")", "").trim());
            }catch(NumberFormatException e){
                u += "Apenas números\n";
            }
        }
        return u;
    }
    public void setAgr(Boolean i){
        if (i){
            btnCriar.setEnabled(true);
            btnSalvar.setEnabled(false);
        }else{
            btnCriar.setEnabled(false);
            btnSalvar.setEnabled(true);
        }
    }
    public void setDados (model.Operator u, model.Card c){
        txtCpf.setText(u.getCpf());
        txtEmail.setText(u.getEmail());
        txtNome.setText(u.getNome());
        txtOperatorCard.setText(u.getCard());
        
        //cbxAccess.setSelectedItem(new c.getAcesso().intValue());
        cbxAccess.getModel().setSelectedItem(c.getAcesso());
        //btnCriar.setEnabled(!btnCriar.isEnabled());
        if(u.getTelefone() != null){
            txtTelefone.setText(u.getTelefone().toString());
        }
        txtid.setText(u.getId().toString());
    }
    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public void setArduino (String a){
        SerialPort serialPort = new SerialPort(a);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxAccess;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtOperatorCard;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
    private Conexao conexao = null;
    private DaoOperador daoOperador = null;
    private DaoCartao daoCartao = null;
    private DaoAcesso daoAcesso = null;
}
