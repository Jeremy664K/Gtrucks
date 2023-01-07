package clases;
import java.sql.*;

public class Conexion {
    
    public static Connection conectar(){
        
        try {
            
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_gt", "root", "");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error, en la Base de datos Local");
            return null;
        }
        
        
    }
}
