package library.transactions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import library.models.LibraryItem;
import library.users.User;

public class Transaction {

    private static long t_no = 0;

    public enum StatusCode {
        S200, //Success
        E100, // 
        E101,
        E102,
        E103
    }
    
    public enum TransactionType{
        BORROW,
        RETURN
    };
    
    static ArrayList<Transaction> transactions = new ArrayList<>();

    private TransactionType _type;
    StatusCode statusCode;
    long _id;
    User user;
    LibraryItem item;
    LocalDate issueDate, dueDate, returnDate;
    boolean isReturned;
    
    public Transaction(TransactionType type, User user, LibraryItem item) {
        _id = ++t_no;
        _type = type;
        issueDate = LocalDate.now();
        dueDate = issueDate.plus((long)user.getBorrowingPeriod(), ChronoUnit.DAYS);      //plus(issueDate, user.getBorrowingPeriod()));
        this.user = user;
        this.item = item;
        isReturned = false;
    }
    
    public Transaction(TransactionType type, User user, LibraryItem item, LocalDate issueDate) {
        _id = ++t_no;
        _type = type;
        this.issueDate = issueDate;
        dueDate = issueDate.plus((long)user.getBorrowingPeriod(), ChronoUnit.DAYS);      //plus(issueDate, user.getBorrowingPeriod()));
        this.user = user;
        this.item = item;
        isReturned = false;
    }

    public long get_id() {
        return _id;
    }
    public User getUser() {
        return user;
    }
    public LibraryItem getItem() {
        return item;
    }
    public LocalDate getDateIssued() {
        return issueDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    public void borrowItem(LibraryItem item, User user) {
        if (user.getBorrowedItems().size() == user.getMaxBorrowLimit()) 
            System.out.println("Maximum borrow limit reached for items. " + item.getItemType() + " cannot be issued!");
        
        else {
            if(item.getAvailableQuantity() == 0) {
                System.out.println("Requested item is currently unavailable!");
                return;
            }

            
        }
    }

    public void returnItem(LibraryItem item, User user) {
        Transaction t = this;
        t.returnDate = LocalDate.now();
        user.returnItem(item);

    }

    public static ArrayList<Transaction> getTransactions() { return transactions; }

    public String displayStatus(StatusCode statusCode) {
        if (statusCode == StatusCode.S200) 
            return "Transaction Successful..!";
        else
            return "Transaction Failed" ;
    }

    public float calculateFine(int daysBorrowed) {
        return item.getLateFee(user, daysBorrowed);
    }

    public String getTransactionType() {
        if (_type == TransactionType.BORROW) 
            return "Borrow";
        else
            return "Return";
    }

    public String toString() {
        return (
            "Transaction ID: " + _id + 
            "\nTransaction Type: " + getTransactionType() +
            "\nUser: " + user + 
            "\nItem: " + item +
            "\nIssue Date: " + issueDate +
            "\nReturn Date: " + returnDate
            );
    }
}
