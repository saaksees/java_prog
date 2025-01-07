package saakshiapplication;

import java.sql.*;

public class ConferenceAttendeeManager {

    private static final String URL = "jdbc:mysql://localhost:3309/lab8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addAttendee(String fullName, String email, String contactNumber, String country) {
        String query = "INSERT INTO Attendees (full_name, email, contact_number, country) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, contactNumber);
            stmt.setString(4, country);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendee added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editAttendee(int attendeeId, String email, String contactNumber) {
        String query = "UPDATE Attendees SET email = ?, contact_number = ? WHERE attendee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, contactNumber);
            stmt.setInt(3, attendeeId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendee information updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAttendee(int attendeeId) {
        String query = "DELETE FROM Attendees WHERE attendee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, attendeeId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendee deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchAttendee(String searchValue) {
        String query = "SELECT * FROM Attendees WHERE full_name LIKE ? OR attendee_id = ? OR country LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (isNumeric(searchValue)) {
                stmt.setInt(2, Integer.parseInt(searchValue));
                stmt.setString(1, "%");
                stmt.setString(3, "%");
            } else {
                stmt.setString(1, "%" + searchValue + "%");
                stmt.setInt(2, -1);
                stmt.setString(3, "%" + searchValue + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("attendee_id") +
                        ", Name: " + rs.getString("full_name") +
                        ", Email: " + rs.getString("email") +
                        ", Contact: " + rs.getString("contact_number") +
                        ", Country: " + rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void generateAttendeeStatistics() {
        String query = "{CALL GetAttendeeStatistics()}";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("country") +
                        ", Attendees: " + rs.getInt("attendee_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        addAttendee("Saakshi", "saak@gmail.com", "1111111111", "IND");
        editAttendee(1, "saak2@gmail.com", "0987654321");
        deleteAttendee(1);
        searchAttendee("saak");
        generateAttendeeStatistics();
    }
}
