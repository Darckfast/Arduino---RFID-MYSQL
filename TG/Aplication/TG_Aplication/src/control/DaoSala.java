/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import model.Room;
/**
 *
 * @author ZnzDarck
 */
public class DaoSala {
    private Connection conn;
    
    public DaoSala(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getAll() {
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM ROOM WHERE STATUS = 'A' ");

            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    
    public void insert(Room u) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO ROOM (NOME_SALA,  ACCESS_idACCESS, STATUS) VALUES (?,?,'A') ");
            
            ps.setString(1, u.getSala());
            ps.setLong(2, u.getAcesso());
            
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
    }
    public void update(Room u) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE ROOM SET STATUS = 'I' WHERE IDROOM  =  ? ");

            ps.setLong(1, u.getId());
          
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
    }
    
    public ResultSet getAccess(){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM ACCESS WHERE STATUS = 'A' ");
          
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return res;
    }
    
    public ResultSet getBySala(String u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM ROOM WHERE STATUS = 'A' AND NOME_SALA = ? ");
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
            ps = conn.prepareStatement("SELECT * FROM ROOM WHERE IDROOM = ? ");
            ps.setLong(1, u);
            
            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return res;
    }
}
