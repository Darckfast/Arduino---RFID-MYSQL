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

/**
 *
 * @author ZnzDarck
 */
public class DaoAcesso {
     private Connection conn;
    
    public DaoAcesso(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getAll(){
        PreparedStatement ps = null;
        ResultSet res = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM ACCESS WHERE STATUS = 'A' ");
                    
            res = ps.executeQuery();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return res;
    }
    
    public void insert(model.Access u){
         PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("INSERT INTO ACCESS (NIVEL, STATUS) VALUES (?,'A') ");
            ps.setString(1,u.getNivel());
            
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public void update (model.Access u){
         PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("UPDATE ACCESS SET STATUS = 'I' WHERE IDACCESS  =  ?");
            ps.setLong(1,u.getId());
            
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public ResultSet getByAcesso (String u){
        PreparedStatement ps = null;
        ResultSet res = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM ACCESS WHERE NIVEL = ? ");
            ps.setString(1, u);
            
            res = ps.executeQuery();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return res;
    }
}
