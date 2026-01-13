package library.management;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import library.models.Book;
import library.models.DVD;
import library.models.LibraryItem;
import library.models.Magazine;
import library.transactions.Transaction;
import library.users.Faculty;
import library.users.Student;
import library.users.User;
import utils.Input;

public class Management {
    
    static Scanner sc = Input.getScannerObject();

    public static ArrayList<LibraryItem> searchItemByTitle(String title) {
        ArrayList<LibraryItem> filteredItems = new ArrayList<>() ;
        for (LibraryItem item : LibraryItem.getItems()) {
            if(item.getTitle().equals(title)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public static ArrayList<LibraryItem> searchItemById(String id) {
        ArrayList<LibraryItem> filteredItems = new ArrayList<>() ;
        for (LibraryItem item : LibraryItem.getItems()) {
            if (item.getID().equals(id))
            {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
    public static ArrayList<LibraryItem> searchItemByType(LibraryItem.Type type) {
        ArrayList<LibraryItem> filteredItems = new ArrayList<>() ;
        for (LibraryItem item : LibraryItem.getItems()) {
            if (item.getItemType() == type)
            {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public static void addItem() {
        String title, id;
        int total_quantity, avl_quantity; 
        System.out.print("Press the respective key for adding the respective item---> \n1 for Book \n2 for Magazine \n3 for DVD \n.Your choice : ");
        int itemTypeNo = sc.nextInt();
        sc.nextLine();

        if (!(itemTypeNo >= 1 && itemTypeNo <= 3)) {
            System.out.println("Not a valid option...!");
            return;
        }

        System.out.println("Enter Item Details--->");
        // Read values for shared attributes...

        System.out.print("Item ID: ");
        id = sc.nextLine();

        System.out.print("Title: ");
        title = sc.nextLine();

        System.out.print("Quantity / No. of copies : ");
        total_quantity = avl_quantity = sc.nextInt();
        sc.nextLine();

        switch (itemTypeNo) {
            case 1:
                // For Books
                System.out.print("Author: ");
                String author = sc.nextLine();

                System.out.print("Genre: ");
                String genre = sc.nextLine();

                System.out.print("Page Count: ");
                int page_count = sc.nextInt();

                System.out.print("Publish Year: ");
                int publish_year = sc.nextInt();
                sc.nextLine();

                new Book(id, title, total_quantity, avl_quantity, page_count, author, genre, publish_year);
                break;

            case 2:
                // For Magazines
                System.out.print("Issue Number: ");
                int issueNumber = sc.nextInt();

                System.out.print("Month(1-12): ");
                Month month = Month.of(sc.nextInt());
                sc.nextLine();

                System.out.print("Frequency: ");
                String frequency = sc.nextLine();

                new Magazine(id, title, total_quantity, avl_quantity, issueNumber, month, frequency);
                break;

            case 3:
                // For DVDs
                System.out.print("Duration (in minutes): ");
                int duration = sc.nextInt();
                sc.nextLine();

                System.out.print("Language: ");
                String language = sc.nextLine();

                new DVD(id, title, total_quantity, avl_quantity, duration, language);
                break;

            }
    }



    public static void removeItem() {
        Scanner sc = Input.getScannerObject();
        System.out.print("Enter the item id for the item to be removed: ");
        String id = sc.nextLine();
        for (LibraryItem item : LibraryItem.getItems()){
            if(item.getID().equals(id)){
                System.out.println("Removed Item---> \n" + item);
                LibraryItem.getItems().remove(item);
                return;
            }

        }
        System.out.println("No item with the id (" + id +") found...!");
    }

    public static void registerUser() {
        String name, email;
        User.Role role;
        int borrow_limit, borrowing_period;
        int errorCount = 0;

        System.out.println("Enter user details--->");
        do {
            System.out.print("Select Role---> \n1 for Faculty \n2 for Student \nRole : ");
            int r = sc.nextInt();
            sc.nextLine();

            if (r != 1 || r != 2) {
                System.out.print("Not a valid input. Retry!");
                
                if (++errorCount == 3){
                    System.out.println("Maximum no. of tries reached! Terminating...");
                    return;
                }
            }

            else {
                role = r == 1 ? User.Role.FACULTY : User.Role.STUDENT;
                break;
            }

        } while(true);
        
        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Email: ");
        email = sc.nextLine();

        if (role == User.Role.FACULTY) {
            System.out.print("Employee ID: ");
            long emp_id = sc.nextLong();
            sc.nextLine();

            System.out.print("Department: ");
            String department = sc.nextLine();

            new Faculty(name, email, role, emp_id, department);
            return;
        }

        else if (role == User.Role.STUDENT) {
            System.out.print("Student ID: ");
            long studentID = sc.nextLong();
            sc.nextLine();

            System.out.print("Course: ");
            String course = sc.nextLine();

            new Student(name, email, role, studentID, course);
            return;
        }

        //Raise an error if control reaches here...(to be implemented)
    }

    public static void removeUser() {
        long id;
        boolean success = false;
        System.out.print("Enter User Id: ");
        id = sc.nextLong();

        for (User user : User.getUsers()) {
            if (user.getId() == id) {
                System.out.println("User with user_id (" + id + ") removed. ");
                User.getUsers().remove(user);
                success = true;
            }
        }

        if (!success) {
            System.out.println("No user with the provided id found!");
        }

    }

    public static void issueItem() {
        User user = null;
        LibraryItem item = null;

        System.out.print("Enter User Id: ");
        long user_id = sc.nextLong();
        sc.nextLine();
        
        // Find User
        for (User u : User.getUsers()){
            if (u.getId() == user_id) {
                user = u;
            }
        }
        
        if(user == null){
            System.out.println("No User found with the provided id!");
            return;
        }

        
        System.out.print("\nEnter Item Id: ");
        String item_id = sc.nextLine();

        // Find Item
        for (LibraryItem li : LibraryItem.getItems()) {
            if (li.getID().equals(item_id)) {
                item = li;
            }
        }

        if (item == null) {
            System.out.println("No Item with the provided id found!");
            return;
        }

        new Transaction(Transaction.TransactionType.BORROW, user, item);

    }

    public static void returnItem() {

    }
    
    public static void searchItems() {
        String searchMenu = """
                ==============Search Menu============
                1. Search By Title
                2. Search By Id
                3. Search By Type

                Option(1/2/3):  
                """;
        
        int errorCount = 0, r ;

        do {
            System.out.print(searchMenu);
            r = sc.nextInt();
            sc.nextLine();

            if (r != 1 || r != 2 || r != 3) {
                System.out.println("Not a valid input. Retry!");
                
                if (++errorCount == 3){
                    System.out.println("Maximum no. of tries reached! Redirecting to Main Menu...");
                    return;
                }
            }

            break;

        } while(true);

        ArrayList<LibraryItem> filteredItems = new ArrayList<>();

        switch(r) {
            case 1:
                System.out.println("\n----------Search By Title------------");
                
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                filteredItems = searchItemByTitle(title);

                if(filteredItems.isEmpty()) {
                    System.out.println("No item with the provided title found in record!");
                    return;
                }

                System.out.println("\n-------------------Item(s) found---------------\n");
                for (LibraryItem item: filteredItems){
                    System.out.println(item);
                }

                break;

            case 2:
                System.out.println("\n----------Search By ID------------");
                
                System.out.print("Enter Item Id: ");
                String id = sc.nextLine();
                filteredItems = searchItemById(id);

                if(filteredItems.isEmpty()) {
                    System.out.println("No item with the provided id found in record!");
                    return;
                }

                System.out.println("\n-------------------Item(s) found---------------\n");
                for (LibraryItem item: filteredItems){
                    System.out.println(item);
                }

                break;

            case 3:
                System.out.println("\n----------Search By Type------------");
                
                System.out.print("Press the respective key for adding the respective item---> \n1 for Book \n2 for Magazine \n3 for DVD \n.Your choice : ");
                int itemTypeNo = sc.nextInt();
                sc.nextLine();

                LibraryItem.Type type;

                switch (itemTypeNo) {
                    case 1 :
                        type = LibraryItem.Type.BOOK; 
                        break;

                    case 2 :
                        type = LibraryItem.Type.MAGAZINE;
                        break;

                    case 3 :
                        type = LibraryItem.Type.DVD;
                        break;

                    default :
                        System.out.println("Not a valid option. Redirecting to Main Menu...");
                        return;
                    
                }
                
                filteredItems = searchItemByType(type);

                if(filteredItems.isEmpty()) {
                    System.out.println("No item with the provided id found in record!");
                    return;
                }

                System.out.println("\n-------------------Item(s) found---------------\n");
                for (LibraryItem item: filteredItems){
                    System.out.println(item);
                }

                break;


            
            default:
                System.out.println("Invalid option! Redirecting to Main Menu...");
                return;

        }
        
    }

    public static void viewAllItems() {
        if (LibraryItem.getItems().isEmpty()) {
            System.out.print("No Item Found! Library is Empty. \nDo you wish to add a new item (y/n)? :");
            String s = sc.next().trim().toLowerCase();
            
            if (s.equals("y")) addItem();
            
            return;
        }
        
        System.out.println("\n\n-------------------------------Items----------------------------->");
        for (LibraryItem item : LibraryItem.getItems()){
            System.out.println(item);
        }
    }
    
    public static void viewAllUsers() {
        if (User.getUsers().isEmpty()) {
            System.out.println("No User Found!");
            return;
        }
        
        System.out.println("\n\n-------------------------------Users----------------------------->");
        for (User user : User.getUsers()) {
            System.out.println(user);
        }
    }
    
    public static void viewAllTransactions() {
        if (Transaction.getTransactions().isEmpty()) {
            System.out.println("No Transaction Records Found!");
            return;
        }

        System.out.println("\n\n-------------------------------Transactions----------------------------->");
        for (Transaction transaction : Transaction.getTransactions()) {
            System.out.println(transaction);
        }
    }

    public static void viewOverdueItems() {
        ArrayList<LibraryItem> overdueItems = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        for (Transaction transaction : Transaction.getTransactions()) {
            if (transaction.getDueDate().isAfter(today) && !transaction.getIsReturned())
            overdueItems.add(transaction.getItem());     
        }

        if (overdueItems.isEmpty()){
            System.out.println("No Overdue Items...!");
        }
        
        System.out.println("\n\n-------------------------------Overdue Items----------------------------->");
        for (LibraryItem item : overdueItems) {
            System.out.println(item) ;
        }
    }
}
