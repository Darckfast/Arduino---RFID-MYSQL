/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexao {
   private String driver;
   private Connection connection=null;
   
   public void setDriver(String driver) {
        this.driver = driver;
   }
   
   public Connection conectar() {
        
	   if (connection == null){
	      try {
                 Class.forName(driver).newInstance();
                 connection = DriverManager.getConnection("jdbc:mysql://192.168.1.46/arduino?" + "user=admin&password=1234");               
                 //connection = DriverManager.getConnection("jdbc:mysql://localhost/arduino?" + "user=admin&password=1234");               
	         System.out.println("Conexao OK");
              } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
	   }  
	   return connection;
   }
   
   public void fecharConexao(){
        if (connection != null){
	   try {
                  connection.close();
           } catch (SQLException ex) {
                   System.out.println(ex.toString());    
           }
        }   
    }
}