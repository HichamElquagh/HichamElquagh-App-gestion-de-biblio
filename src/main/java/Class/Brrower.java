package Class;

import BD.Cdb;
import javafx.scene.text.Text;

import java.sql.*;

public class Brrower {


    private int id ;
    private String name_empr;
    private String cin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_empr() {
        return name_empr;
    }

    public void setName_empr(String name_empr) {
        this.name_empr = name_empr;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    public void register(){
        String sql = "INSERT INTO brrower (brrower_name , cin )VALUES (?,?) ";
        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name_empr);
            preparedStatement.setString(2, cin);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int brrowerId = generatedKeys.getInt(1);
                        this.setId(brrowerId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to add Brrower.");
            e.printStackTrace(); // Print the error details for debugging.
        }

    }
    public int checkCinBrrower() {
        String sql = "SELECT id FROM brrower WHERE cin = ? ";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cin);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    int idBrrower = result.getInt("id");
                    this.setId(idBrrower);
                    System.out.println("Deja Brrower.");
                    return 1;

                }
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
        }

        return -1 ;

    }
}
