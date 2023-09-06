package Class;

import BD.Cdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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




    public void addBook() {
        String sql = "INSERT INTO book (isbn , title, author_id, available_quantity, total_quantity, missing_quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Cdb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, 1);
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

    public Book searchBook() {
        return null;
    }

    public void displayAvailableBooks() {
    }

    public void displayBorrowedBooks() {
    }

    public void returnBook() {
    }
}



