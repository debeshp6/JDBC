import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String query = "select * from employees where name = ? and job_title = ?"; // multiple placeholders

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully.");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "aisha");
            preparedStatement.setString(2, "Executive");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String jobTitle = resultSet.getString("job_title");
                double salary = resultSet.getDouble("salary");
                System.out.println();
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Job Title: " + jobTitle);
                System.out.println("Salary: " + salary);
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();
            System.out.println();
            System.out.println("connection closed successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}