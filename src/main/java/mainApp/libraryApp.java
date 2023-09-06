package mainApp;

import Class.Book; // Import the Book class
import Class.Author;
import java.util.Scanner;

public class libraryApp {

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        final Scanner ui = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            switch (ui.nextInt()) {
                case 1:
                    addBookmain(); // Call the addBook method when the user selects "Add Book"
                    break;
                case 2:
                    EditBookmainmain();
                    break;
                case 3:
                    DeleteBookmain();
                    break;
                case 4:
                    // Call the displayAllBooks method
                    break;
                case 5:
                    System.out.println("Leaving the program now...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addBookmain() {

        Scanner scanner = new Scanner(System.in);

        System.out.print(" enter isbn code : ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        System.out.print(" enter title : ");
        String  title = scanner.nextLine();
        System.out.print(" enter Author : ");
        String  author = scanner.nextLine();
        System.out.print(" enter available quantity : ");
        int available = scanner.nextInt();
        System.out.print(" enter total quantity : ");
        int total = scanner.nextInt();

        // Create a Book object
        Book book = new Book();
        // Set the book properties
        book.setIsbn(isbn);
        book.setTitle(title);
         // Assuming you have an Author class with an ID
        book.setAvailableQuantity(available);
        book.setTotalQuantity(total);
        book.setMissingQuantity(0);
        //Author author1 = new Author();
        //author1.setName(author);
        // Call the addBook method to insert the book into the database
        book.addBook();
        System.out.println("Book added successfully.");
    }
    public static void EditBookmainmain(){
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter ISBN code: ");
            int isbn = scanner.nextInt();

            System.out.println("Choose a column to edit:");
            System.out.println("1. Title");
            System.out.println("2. Author ID");
            System.out.println("3. Available Quantity");
            System.out.println("4. Total Quantity");
            System.out.println("5. Missing Quantity");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            Book book = new Book(); // Create a Book object

            switch (choice) {
                case 1:
                    System.out.print("Enter new title: ");
                    scanner.nextLine(); // Consume the newline character
                    String newTitle = scanner.nextLine();
                    book.setIsbn(isbn);
                    book.setTitle(newTitle);
                    book.editBook(); // Call the editBook method to update the title
                    break;
                case 2:
                    System.out.print("Enter new Author: ");
                    scanner.nextLine(); // Consume the newline character
                    String newAuthor = scanner.nextLine();
                    book.setIsbn(isbn);
                    book.setTitle(newAuthor);
                    book.editBook();
                    break;
                case 3:
                    System.out.print("Enter new Available Quantity: ");
                    scanner.nextLine(); // Consume the newline character
                    int newAvailableQantity = scanner.nextInt();
                    book.setIsbn(isbn);
                    book.setAvailableQuantity(newAvailableQantity);
                    book.editBook();
                    break;
                case 4:
                    System.out.print("Enter new Total Quantity: ");
                    scanner.nextLine(); // Consume the newline character
                    int newTotalQantity = scanner.nextInt();
                    book.setIsbn(isbn);
                    book.setTotalQuantity(newTotalQantity);
                    book.editBook();
                    break;
                case 5:
                    System.out.print("Enter new Missing Quantity: ");
                    scanner.nextLine(); // Consume the newline character
                    int newMissingQantity = scanner.nextInt();
                    book.setIsbn(isbn);
                    book.setMissingQuantity(newMissingQantity);
                    book.editBook();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }



    }

    public static void DeleteBookmain(){

        Scanner scanner = new Scanner(System.in);

        System.out.print(" enter isbn code : ");
        int isbn = scanner.nextInt();
        Book book = new Book();
        // Set the book properties
        book.setIsbn(isbn);
        book.deleteBook();



    }
}
