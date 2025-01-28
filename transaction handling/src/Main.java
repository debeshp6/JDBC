import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
        String withdrawQuery = "update accounts set balance = balance - ? where account_number = ?";
        String depositQuery = "update accounts set balance = balance + ? where account_number = ?";

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

            try {
                PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);

                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account123");
                depositStatement.setDouble(1, 500);
                depositStatement.setString(2, "account456");

                int rowsAffectedWithdrawl = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();

                if (rowsAffectedDeposit > 0 && rowsAffectedWithdrawl > 0) {
                    connection.commit();
                    System.out.println("transaction successfull");
                } else {
                    connection.rollback();
                    System.out.println("transaction failed");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}