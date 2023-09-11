package Class;

import BD.Cdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.*;


public class Brrowed {


 private int id ;
 private Book book;
 private Brrower  brrower;
 private Date strat_date;
 private Date end_date;
 private String  status_book;
 private int brrower_id;



 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public Book getIsbn_book() {
  return book;
 }

 public void setIsbn_book(Book book) {
  this.book = book;
 }

 public Brrower  getBrrower_id() {
  return brrower;
 }

 public void setBrrower_id(Brrower brrower) {
  this.brrower = brrower;
 }

 public Date getStrat_date() {
  return strat_date;
 }

 public void setStrat_date(Date strat_date) {
  this.strat_date = strat_date;
 }

 public Date getEnd_date() {
  return end_date;
 }

 public void setEnd_date(Date end_date) {
  this.end_date = end_date;
 }

 public String getStatus_book() {
  return status_book;
 }



 private boolean isISBNExists(int isbn) {
  // Add your database query here to check if the ISBN exists
  // Return true if it exists, false otherwise
  // Replace the example logic below with your database query

  // Example: Check if the ISBN exists in a hypothetical "books" table
  String query = "SELECT COUNT(*) FROM books WHERE isbn = ?";

  try (Connection connection = Cdb.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(query)) {
   preparedStatement.setInt(1, isbn);
   ResultSet resultSet = preparedStatement.executeQuery();
   if (resultSet.next()) {
    int count = resultSet.getInt(1);
    return count > 0;
   }
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }

  return false; // Default to false if there's an error or no match
 }

 // Function to borrow a book
 public void borrowBook() {
  Scanner scanner = new Scanner(System.in);
  boolean isbnExists = false;

  do {
   System.out.println("Please Enter ISBN for the book: ");
   int isbnInput = scanner.nextInt();
   scanner.nextLine(); // Consume the newline character
   // Check if the ISBN exists
   if (isISBNExists(isbnInput)) {
    isbnExists = true;

    String insertSql = "INSERT INTO brrowedbooks (isbn_book, brrower_id, start_date, end_date, status_book) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE books SET available_quantity = available_quantity - 1 WHERE isbn = ?";

    try (Connection connection = Cdb.getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(insertSql);
         PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
     Date currentDate = new Date();
     // Calculate the end date as current date + 8 days
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(currentDate);
     calendar.add(Calendar.DAY_OF_YEAR, 8);
     Date endDate = calendar.getTime();

     // Set ISBN and Borrower ID for both statements
     int borrowerId = brrower.getId();
     insertStatement.setInt(1, isbnInput);
     insertStatement.setInt(2, borrowerId);
     updateStatement.setInt(1, isbnInput);

     // Set start and end dates for the INSERT statement
     insertStatement.setDate(3, new java.sql.Date(currentDate.getTime())); // Set start date
     insertStatement.setDate(4, new java.sql.Date(endDate.getTime())); // Set end date

     // Set status_book for the INSERT statement
     insertStatement.setString(5, "brrowed");

     // Execute the INSERT statement to borrow the book
     int rowsInserted = insertStatement.executeUpdate();
     if (rowsInserted > 0) {
      System.out.println(isbnInput + " borrowed successfully.");
     }

     // Execute the UPDATE statement to decrement available_quantity
     int rowsUpdated = updateStatement.executeUpdate();
     if (rowsUpdated > 0) {
      System.out.println("Available quantity updated.");
     }

    } catch (SQLException e) {
     System.out.println(e.getMessage());
    }
   } else {
    System.out.println("ISBN does not exist in the database. Please enter another ISBN.");
   }
  } while (!isbnExists);
 }


 private boolean hasReservation(String cin) {
  // Add your database queries here to check if cin exists in brrowed and has a corresponding entry in brrowedbooks
  // Return true if both conditions are met, false otherwise
  // Replace the example logic below with your database queries

  try (Connection connection = Cdb.getConnection()) {
   // Check if cin exists in brrowed
   String checkBrrowedQuery = "SELECT id FROM brrower WHERE cin = ?";
   PreparedStatement checkBrrowedStatement = connection.prepareStatement(checkBrrowedQuery);
   checkBrrowedStatement.setString(1, cin);
   ResultSet brrowedResult = checkBrrowedStatement.executeQuery();

   if (brrowedResult.next()) {
    int brrowedId = brrowedResult.getInt("id");
    this.brrower_id = brrowedId;
    System.out.println("this is id brrower for check "+ brrowedId);

    // Check if the corresponding ID exists in brrowedbooks
    String checkBrrowedBooksQuery = "SELECT COUNT(*) FROM brrowedbooks WHERE brrower_id = ?";
    PreparedStatement checkBrrowedBooksStatement = connection.prepareStatement(checkBrrowedBooksQuery);
    checkBrrowedBooksStatement.setInt(1, brrowedId);
    ResultSet brrowedBooksResult = checkBrrowedBooksStatement.executeQuery();

    if (brrowedBooksResult.next()) {
     int count = brrowedBooksResult.getInt(1);
     System.out.println("this is count for check "+count);
     System.out.println();
     return count > 0;
    }
   }
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }

  return false; // Default to false if there's an error or no reservation
 }

 // Function to retrieve a book
 public void RetrieveAbook() {
  boolean cinExists = false;
  do {
  Scanner scanner = new Scanner(System.in);
  System.out.println("Please Enter your CIN: ");
  String cinInput = scanner.nextLine();

  // Check if there is a reservation for the user with the given CIN
  if (hasReservation(cinInput)) {
   cinExists = true;
   System.out.println("Please Enter ISBN for borrowing: ");
   int isbnInput = scanner.nextInt();
   scanner.nextLine(); // Consume the newline character
   String sqldelete =  " DELETE FROM  brrowedbooks  WHERE brrower_id = ? AND isbn_book = ? ";
   String sqlupd =  "UPDATE books SET available_quantity = available_quantity + 1 WHERE isbn = ?";
    try( Connection connection = Cdb.getConnection();
    PreparedStatement deleteStatement = connection.prepareStatement(sqldelete);
    PreparedStatement updateStatement = connection.prepareStatement(sqlupd)){
     deleteStatement.setInt(1, brrower_id);
     deleteStatement.setInt(2, isbnInput);
     int rowsDeleted = deleteStatement.executeUpdate();
     if (rowsDeleted > 0){
      updateStatement.setInt(1, isbnInput);
      int rowsUpdated = updateStatement.executeUpdate();
      System.out.println("Book retrived successfuly ");
     }
     else {
      System.out.println("ISBN does not exist in the brrowedbooks table. Please enter another ISBN.");
     }

    }catch (SQLException e){
      System.out.println(e.getMessage());
    }

  } else {
   System.out.println("You don't have a reservation with this CIN.");
  }
 } while (!cinExists);
 }
}







