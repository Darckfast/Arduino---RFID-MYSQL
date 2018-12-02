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
    private final Connection conn;
    
    public DaoLogs(Connection conn) {
         this.conn = conn;
    }
    
    public ResultSet getAll(){
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement("SELECT L.idLOG AS ID, L.hash AS HASH, L.data AS DATA, IFNULL(O.nome, 'DESCONHECIDO') AS OPERADOR, CASE L.granted WHEN 0 THEN 'NÃO GARANTIDO' ELSE 'GARANTIDO' END AS GRANTED, R.nome_sala AS SALA FROM arduino.LOGS_ACESS AS L LEFT JOIN arduino.CARD AS C ON L.CARD_idCARD = C.idCARD INNER JOIN arduino.ROOM AS R ON L.ROOM_idROOM = R.idROOM LEFT JOIN arduino.OPERATOR AS O ON L.OPERATOR_idUSER = O.idUSER ");
            
            res = ps.executeQuery();
        } catch (Exception ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    
    public ResultSet genReport(String dataIni, String dataFim, String sala){
        PreparedStatement ps = null;
        ResultSet res = null;
        String filter = "";
        String statement = "SELECT L.idLOG AS ID,"
                + " L.hash AS HASH, "
                + "L.data AS DATA, "
                + "IFNULL(O.nome, 'DESCONHECIDO') AS OPERADOR, "
                + "CASE L.granted WHEN 0 "
                + "THEN 'NÃO GARANTIDO' "
                + "ELSE 'GARANTIDO' "
                + "END AS GRANTED, "
                + "R.nome_sala AS SALA "
                + "FROM arduino.LOGS_ACESS AS L "
                + "LEFT JOIN arduino.CARD AS C "
                + "ON L.CARD_idCARD = C.idCARD "
                + "INNER JOIN arduino.ROOM AS R "
                + "ON L.ROOM_idROOM = R.idROOM "
                + "LEFT JOIN arduino.OPERATOR AS O "
                + "ON L.OPERATOR_idUSER = O.idUSER ORDER BY ID";
        
        if (!dataIni.isEmpty()) {
            filter = filter.concat(String.format(" DATA >= %s ", dataIni));
        }
        if (!dataFim.isEmpty()) {
            if (!filter.isEmpty()) {
                filter = filter.concat(String.format(" AND "));
            }
            filter = filter.concat(String.format(" DATA <= %s", dataFim));
        }
        if (!sala.isEmpty()) {
            if (!filter.isEmpty()){ 
                filter = filter.concat(String.format(" AND "));
            }
            filter = filter.concat(String.format(" R.nome_sala = %s ", sala));
        }
  
        try {
            if (!filter.isEmpty()) {
                statement = statement.concat(String.format(" WHERE %s", filter));
            }
            
            ps = conn.prepareStatement(statement);
            
            res = ps.executeQuery();
        } catch (Exception ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
}
