//package a13;

import java.sql.*;
import java.util.Scanner;

public class StudentDBViewer {

    // SQLite DB file stored locally (automatically created if missing)
    private static final String URL = "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?user=postgres.xahovmpkpyvecfkrthtd&password=jdbcdemo";

    private static Connection conn;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connect();
            createTableIfNotExists();
            menu();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // Connect to SQLite
    private static void connect() throws Exception {
        conn = DriverManager.getConnection(URL);
        System.out.println("Connected to SQLite database.\n");
    }

    // Close connection
    private static void close() {
        try {
            if (conn != null) conn.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create students table if missing
    private static void createTableIfNotExists() throws Exception {
        String sql = """
        CREATE TABLE IF NOT EXISTS students (
            id SERIAL PRIMARY KEY ,
            name TEXT,
            age INTEGER
        );
        """;

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private static void menu() throws Exception {
        while (true) {
            System.out.println("\n===== STUDENT DATABASE VIEWER =====");
            System.out.println("1. List all students");
            System.out.println("2. Add a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Run full demonstrations");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> listStudents();
                case 2 -> addStudent();
                case 3 -> deleteStudent();
                case 4 -> runDemo();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // List all students
    private static void listStudents() throws Exception {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Students ---");
        while (rs.next()) {
            System.out.printf("%d | %s | %d\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age")
            );
        }

        rs.close();
        stmt.close();
    }

    // Add student
    private static void addStudent() throws Exception {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();

        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.executeUpdate();

        System.out.println("Student added!");
    }

    // Delete student by ID
    private static void deleteStudent() throws Exception {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Student deleted!");
    }

    private static void runDemo() throws Exception {
        System.out.println("\n--- Running Demo ---");

        System.out.println("1. Listing students:");
        listStudents();

        System.out.println("\n2. Adding sample student: (Demo Student, 20)");
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO students(name, age) VALUES ('Demo Student', 20)"
        );
        ps.executeUpdate();

        System.out.println("3. Listing after insert:");
        listStudents();

        System.out.println("\n4. Deleting sample student...");
        Statement st = conn.createStatement();
        st.executeUpdate("DELETE FROM students WHERE name='Demo Student'");

        System.out.println("5. Listing after delete:");
        listStudents();

        System.out.println("\n--- Demo Complete ---\n");
    }
}
