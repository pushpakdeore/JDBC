import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataUsingPreparedStatement {
    private static final String url = "jdbc:mysql://localhost:3306/company_db";
    private static final String username = "root";
    private static final String password = "Karanjad@202";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try (Connection con  = DriverManager.getConnection(url,username,password)){
            String query  ="insert into employees(name,age) values(?,?); ";
            try(PreparedStatement pre = con.prepareStatement(query)) {
                pre.setString(1,"pushpak");
                pre.setInt(2,23);
                int rowaffected = pre.executeUpdate();
                if(rowaffected>0){
                    System.out.println("insert Successfully !");
                }



            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
