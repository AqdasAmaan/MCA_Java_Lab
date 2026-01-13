package library.models;

import java.time.Month;
import library.users.User;

public class Magazine extends LibraryItem {
    
    // public enum Frequency {
    //     WEEKLY('W'),
    //     BI_WEEKLY,
    //     MONTHLY,
    //     QUATERLY,
    //     HALF_YEARLY,
    //     YEARLY;

    //     Frequency(char c) {
            
    //     }
    // }
    int issueNumber;
    
    Month month; 

    String frequency; //could also use an enum
    
    @Override
    public String toString() {
        return "Magazine [id=" + id + ", title=" + title + ", issueNumber=" + issueNumber + ", month=" + month + ", frequency=" + frequency + ", total_quantity=" + total_quantity + ", avl_quantity="
                + avl_quantity + "]";
    }

    public Magazine(
        String id,
        String title, 
        int total_quantity, 
        int avl_quantity,
        int issueNumber, 
        Month month, 
        String frequency
    ) {
        super(id, title, LibraryItem.Type.MAGAZINE, 5.0f, total_quantity, avl_quantity);
        this.issueNumber = issueNumber;
        this.month = month;
        this.frequency = frequency;
    }
    
    public Type getItemType() {
        return this.type;
    }
    
    public float getLateFee(User user, int days_borrowed) {
        return 0.0f;
    }
    
    public Month getMonth() {
        return month;
    }
}
