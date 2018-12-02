import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
 
public class JasperReportExemple {
 
private static final String URL_STRING = "jdbc:mysql://127.0.0.1/teste";
private static final String DRIVER_STRING = "com.mysql.jdbc.Driver";
private static final String LOGIN_STRING = "";
private static final String PWD_STRING = "";
 
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
String query = "select * from turma";
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
            new JasperReportExemple().gerar( "report.jrxml" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}