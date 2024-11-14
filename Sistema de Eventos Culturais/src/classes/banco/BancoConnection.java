package classes.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_eventos_culturais?createDatabaseIfNotExist=true";

    public static final String USUARIO = "root";
    public static final String SENHA = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( URL , USUARIO , SENHA );
    }
}
