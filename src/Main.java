import java.sql.*;

public class Main {
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

        // Database connection and statement execution
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            // READ operation
            String selectQuery = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Reading data from the students table:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double mark = resultSet.getDouble("mark");
                System.out.printf("ID: %d, Name: %s, Age: %d, Mark: %.2f%n", id, name, age, mark);
            }

            // WRITE operation
            String insertQuery = "INSERT INTO students(id, name, age, mark) VALUES (24, 'snehal', 23, 25.4)";
            int rowsInserted = statement.executeUpdate(insertQuery);
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data not inserted.");
            }

            // UPDATE operation
            String updateQuery = "UPDATE students SET mark = 89.5 WHERE id = 2";
            int rowsUpdated = statement.executeUpdate(updateQuery);
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("Data not updated.");
            }

            // DELETE operation
            String deleteQuery = "DELETE FROM students WHERE id = 2";
            int rowsDeleted = statement.executeUpdate(deleteQuery);
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("No record found with the specified ID, nothing deleted.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
