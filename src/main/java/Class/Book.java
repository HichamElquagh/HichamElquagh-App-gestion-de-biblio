package Class;

import BD.Cdb;

import java.sql.*;

public class Book {
    private int isbn;
    private String title;
    private Author author;
    private int availableQuantity;

    private int totalQuantity;
    private int missingQuantity;


    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthorId() {
        return author;
    }

    public void setAuthorId(Author author) {
        this.author = author;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getMissingQuantity() {
        return missingQuantity;
    }

    public void setMissingQuantity(int missingQuantity) {
        this.missingQuantity = missingQuantity;
    }



    public int getIsbnBook(){
        String sql = "SELECT title FROM book WHERE isbn = ? " ;

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String Titlebook = result.getString("title");
                    this.setTitle(Titlebook);
                    return 1 ;
                }
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions here
        }

return -1;
    }
    public void addBook() {
        String sql = "INSERT INTO book (isbn , title, author_id, available_quantity, total_quantity, missing_quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, author.getId());
            preparedStatement.setInt(4, availableQuantity);
            preparedStatement.setInt(5, totalQuantity);
            preparedStatement.setInt(6, missingQuantity);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to add the book.");
        }
    }



    public void editBook() {
        String updateSql = "UPDATE book SET title=?, author_id=?, available_quantity=?, total_quantity=?, missing_quantity=? WHERE isbn=?";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, 1); // Assuming you have an Author class with an ID
            preparedStatement.setInt(3, availableQuantity);
            preparedStatement.setInt(4, totalQuantity);
            preparedStatement.setInt(5, missingQuantity);
            preparedStatement.setInt(6, isbn); // Specify the ISBN of the book to update

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book with ISBN " + isbn + " updated successfully.");
            } else {
                System.out.println("No book found with ISBN " + isbn + ".");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update the book.");
        }
    }


    public void deleteBook() {
        String sql = "DELETE FROM book WHERE isbn = ?";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book with ISBN " + isbn + " deleted successfully.");
            } else {
                System.out.println("No book found with ISBN " + isbn + ".");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete the book.");
        }
    }


    public void searchBookByTitle(String title){

        String sql = "SELECT * FROM book WHERE title LIKE ?";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + title + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Book found:");
                    System.out.println("ISBN: " + resultSet.getInt("isbn"));
                    System.out.println("Title: " + resultSet.getString("title"));
                    System.out.println("Available Quantity " + resultSet.getInt("available_quantity"));
                    System.out.println("Total Quantity  : " + resultSet.getInt("total_quantity"));
                    System.out.println("Missing Quantity  " + resultSet.getInt("missing_quantity"));
                    // Display other book properties as needed
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search for books by title.");
        }
    }



    public void displayAvailableBooks() {
        String sql = "SELECT * FROM book ";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Book found:");
                    System.out.println("ISBN: " + resultSet.getInt("isbn"));
                    System.out.println("Title: " + resultSet.getString("title"));
                    System.out.println("Available Quantity " + resultSet.getInt("available_quantity"));
                    System.out.println("Total Quantity  : " + resultSet.getInt("total_quantity"));
                    System.out.println("Missing Quantity  " + resultSet.getInt("missing_quantity"));
                    // Display other book properties as needed
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search for books by title.");
        }
    }

    public void displayBorrowedBooks() {
    }

    public void returnBook() {
    }
}



