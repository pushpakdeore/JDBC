import java.sql.*;

public class Test {
    private static final String url = "jdbc:mysql://localhost:3306/testdb";
    private static final String username = "root";
    private static final String password = "Karanjad@202";

    public static void main(String[] args) {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            return;
        }

        // Database connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            // READ operation using PreparedStatement
            String selectQuery = "SELECT * FROM students";
            try (PreparedStatement pstmt = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = pstmt.executeQuery()) {
                System.out.println("Reading data from the students table:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    double mark = resultSet.getDouble("mark");
                    System.out.printf("ID: %d, Name: %s, Age: %d, Mark: %.2f%n", id, name, age, mark);
                }
            }

            // WRITE operation using PreparedStatement
            String insertQuery = "INSERT INTO students (id, name, age, mark) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                pstmt.setInt(1, 24);
                pstmt.setString(2, "snehal");
                pstmt.setInt(3, 23);
                pstmt.setDouble(4, 25.4);

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Data inserted successfully." : "Data not inserted.");
            }

            // UPDATE operation using PreparedStatement
            String updateQuery = "UPDATE students SET mark = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
                pstmt.setDouble(1, 89.5);
                pstmt.setInt(2, 2);

                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated > 0 ? "Data updated successfully." : "Data not updated.");
            }

            // DELETE operation using PreparedStatement
            String deleteQuery = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, 2);

                int rowsDeleted = pstmt.executeUpdate();
                System.out.println(rowsDeleted > 0 ? "Deleted successfully!" : "No record found, nothing deleted.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
