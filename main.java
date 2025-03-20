import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Book class
    static class Book {
        private int id;
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void borrowBook() {
            if (isAvailable) {
                isAvailable = false;
                System.out.println("You have successfully borrowed " + title);
            } else {
                System.out.println("The book is currently unavailable.");
            }
        }

        public void returnBook() {
            isAvailable = true;
            System.out.println("You have successfully returned " + title);
        }
    }

    // Library class
    static class Library {
        private List<Book> books;

        public Library() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void displayBooks() {
            for (Book book : books) {
                String availability = book.isAvailable() ? "Available" : "Not Available";
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Status: " + availability);
            }
        }

        public Book getBookById(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
            return null;
        }
    }

    // User class
    static class User {
        private String name;
        private int userId;

        public User(String name, int userId) {
            this.name = name;
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public int getUserId() {
            return userId;
        }

        public void borrowBook(Library library, int bookId) {
            Book book = library.getBookById(bookId);
            if (book != null) {
                book.borrowBook();
            } else {
                System.out.println("Book not found!");
            }
        }

        public void returnBook(Library library, int bookId) {
            Book book = library.getBookById(bookId);
            if (book != null) {
                book.returnBook();
            } else {
                System.out.println("Book not found!");
            }
        }
    }

    // Main class
    public static void main(String[] args) {
        // Create library and add books
        Library library = new Library();
        library.addBook(new Book(1, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addBook(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald"));

        // Create user
        User user = new User("Alice", 101);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Display All Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    user.borrowBook(library, borrowId);
                    break;
                case 3:
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    user.returnBook(library, returnId);
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
