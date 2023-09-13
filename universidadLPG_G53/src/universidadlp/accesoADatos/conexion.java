package universidadlp.accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String DB = "universidadlp";
    private static final String USUARIO = "root";
    private static String PASSWORD = "";
    private static Connection connection;
    private conexion() {
    }

    public static Connection getConexion() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL + DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar el driver: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos: " + ex.getMessage());
            }
        }
        return connection;
    }
}
