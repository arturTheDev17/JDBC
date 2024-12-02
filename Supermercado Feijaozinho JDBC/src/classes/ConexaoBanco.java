package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/db_sistema_feijaozinho?createDatabaseIfNotExist=true";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection getConnections () throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
