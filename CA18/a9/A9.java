import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

class BookNotAvailableException extends Exception {
	BookNotAvailableException() {
		super("Book Out Of Stock!");
	}
}

class Book {
	String id, title;
	int totalQuantity, avlQuantity;
	
	static ArrayList<Book> books = new ArrayList<>();
	
	Book(String book_id, String title, int quantity) {
		this.id = book_id;
		this.title = title;
		totalQuantity = quantity;
		avlQuantity = quantity;
		addBook();
	}
	
	Book() {
		id = "N/A";
		title = "N/A";
	}

	private void addBook() {
		books.add(this);
	}
	
	public String toString() {
		return ("Book ID: " + id + "Title: " + title);
	}
}

class Student {
	long id;
	String name;
	int age;
	Book[] issuedBooks;
	
	static ArrayList<Student> students = new ArrayList<>();
	
	Student(long id, String name, int age) {
		this.id = id; 
		this.name = name;
		this.age = age;
		issuedBooks = new Book[5];
		addStudent();
	}
	
	Student() {
		id = -1;
		name = "N/A";
		age = 1;
	}

	private void addStudent() {
		students.add(this);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: ").append(name).append("\n");
		sb.append("ID: ").append(id).append("\n");
		sb.append("Issued Books: \n\t");
		
		for (Book b : issuedBooks) 
			sb.append(b).append("\n\t");
		sb.append("\n");

		return sb.toString();
	}
}

public class A9 {
	final static Logger log = Logger.getLogger(A9.class.getSimpleName());
	
	static {
		try {
			LogManager.getLogManager().reset();
			FileHandler file = new FileHandler("library.log", true);
			file.setFormatter(new SimpleFormatter());
			log.addHandler(file);
			log.setLevel(Level.ALL);
		}
		catch (Exception e) {
			System.err.println("Error While Initializing Logger!");
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A9 library = new A9();
		System.out.println("Welcome to the Library Management System");

		while (true) {
			System.out.println("1. Add Book\n2. Add Student\n3. Issue Book\n4. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine(); // consume newline
			
			switch (choice) {
				case 1:
					System.out.print("Enter Book ID: ");
					String book_id = sc.nextLine();
					System.out.print("Enter Book Title: ");
					String title = sc.nextLine();
					System.out.print("Enter Quantity: ");
					int quantity = sc.nextInt();
					new Book(book_id, title, quantity);
					log.log(Level.INFO, "Book added: {0}", book_id);
					System.out.println("[Debug Log] Book added: " + book_id);
					break;

				case 2:
					System.out.print("Enter Student ID: ");
					long student_id = sc.nextLong();
					sc.nextLine(); // consume newline
					System.out.print("Enter Student Name: ");
					String name = sc.nextLine();
					System.out.print("Enter Student Age: ");
					int age = sc.nextInt();
					new Student(student_id, name, age);
					log.log(Level.INFO, "Student added: {0}", student_id);
					System.out.println("[Debug Log] Student added: " + student_id);
					break;

				case 3:
					System.out.print("Enter Book ID to issue: ");
					String b_id = sc.nextLine();
					System.out.print("Enter Student ID: ");
					long s_id = sc.nextLong();
					library.issueBook(b_id, s_id);
					break;
					
				case 4:
					System.out.println("Exiting...");
					sc.close();
					return;

				case 5:
					// For debugging: Print all students and their issued books
					System.out.println("----- Students and Issued Books -----");
					for (Student s : Student.students) {
						System.out.println(s);
					}
					break;

				case 6:
					// For debugging: Print all books in the library
					System.out.println("----- Books in Library -----");
					for (Book b : Book.books) {
						System.out.println(b);
					}
					break;
				
				case 7:
					// For debugging: Trigger an assertion error
					System.out.println("Triggering assertion error for testing...");
					Student testStudent = new Student(9999, null, 15);
					library.issueBook("nonexistent_book", 9999);
					break;

				case 8:
					// For debugging: Trigger BookNotAvailableException
					System.out.println("Triggering BookNotAvailableException for testing...");
					new Book("B999", "Test Book", 0);
					new Student(8888, "Test Student", 16);
					library.issueBook("B999", 8888);
					break;

				case 9:
					// For debugging: Trigger maximum issued books warning
					System.out.println("Triggering maximum issued books warning for testing...");
					Student maxBooksStudent = new Student(7777, "Max Books Student", 20);
					for (int i = 0; i < 5; i++) {
						Book tempBook = new Book("B" + (100 + i), "Book " + i, 1);
						library.issueBook(tempBook.id, 7777);
					}
					// Attempt to issue one more book
					Book extraBook = new Book("B200", "Extra Book", 1);
					library.issueBook(extraBook.id, 7777);
					break;

				case 10:
					// For debugging: Trigger null name exception
					System.out.println("Triggering null name exception for testing...");
					Student nullNameStudent = new Student(6666, null, 18);
					library.issueBook("some_book", 6666);
					break;

				case 0:
					// For debugging: Log File Content
					try {
						java.nio.file.Path logPath = java.nio.file.Paths.get("library.log");
						System.out.println("----- Log File Content -----");
						java.nio.file.Files.lines(logPath).forEach(System.out::println);
					} catch (Exception e) {
						System.out.println("Error reading log file: " + e.getMessage());
					}
					break;

				default:
					System.out.println("Invalid choice! Please try again.");
			}
		}

		
	}
	
	void issueBook(String book_id, long student_id) {
		Book book = null;
		for (Book b : Book.books)
			if (b.id == book_id) book = b;
		
		Student student = null;
		for (Student s : Student.students) {
			if (s.id == student_id) student = s; 
		}

		try {
			if (book == null)
				throw new NullPointerException("Book not found!");
		} catch (Exception e) {
			log.log(Level.SEVERE, "Book ID : '{'{0}'}' not found !!!", book_id);
			System.out.println("[Debug Log] Book ID : " + book_id + " not found !!!");
			return;
		}
		
		try {
			if (student == null)
				throw new NullPointerException("Student not found!");
			if (student.name == null) 
				throw new NullPointerException("Name cannot be null!");
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, "Student Name : null !!!");
			System.out.println("[Debug Log] Student's name : null");
		}
		
		assert book.avlQuantity > 0 : "No copy available for the book!";
		assert student != null && student.age > 12 : "No book can be issued to students under the age of 12!";
		
		if (student.issuedBooks.length >= 5) {
			log.warning("Student has already issued maximum number of books!");
			System.out.println("[Debug Log] Student has already issued maximum number of books!");
			return;
		}
		try {
			if (book.avlQuantity <= 0) 
				throw new BookNotAvailableException();
			
			// Issue Book
			for (int i = 0; i < student.issuedBooks.length; i++) {
				if (student.issuedBooks[i] == null) {
					student.issuedBooks[i] = book;
					book.avlQuantity--;
					log.log(Level.INFO, "Book issued successfully to student ID: {0}", student_id);
					System.out.println("[Debug Log] Book issued successfully to student ID: " + student_id);
					return;
				}
			}
		} catch (BookNotAvailableException e) {
			log.severe(e.getMessage());
			System.out.println("[Debug Log] " + e.getMessage());
		}
	}

}