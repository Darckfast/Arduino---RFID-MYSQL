import control.Conexao;
import control.DaoLogs;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Logs;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.logging.LogFactory;

public class JasperReportExemple {
 
private static final String URL_STRING = "jdbc:mysql://192.168.1.46/arduino";
private static final String DRIVER_STRING = "com.mysql.jdbc.Driver";
private static final String LOGIN_STRING = "admin";
private static final String PWD_STRING = "1234";
 
public JasperReportExemple() {
}
 
public void gerar( String layout ) throws JRException , SQLException, ClassNotFoundException {
//gerando o jasper design
JasperDesign desenho = JRXmlLoader.load( layout );
 
//compila o relatório
JasperReport relatorio = JasperCompileManager.compileReport( desenho );
 
//estabelece conexão
Class.forName( DRIVER_STRING );
Connection con = DriverManager.getConnection( URL_STRING , LOGIN_STRING , PWD_STRING );
Statement stm = con.createStatement();
String query = "SELECT L.idLOG AS ID,"
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
ResultSet rs = stm.executeQuery( query );
 
//implementação da interface JRDataSource para DataSource ResultSet
JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

//executa o relatório
Map parametros = new HashMap();
parametros.put("nota", new Double(10));
JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros, jrRS );
 
//exibe o resultado
JasperViewer viewer = new JasperViewer( impressao , true );
viewer.show();
}
 
    public static void main(String[] args) {
        try {
            Conexao conexao = new Conexao();
            conexao.setDriver("com.mysql.cj.jdbc.Driver");
            //conexao.conectar();
            DaoLogs daoLogs = new DaoLogs(conexao.conectar());    

            JRResultSetDataSource jrRS = new JRResultSetDataSource( daoLogs.getAll() );
            Map parametros = new HashMap( );
            parametros.put("query", "SELECT * FROM ROOM;");
                
            JasperPrint impressao = JasperFillManager.fillReport(JasperCompileManager.compileReport( "C:\\Users\\ZnzDarck\\Arduino---RFID-MYSQL\\TG\\Aplication\\TG_Aplication\\src\\report\\report.jrxml" ) , parametros,  jrRS );

            JasperViewer jrviewer = new JasperViewer(impressao, true);   
            // Deixa a janela visível
            jrviewer.setVisible(true);
            // Trás a janela à frente das outras
            jrviewer.toFront();	  
        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null,"Não foi possivel gerar relatório! "+erro);  
        }
    
    }
}