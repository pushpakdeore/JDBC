import java.sql.*;

public class HandleResultSetUpdateData {
    private static final String url = "jdbc:mysql://localhost:3306/school_db";
    private static final String username="root";
    private static final String password ="Karanjad@202";
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection(url,username,password)) {
            String query = "update students set grade =? where id =?";
            String query1 ="select* from students";
            try(PreparedStatement pre =con.prepareStatement(query);
            PreparedStatement pre1 =con.prepareStatement(query1)) {
                pre.setString(1,"B");
                pre.setInt(2,1);

                int s =pre.executeUpdate();
                if(s>0){
                    System.out.println("data updated successfuly !");
                }
                ResultSet resultset =pre1.executeQuery();
                while (resultset.next()){
                    System.out.print(" id:"+resultset.getInt("id"));
                    System.out.print(" name:"+resultset.getString("name"));
                    System.out.println(" greade:"+resultset.getString("grade"));
                }



            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
