package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cdb {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gestion_biblio",
                    "root", "");
            System.out.println("Connection Success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
        return connection;
    }
}
