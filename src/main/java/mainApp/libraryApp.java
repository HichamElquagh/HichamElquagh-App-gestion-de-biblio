package mainApp;

import Class.Book; // Import the Book class
import Class.Author;
import Class.Brrower;
import Class.Brrowed;

import java.io.PrintStream;
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
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Display All Books");
            System.out.println("7. Borrow a Book");
            System.out.println("8. Retrieve a Book");
            System.out.println("9. Exit");
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
                    SearchByTitlemain();
                    break;
                case 5:

                    break;
                case 6:
                    DisplayAllboksmain();
                    break;
                case 7:
                    BrrowAbookmain();
                    break;
                case 8:
                    RetrieveAbookmain();
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

        Author author1 = new Author();
        author1.setName(author);
        author1.authorBookid();
        // Create a Book object
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthorId(author1);
        book.setAvailableQuantity(available);
        book.setTotalQuantity(total);
        book.setMissingQuantity(0);
        book.addBook();
// for adding author







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
    public static void SearchByTitlemain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Search By title: ");
        // Consume the newline character
        String Title = scanner.nextLine();
        Book book = new Book();
        // Set the book properties
        book.searchBookByTitle(Title);

    }
    public static void DisplayAllboksmain(){
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        // Set the book properties
        book.displayAvailableBooks();

    }

    public static void BrrowAbookmain(){
        Scanner scanner = new Scanner(System.in);
        Brrower brrower = new Brrower();
        System.out.println(" ----------- record the information of the Brrower ----------- ");
        System.out.println(" Please Enter CIN for brrower : ");
        String  cin_brrower = scanner.nextLine();
        brrower.setCin(cin_brrower);
        int  ifone = brrower.checkCinBrrower();
        if(ifone == 1){
            Brrowed brrowed = new Brrowed();
            brrowed.setBrrower_id(brrower);
            brrowed.borrowBook();
            }
        else {

            System.out.println(" Please Enter Full name for brrower : ");
            String  brrower_name = scanner.nextLine();
            brrower.setName_empr(brrower_name);
            brrower.register();
            brrower.getId();
            Brrowed brrowed = new Brrowed();
            brrowed.setBrrower_id(brrower);
            brrowed.borrowBook();



        }

    }
    public static void RetrieveAbookmain(){

        Brrowed brrowed = new Brrowed();
        brrowed.RetrieveAbook();

    }
}
