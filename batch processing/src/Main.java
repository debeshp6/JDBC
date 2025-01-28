import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully.");
            connection.setAutoCommit(false);

/*
            Statement statement = connection.createStatement();
            statement.addBatch("insert into employees(id, name, job_title, salary) values(6, 'Riya', 'HR Manager', 30000.0)");
            statement.addBatch("insert into employees(id, name, job_title, salary) values(7, 'Alisha', 'Data Analyst', 45000.0)");
            statement.addBatch("insert into employees(id, name, job_title, salary) values(8, 'Moutushi', 'Designer', 35000.0)");
            int[] batchResult = statement.executeBatch();
            connection.commit();
            System.out.println("batch executed successfully.");
*/
            String query = "insert into employees(id, name, job_title, salary) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.print("enter id: ");
                int id = scanner.nextInt();
                System.out.print("enter name: ");
                String name = scanner.next();
                System.out.print("job title: ");
                String jobTitle = scanner.next();
                System.out.print("salary: ");
                double salary = scanner.nextDouble();

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, jobTitle);
                preparedStatement.setDouble(4, salary);

                preparedStatement.addBatch();
                System.out.print("add more values? : Y/N = ");
                String decision = scanner.next();
                if(decision.toUpperCase().equals("N")) {
                    break;
                }
            }

            int[] batchResult = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("batch executed successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}