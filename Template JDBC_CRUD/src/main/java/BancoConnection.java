import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoConnection {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/db_crud?createDatabaseIfNotExist=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( URL, USER , PASSWORD );
    }

}