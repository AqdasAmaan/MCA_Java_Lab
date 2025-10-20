package library.models;

import library.users.User;

public class DVD extends LibraryItem {
    
    long duration;
    String language;

    public DVD(
        String id,
        String title, 
        int total_quantity, 
        int avl_quantity,
        long duration,
        String language
    ) {
        super(id, title, LibraryItem.Type.BOOK, 10.0f, total_quantity, avl_quantity);
        this. duration = duration;
        this.language = language;
    }

    public Type getItemType() {
        return this.type;
    }

    public float getLateFee(User user, int days_borrowed) {
        return 0.0f;
    }

    @Override
    public String toString() {
        return "DVD [duration=" + duration + ", language=" + language + ", id=" + id + ", title=" + title + ", type="
                + type + ", late_fee_factor=" + late_fee_factor + ", total_quantity=" + total_quantity
                + ", avl_quantity=" + avl_quantity + "]";
    }
}
