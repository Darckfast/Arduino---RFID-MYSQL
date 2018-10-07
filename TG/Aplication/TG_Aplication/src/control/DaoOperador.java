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
}
