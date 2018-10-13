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
import model.User;



/**
 *
 * @author ZnzDarck
 */
public class DaoUsuario {
    private Connection conn;
    
    public DaoUsuario(Connection conn) {
         this.conn = conn;
    }
    
    public Boolean login(User usuario) {
        PreparedStatement ps;
        ResultSet res;
        String p;
        try {
            ps = conn.prepareStatement("SELECT PASSWORD FROM USER "
                    + "WHERE LOGIN = ? AND STATUS = 'A' ");
            
            ps.setString(1, usuario.getLogin());
 
            res = ps.executeQuery();
            
            while(res.next()){
                p = res.getString("PASSWORD");
                return p.equals(usuario.getPassword());
            }
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        return false;
    }
    
    public ResultSet getAllUsers (){
        PreparedStatement ps;
        ResultSet res = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM USER WHERE STATUS = 'A' ");
  
            res = ps.executeQuery();  
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return res;
    }
    
    public ResultSet getByLogin (String u){
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM USER WHERE LOGIN = ? AND STATUS = 'A' ");
                      
            ps.setString(1, u);
            
            res = ps.executeQuery();  
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return res;
    }
    public void update (User u){
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement("UPDATE USER SET STATUS = 'I' WHERE ID_USER  =  ?");
                      
            ps.setLong(1, u.getId());
            
            ps.execute();  
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
     public void insert(User u){
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement("INSERT INTO USER (LOGIN, PASSWORD, STATUS) VALUES (?,?,'A') ");
                      
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            
            ps.execute();  
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }  
}




