import java.util.ArrayList;

class Student {


    enum Department {
        CSE(101, "HOD101"),
        IT(102, "HOD102"),
        ECE(103, "HOD103"),
        MECH(104, "HOD104");

        private int departmentCode;
        private String hodName;

        private Department(int departmentCode, String hodName) {
            this.departmentCode = departmentCode;
            this.hodName = hodName;
        }

        int getDepartmentCode() {
            return departmentCode;
        }

        String getHOD() {
            return hodName;
        }
    }

    enum Grade {
        O(10),
        A_PLUS(9),
        A(8),
        B_PLUS(7),
        B(6), 
        C(5),
        F(0);

        private int gradePoint;

        Grade(int gradePoint) {
            this.gradePoint = gradePoint;
        }

        int getGradePoint() {
            return gradePoint;
        }

        public static Grade fromMarks(int marks) {
            if (marks >= 90) return O;
            if (marks >= 80) return A_PLUS;
            if (marks >= 70) return A;
            if (marks >= 60) return B_PLUS;
            if (marks >= 50) return B;
            if (marks >= 40) return C;
            return F;
        }
    }

    private Integer rollNo;
    private Double cgpa;
    private Boolean isScholarship;
    private Double totalMarks;

    public static ArrayList<Student> students ;
    
    static {
        students = new ArrayList<>();
    }

    Student (int rollNo, double cgpa, boolean isScholarship, double totalMarks) {
        this.rollNo = rollNo;
        this.cgpa = cgpa;
        this.isScholarship = isScholarship;
        this.totalMarks = totalMarks;
    }

    Student() {

    }

    public Integer getRollNo() {
        return rollNo;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public Boolean getIsScholarship() {
        return isScholarship;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    int compareTo(Student s) {
        return (int)(totalMarks.compareTo(s.totalMarks));
    }

    void calculateAggregateMarks(String studentName, Double ... subjectMarks) {
        double aggregateMarks = 0;
        for (double marks : subjectMarks) {
            aggregateMarks += marks;
        }

        aggregateMarks /= subjectMarks.length;

        System.out.println("Name: " + studentName + "\tAggregate: " + aggregateMarks);
    }

    void addMultipleStudents(Student ... students) {
        for (Student student : students) {
            Student.students.add(student);
        }
    }

    static void checkEquality() {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
    }
}

// class StudentInspector {
//     public static void main(String[] args) {
        
//     }

//     public static void printObjectDetails(Object obj) {
//         Class c = obj.getClass();
//         System.out.println("Class : " + c.getName());
        
//         Constructor[] constructors = c.getConstructors();
//         System.out.println("\nConstructors --->");
//         for (Constructor constructor : constructors) {
//             System.out.println("\t" + constructor);
//         }

//         Field[] fields = c.getDeclaredFields();
//         if (fields.length != 0) {
//             System.out.println("Fields --->");
//             for (Field field : fields) {
//                 System.out.println("\t" + field);
//             }
//         }
//         else
//             System.out.println("No Declared Field Exists!");

//         Method[] methods = c.getDeclaredMethods();
//         if (methods.length != 0) {
//             System.out.println("\nDeclared Methods --->");
//             for (Method method : methods) {
//                 System.out.println("\t" + method);
//             }
//         }
//         else
//             System.out.println("No Declared Methods Exists!");
//     }
// }

