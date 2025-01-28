import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String query = "insert into employees(id, name, job_title, salary) values(?, ?, ?, ?)"; // multiple placeholders

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully.");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            String name = scanner.nextLine();
            scanner.nextLine();
            String jobTitle = scanner.nextLine();
            double salary = scanner.nextDouble();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, jobTitle);
            preparedStatement.setDouble(4, salary);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("data inserted successfully.");
            } else {
                System.out.println("data insertion failed");
            }

            connection.close();
            preparedStatement.close();
            System.out.println();
            System.out.println("connection closed successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}