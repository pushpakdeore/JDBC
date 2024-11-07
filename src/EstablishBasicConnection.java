import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EstablishBasicConnection {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username="root";
    private static final String password ="Karanjad@202";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection(url,username,password)){
            System.out.println("Connection Successfull !");


        }catch (SQLException e){
            System.out.println("Connection failed");
        }





    }
}
