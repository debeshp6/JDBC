import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String query = "insert into employees(id, name, job_title, salary) values(4, 'Rupsa', 'Desk Manager (Head)', 25000.0);";

        try {
            Class.forName("com.mysql.jdbc.Driver"); // load
            System.out.println("drivers loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully!");
            Statement stmt = con.createStatement(); // to run queries
            int rowsAffected = stmt.executeUpdate(query);

            if(rowsAffected > 0) {
                System.out.println("insert successful. " + rowsAffected + " rows affected.");
            } else {
                System.out.println("insertion failed.");
            }

            stmt.close();
            con.close();
            System.out.println();
            System.out.println("connection closed successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}