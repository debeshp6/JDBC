import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String query = "select * from employees;";
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
            ResultSet rs = stmt.executeQuery(query); // to store all data of our database
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String jobTitle = rs.getString("job_title");
                double salary = rs.getDouble("salary");
                System.out.println();
                System.out.println("================================================");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Job Title: " + jobTitle);
                System.out.println("Salary: " + salary);
            }

            rs.close();
            stmt.close();
            con.close();
            System.out.println();
            System.out.println("connection closed successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}