import java.sql.*;
import java.util.Scanner;
import java.io.*;
import javax.xml.transform.Result;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "8389892499Dp@";
//      String image_path = "C:\\Users\\debes\\Downloads\\emmy.jpg";
//      String query = "insert into image_table(image_data) values(?)";
        String folder_path = "C:\\Users\\debes\\Downloads\\";
        String query = "select image_data from image_table where image_id = (?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully.");
/*
            FileInputStream fileInputStream = new FileInputStream(image_path); // transforms image into bytes
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBytes(1, imageData);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0) {
                System.out.println("image insertion successfully.");
            } else {
                System.out.println("image not inserted");
            }
*/
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                byte[] imageData = resultSet.getBytes("image_data");
                String image_path = folder_path + "extractedImage.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(imageData);
            } else {
                System.out.println("image not found");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



