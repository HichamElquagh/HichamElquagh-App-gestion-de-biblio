package Class;

import BD.Cdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
public class Brrowed {


 private int id ;
 private Book book;
 private Brrower  brrower;
 private Date strat_date;
 private Date end_date;
 private String  status_book;



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

 public void setStatus_book(String status_book) {
  this.status_book = status_book;
 }


 public void borrowBook() {
  String sql = "INSERT INTO borrowed (isbn_book, brrower_id, start_date, end_date, status_book) VALUES (?, ?, ?, ?, ?)";

  try (Connection connection = Cdb.getConnection();
   PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
   Date currentDate = new Date();
   // Calculate the end date as current date + 8 days
   Calendar calendar = Calendar.getInstance();
   calendar.setTime(currentDate);
   calendar.add(Calendar.DAY_OF_YEAR, 8);
   Date endDate = calendar.getTime();
   // Set ISBN and Borrower ID
   preparedStatement.setInt(1, book.getIsbn());
   preparedStatement.setInt(2, brrower.getId());

   preparedStatement.setDate(3, new java.sql.Date(currentDate.getTime())); // Set start date
   preparedStatement.setDate(4, new java.sql.Date(endDate.getTime())); // Set end date

   // Set status_book
   preparedStatement.setString(5, "brrowed");

   int rowsInserted = preparedStatement.executeUpdate();
   if (rowsInserted > 0) {
    System.out.println("Book borrowed successfully.");
   }
  } catch (SQLException e) {
   System.out.println("Failed to borrow the book.");
  }
 }
 }







