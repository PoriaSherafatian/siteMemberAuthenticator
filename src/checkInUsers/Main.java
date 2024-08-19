package checkInUsers;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your passcode: ");
        int passcode = scanner.nextInt();
        scanner.close();

        System.out.println("Checking your entry information....\n");

        if (authenticateUser(username, passcode)) {
            System.out.println("Welcome back " + username + "\nLogged in successfully");
        } else {
            System.out.println("You have to sign in first then logged in");
        }
    }

    public static boolean authenticateUser(String username, int passcode) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "Poria1382")) {
            String query = "SELECT user_name, passcode FROM registereduser WHERE user_name = ? AND passcode = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, passcode);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }
    }
}
