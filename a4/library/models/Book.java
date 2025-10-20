package library.models;

import java.util.Scanner;
import library.users.User;
import utils.Input;

public class Book extends LibraryItem {

    String author, genre;
    int page_count, publish_year;
    
    public Book(
        String id,
        String title, 
        int total_quantity, 
        int avl_quantity,
        int page_count,
        String author,
        String genre,
        int publish_year
    ) {
        super(id, title, LibraryItem.Type.BOOK, 10.0f, total_quantity, avl_quantity);
        this.author = author;
        this.genre = genre;
        this.page_count = page_count;
        this.publish_year = publish_year;
    }

    public void inputDetails(){
        Scanner sc = Input.getScannerObject();
        
    }

    
    public Type getItemType() {
        return this.type;
    }

    public float getLateFee(User user, int daysExceeded) {
        return daysExceeded * late_fee_factor;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", genre=" + genre + ", page_count=" + page_count + ", publish_year="
                + publish_year + ", id=" + id + ", title=" + title + ", type=" + type + ", total_quantity="
                + total_quantity + ", avl_quantity=" + avl_quantity + "]";
    }


}
