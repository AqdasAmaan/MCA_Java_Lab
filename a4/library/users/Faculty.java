package library.users;

import library.models.LibraryItem;

public class Faculty extends User {
    
    long emp_id;
    String department;

    public Faculty(String name, String email, Role role, long emp_id, String department) {
        super(name, email, role, 5, 14);
        this.emp_id = emp_id;
        this.department = department;
    }
    
    public int getMaxBorrowLimit() {
        return this.borrow_limit;
    }

}
