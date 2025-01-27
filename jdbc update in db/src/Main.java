import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String query = "update employees\n" +
                "set job_title = 'Technical Lead', salary = 50000.0\n" +
                "where id = 2;";

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
            int rowsAffected = stmt.executeUpdate(query); // to check whether the row is affected or not

            if(rowsAffected > 0) {
                System.out.println("update successful. " + rowsAffected + " rows affected.");
            } else {
                System.out.println("update failed.");
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