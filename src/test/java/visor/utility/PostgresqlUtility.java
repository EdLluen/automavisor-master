package visor.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresqlUtility {
    public Connection connectDatabase(String url_bd,String port,String bd,String user,String password) {
        Connection connection = null;
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {

                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }

            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(url_bd+":"+port+"/"+bd,user,password);

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    return connection;
    }
}


