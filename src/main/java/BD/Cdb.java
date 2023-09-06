package BD;
import java.sql.*;
public class Cdb {


        public static void main()
        {
            Connection connection = null;
            try {
                // below two lines are used for connectivity.
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/gestion_biblio",
                        "root", "");
                System.out.println("Connection Success");
            } catch (Exception e) {
                System.out.println("Connection Failed");
            }
        } // function ends
} // class ends


