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
public class DaoLogs {
    private Connection conn;
    
    public DaoLogs(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getAll(){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM LOGS_ACESS");

            res = ps.executeQuery();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
}
