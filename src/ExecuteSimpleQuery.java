import java.sql.*;

public class ExecuteSimpleQuery {
    private static final String url = "jdbc:mysql://localhost:3306/company_db";
    private static final String username = "root";
    private static final String password = "Karanjad@202";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try(Connection con  = DriverManager.getConnection(url,username,password)) {
            String query ="Select* from employees";
            try(PreparedStatement pre = con.prepareStatement(query) ) {
                ResultSet s = pre.executeQuery();
                while (s.next()){
                    int id  = s.getInt("id");
                    String name = s.getString("name");
                    int age =s.getInt("age");
                    System.out.println("id :"+id+" name :"+name+" age :"+age);
                }

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
