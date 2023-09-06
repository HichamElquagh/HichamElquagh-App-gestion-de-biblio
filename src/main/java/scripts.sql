CREATE DATABASE gestion_biblio;
    USE gestion_biblio;


CREATE TABLE author (
                        id int primary key auto_increment,
                        full_name varchar(255)

);
CREATE TABLE book (
                      isbn int primary key auto_increment,
                      title varchar(255),
                      author_id int,
                      available_quantity int,
                      total_quantity  int,
                      missing_quantity int,
                      FOREIGN KEY (author_id) REFERENCES author(id)
);
CREATE TABLE brrower (
                         id int primary key auto_increment,
                         brrower_name  varchar(255),
                         cin  text

);

CREATE TABLE brrowedbooks (
                              id int primary key auto_increment,
                              isbn_book  int,
                              brrower_id  int,
                              start_date date,
                              end_date date,
                              status_book varchar(255),
                              FOREIGN KEY (isbn_book) REFERENCES book(isbn),
                              FOREIGN KEY (brrower_id) REFERENCES brrower(id)
);


create table deleted_book_log
(
                isbn               int,
                deleted_at          DATETIME
);
DELIMITER //
CREATE TRIGGER after_book_delete
    AFTER DELETE ON book FOR EACH ROW
BEGIN
    INSERT INTO deleted_book_log (isbn, deleted_at)
    VALUES (OLD.isbn, NOW());
END ;
//
DELIMITER ;









