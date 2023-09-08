package Class;

import BD.Cdb;

import java.sql.*;

public class Author {
    private int id ;
    private String  name ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int addAuthor(String nameAuthor) {
        String sql = "INSERT INTO author (full_name) VALUES (?)";
        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nameAuthor);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Retrieve the auto-generated ID
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int authorId = generatedKeys.getInt(1);
                        this.setId(authorId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to add Author.");
            e.printStackTrace(); // Print the error details for debugging.
        }

        // Return a default value or handle the case where the author was not added.
        return -1; // You can choose an appropriate default value or error handling mechanism.
    }

    public int authorBookid()  {

                String sql = "SELECT id FROM author WHERE full_name = ?";

                try (Connection connection = Cdb.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, name);

                    try (ResultSet result = preparedStatement.executeQuery()) {
                        if (result.next()) {
                            int idbook = result.getInt("id");
                            this.setId(idbook);
                        }else{

                            addAuthor(name);
                        }
                    }
                } catch (SQLException e) {
                    // Handle any database-related exceptions here
                    e.printStackTrace();
                }

                // Return a default value or handle the case where no author with the given name is found.
                return -1; // You can choose an appropriate default value or error handling mechanism.
            }







    }



