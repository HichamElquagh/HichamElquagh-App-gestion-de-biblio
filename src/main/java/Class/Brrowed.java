package Class;

import java.util.Date;

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


 }






