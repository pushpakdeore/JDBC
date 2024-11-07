import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleTransactions {
    private static final String url = "jdbc:mysql://localhost:3306/bank_db";
    private static final String username = "root";
    private static final String password = "Karanjad@202";

    public static void main(String[] args) {
        try (Connection con  = DriverManager.getConnection(url,username,password)){
            con.setAutoCommit(false);
            String debit ="update accounts set balance =balance-? where id =?";
            String credit ="update accounts set balance =balance+? where id =?";
            try(PreparedStatement debitpre =con.prepareStatement(debit);
            PreparedStatement creditpre =con.prepareStatement(credit)) {

                debitpre .setDouble(1,20);
                debitpre.setInt(2,1);
                int debitresult = debitpre.executeUpdate();

                creditpre.setDouble(1,20);
                creditpre.setInt(2,2);
                int creditresult  = creditpre.executeUpdate();

                if(debitresult==1&& creditresult==1){
                    System.out.println("transaction is successfully");
                    con.commit();
                }else {
                    con.rollback();
                    System.out.println("transaction fail.Rolled back");

                }

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
