import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Telkom University/Java/Exercise/Tubes/src/testDB.db"; // Path ke database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }
}
