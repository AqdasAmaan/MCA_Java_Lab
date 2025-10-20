package library.models;

import java.util.ArrayList;
import library.users.User;

public abstract class LibraryItem {
    public enum Type {
        BOOK,
        DVD,
        MAGAZINE
    }

    static ArrayList<LibraryItem> items = new ArrayList<>();
    String id;
    String title;
    Type type;
    float late_fee_factor;
    int total_quantity, avl_quantity;

    LibraryItem (
        String id,
        String title, 
        Type type,
        float late_fee_factor,
        int total_quantity, 
        int avl_quantity
    ) {
        items.add(this);
        this.id = id;
        this.title = title;
        this.type = type;
        this.avl_quantity = avl_quantity;
        this.total_quantity = total_quantity;
        this.late_fee_factor = late_fee_factor;
    }

    public abstract Type getItemType();

    public abstract float getLateFee(User user, int days_borrowed);

    public static ArrayList<LibraryItem> getItems() { return items; }
    
    public String getID() { return id; }
    public String getTitle() { return title; }
    public int getTotalQuantity() { return this.total_quantity; }
    public int getAvailableQuantity() { return this.avl_quantity; }
    
    public boolean isAvailable() { return avl_quantity != 0; }
    
    public void borrowItem() { avl_quantity--; }
    public void returnItem() { avl_quantity++; }
}
