/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Card;
import model.Operator;

/**
 *
 * @author ZnzDarck
 */
public class DaoOperador {
    private Connection conn;
    
    public DaoOperador(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getOperador(Long u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM OPERATOR WHERE idUSER = ? ");
            ps.setLong(1, u);
            
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return res;   
    }
    public ResultSet getAll(){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM OPERATOR WHERE STATUS = 'A' ");
          
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return res;   
    }
    
    public ResultSet getByNome(String u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM OPERATOR WHERE STATUS = 'A' AND NOME = ? ");
            ps.setString(1, u);
            
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return res;   
    }
    
    public void update(Long u){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT CARD_idCARD FROM OPERATOR WHERE IDUSER = ? AND STATUS = 'A' ");
            ps.setLong(1, u);
            ResultSet res = ps.executeQuery();
            
            Long id = null;
            while(res.next()){
                id = res.getLong("CARD_idCARD");
            }
            
            ps = conn.prepareStatement("UPDATE OPERATOR SET STATUS = 'I' WHERE IDUSER = ? ");
            ps.setLong(1, u);
            ps.execute();

            ps = conn.prepareStatement("UPDATE CARD SET STATUS = 'I' WHERE IDCARD = ?");
            ps.setLong(1, id);
            ps.execute();
            
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void insert(Operator u, Card c){
        PreparedStatement ps = null;
        PreparedStatement pc = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("INSERT INTO CARD (hash, status, ACCESS_idACCESS) VALUES (?,'A',?)");
            ps.setString(1, c.getHash());
            ps.setLong(2, c.getAcesso());
           
            ps.execute();
            
            ps.clearParameters();
            pc = conn.prepareStatement("SELECT IDCARD FROM CARD WHERE HASH = ? AND STATUS = 'A' ");
            pc.setString(1, c.getHash());
            
            res = pc.executeQuery();
            
            Long id = null;
            while(res.next()){
                id = res.getLong("IDCARD");
            }
            
            ps = conn.prepareStatement("INSERT INTO OPERATOR (cpf, nome, status, email, telefone, CARD_idCARD) VALUES (?,?,'A',?,?,?)");
            ps.setString(1, u.getCpf());
           
            ps.setString(2, u.getNome());
            ps.setString(3, u.getEmail());

            if(u.getTelefone() != null){
                ps.setLong(4, u.getTelefone());
            }else{
                ps.setString(4, null);
            }
            ps.setLong(5, id);
            
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
}
