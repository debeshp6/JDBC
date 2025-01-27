import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // database url
        String url = "jdbc:mysql://localhost:3306/Students";

        // database credentials
        String username = "root";
        String password = "8389892499Dp@";

        // establish the connection
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to the database");
            System.out.println(connection);
            // perform database operation here
        } catch (SQLException e) {
            System.err.println("connection failed: " + e.getMessage());
        }
    }
}