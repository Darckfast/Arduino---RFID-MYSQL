/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.control;

import app.model.Card;
import app.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class DAOCadastroUsuario {
    
    private Connection conn;
    
    public DAOCadastroUsuario(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(User user, Card card) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO USER"
                    + "(cpf, nome, email, telefone, idCARD)"
                    + " VALUES(?,?,?,?,?)");
            
            ps.setString(1, user.getCpf());
            ps.setString(2, user.getNome());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getCard().getId());
            ps.setLong(5, user.getTelefone());
           
                      
            ps.execute();
            
            ps = conn.prepareStatement("INSERT INTO CARD"
                    + "(idAccess, hash)"
                    + " VALUES(?,?)");
            
            ps.setLong(1, card.getAccess().getId());
            ps.setString(2, card.getHash());
               
            ps.execute();
            
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    /*
    public void alterar(User user) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE CLIENTE SET nome = ?, endereco = ?, cidade = ?, cep = ?, uf = ?, ddd = ?, telefone= ?, limiteCred = ?, limiteDisp = ? where cpf = ?");
            
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEndereco());
            ps.setString(3, user.getCidade());
            ps.setInt(4, Integer.parseInt(user.getCep()));
            ps.setString(5, user.getUf());
            ps.setInt(6, Integer.parseInt(user.getDdd()));
            ps.setInt(7, Integer.parseInt(user.getTelefone()));
            ps.setDouble(8, user.getLimiteCred());
            ps.setDouble(9, user.getLimiteDisp());
            ps.setString(10, user.getCpf());
           

            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public  User consultar (String cpf) {
        User d = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CLIENTE WHERE " +
                                                 "CPF = ?");
            
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                d = new User (cpf, rs.getString("nome"), rs.getDouble("limiteCred"));
                d.setCep(rs.getString("cep"));
                d.setCidade(rs.getString("cidade"));
                d.setDdd(rs.getString("ddd"));
                d.setEndereco(rs.getString("endereco"));
                d.setTelefone(rs.getString("telefone"));
                d.setLimiteDisp(rs.getDouble("limitedisp"));
                d.setUf(rs.getString("uf"));
                d.setLimiteCredAntigo(rs.getDouble("limiteCred"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (d);
    }    
     
     public void excluir(User user) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM CLIENTE where cpf = ?");
            
            ps.setString(1, user.getCpf());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
        
    }
    public User Limite (String cpf) {
        User d = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT LIMITEDISP FROM CLIENTE WHERE " +
                                                 "CPF = ?");
            
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            d.setLimiteCredAntigo(rs.getDouble("LIMITEDISP"));
           
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (d);
    }   

*/
}
