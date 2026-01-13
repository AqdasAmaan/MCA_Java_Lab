package library.users;

import library.models.LibraryItem;

public class Student extends User {

    long studentID;
    String course;

    public Student(String name, String email, Role role, long studentID, String course) {
        super(name, email, role, 5, 14);
        this.studentID = studentID;
        this.course = course;
    }
    
    public int getMaxBorrowLimit() { return this.borrow_limit; }

}
