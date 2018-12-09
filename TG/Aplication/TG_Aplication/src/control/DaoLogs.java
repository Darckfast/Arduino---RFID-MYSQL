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
import java.text.SimpleDateFormat;

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
    
    public ResultSet genReport(String dataIni, String dataFim, String sala) throws Exception{
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
                + "ON L.OPERATOR_idUSER = O.idUSER ";
        
        if (!dataIni.isEmpty()) {
            filter = filter.concat(String.format(" DATA >= DATE('%s') ", new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dataIni))));
        }
        if (!dataFim.isEmpty()) {
            if (!filter.isEmpty()) {
                filter = filter.concat(String.format(" AND "));
            }
            filter = filter.concat(String.format(" DATA <= DATE('%s')", new SimpleDateFormat("yyyy/MM/dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dataFim))));
        }
        if (!sala.isEmpty()) {
            if (!filter.isEmpty()){ 
                filter = filter.concat(String.format(" AND "));
            }
            filter = filter.concat(String.format(" R.nome_sala = '%s' ", sala));
        }
  
        try {
            if (!filter.isEmpty()) {
                statement = statement.concat(String.format(" WHERE %s ", filter));
            }
            statement = statement.concat(" ORDER BY ID ");
            
            ps = conn.prepareStatement(statement);
            
            res = ps.executeQuery();
        } catch (Exception ex) {
             System.out.println(ex.toString());   
        }
        
        return res;
    }
    
    public String getFirstDate(){
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(" SELECT DATE_FORMAT(L.data, '%d/%m/%Y') AS DATA FROM arduino.LOGS_ACESS L WHERE idLOG = 1;  ");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("DATA");
        } catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }
}
