package HospitalManagementSystem;

import javax.print.Doc;
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "8389892499Dp@";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            while (true) {
                System.out.println("===================================== HOSPITAL MANAGEMENT SYSTEM =====================================");
                System.out.println("1. Add patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // add patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // view patients
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // view doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        // book appointment
                        bookAppointments(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        try {
                            exit();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        scanner.close();
                        return;
                    default:
                        System.out.println("ENTER VALID CHOICE!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointments (Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if (patient.getPatientByID(patientId) && doctor.getDoctorByID(doctorId)) {
            if(checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                String appointmentQuery = "insert into appointments(patient_id, doctor_id, appointment_date) values(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("APPOINTMENT BOOKED!");
                    } else {
                        System.out.println("Failed to book appointment.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor not available on this date.");
            }
        } else {
            System.out.println("Either Doctor or Patient doesn't exist.");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "select count(*) from appointments where doctor_id = ? and appointment_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM!");
    }
}
