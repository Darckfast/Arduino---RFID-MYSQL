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

/**
 *
 * @author ZnzDarck
 */
public class DaoCartao {
    private Connection conn;
    
    public DaoCartao(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getAll(){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(" SELECT C.hash AS HASH, O.nome AS NOME FROM CARD AS C LEFT JOIN OPERATOR AS O ON C.idCARD = O.CARD_idCARD WHERE C.STATUS = 'A' ");

            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    
    public ResultSet getByCartao(String u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CARD WHERE HASH = ? AND STATUS = 'A'");
            ps.setString(1, u);
            
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    public ResultSet getById(Long u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CARD WHERE idCARD = ? ");
            ps.setLong(1, u);
            
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    public void update (Card u){
         PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE CARD SET STATUS = 'I' WHERE idCARD = ? ");
            ps.setLong(1, u.getId());

            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
    }
    
    public Boolean getUnique(Long c, String u){
        PreparedStatement ps;
        ResultSet res;
        try {
            ps = conn.prepareStatement("SELECT IDCARD FROM CARD WHERE HASH = ? AND STATUS = 'A'");
            ps.setString(1, u); 
            
            res = ps.executeQuery();
            if(res.next()){
                ps = conn.prepareStatement("SELECT IDUSER FROM OPERATOR WHERE CARD_IDCARD = ? AND STATUS = 'A' ");
                ps.setLong(1, res.getLong("IDCARD"));
                res = ps.executeQuery();
                if(res.next()){
                    if(res.getLong("IDUSER") == c){
                        return false;
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return false;
    }
}
