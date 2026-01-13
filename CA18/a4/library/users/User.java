package library.users;

import java.util.ArrayList;
import library.models.LibraryItem;

public abstract class User {

    
    public enum Role {
        STUDENT,
        FACULTY
    };
    
    private static ArrayList<User> users = new ArrayList<>();
    
    long _id;
    String name, email;
    Role role;
    int borrow_limit, borrowing_period;
    
    ArrayList<LibraryItem> borrowedItems = new ArrayList<>();
    ArrayList<LibraryItem> borrowedItemsHistory = new ArrayList<>();

    
    User(String name, String email, Role role, int borrow_limit, int borrowing_period) {
        users.add(this);
        this.name = name;
        this.email = email;
        this.role = role;
        this.borrow_limit = borrow_limit;
        this.borrowing_period = borrowing_period; 
    }
    
    public abstract int getMaxBorrowLimit();
    
    public ArrayList<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }
    
    public ArrayList<LibraryItem> getBorrowedItemHistory() {
        return borrowedItemsHistory;
    }

    public long getId() { return _id; }
    
    public Role getRole() { return role; }
    
    public int getBorrowingPeriod() { return borrowing_period; }
    
    public ArrayList<LibraryItem> getBorrowedItemsHistory() {
        return borrowedItemsHistory;
    }
    
    public void borrowItem(LibraryItem item) {
        if(item.isAvailable()){
            borrowedItems.add(item);
            borrowedItemsHistory.add(item);
            item.borrowItem();
        }
        else{
            System.out.println("Item Unavailable!");
        }
        
    }
    
    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
        item.returnItem();
    }
    
    public void viewBorrowedItems() {
        System.out.println("\n\n-------Borrowed Items-------");
        for (LibraryItem item : borrowedItems) {
            System.out.println(item);
        }
    }
    
    public void viewBorrowHistory() {
        System.out.println("\n\n-------Borrowed Item History-------");
        for (LibraryItem item : borrowedItemsHistory) {
            System.out.println(item);
        }
    }
    
    public static ArrayList<User> getUsers() { return users; }
    
}
