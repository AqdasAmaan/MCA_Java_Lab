import java.lang.reflect.*;

public class StudentInspector {

    public static void main(String[] args) {
        runAllDemonstrations();
    }

    public static void runAllDemonstrations() {
        System.out.println("========= STUDENT MANAGEMENT SYSTEM DEMONSTRATION =========\n");

        demonstrateWrapperAndAutoboxing();
        demonstrateVarargs();
        demonstrateEnums();
        demonstrateReflection();
    }


    static void demonstrateWrapperAndAutoboxing() {
        System.out.println("1. Wrapper Classes & Autoboxing Demo\n");

        Student s1 = new Student(1, 8.5, true, 450);
        Student s2 = new Student(2, 9.0, false, 480);

        System.out.println("Comparing total marks (Autoboxing Demo): " + s1.compareTo(s2));

        // == vs equals()
        int a = 100, b = 100;
        Integer x = 100, y = 100, p = 200, q = 200;

        System.out.println("Primitive == : " + (a == b));
        System.out.println("Wrapper == (cached range): " + (x == y));
        System.out.println("Wrapper == (outside cache): " + (p == q));
        System.out.println("Wrapper equals(): " + p.equals(q));

        System.out.println("\n---------------------------------------\n");
    }

    static void demonstrateVarargs() {
        System.out.println("2. Varargs Demo\n");

        Student s = new Student();
        Double[] marks = {85.0, 90.0, 95.0};
        s.calculateAggregateMarks("Aqdas", marks);

        Student s1 = new Student(1, 8.5, true, 400);
        Student s2 = new Student(2, 9.0, false, 450);
        s.addMultipleStudents(s1, s2);

        System.out.println("Total Students Registered: " + Student.students.size());

        System.out.println("\n---------------------------------------\n");
    }

    static void demonstrateEnums() {
        System.out.println("3. Enum Demo\n");

        Student.Grade grade = Student.Grade.fromMarks(85);
        System.out.println("Grade from marks: " + grade + " | Points: " + grade.getGradePoint());

        Student.Department dept = Student.Department.CSE;
        switch (dept) {
            case CSE:
                System.out.println("Department: " + dept + 
                                   " | Code: " + dept.getDepartmentCode() +
                                   " | HOD: " + dept.getHOD());
                break;
            default:
                System.out.println("Other Department");
        }

        System.out.println("\n---------------------------------------\n");
    }

    static void demonstrateReflection() {
        System.out.println("4. Reflection API Demo\n");

        Student st = new Student(3, 9.5, true, 490);
        printObjectDetails(st);
        identifyWrapperFields(st);
        invokeGetters(st);

        System.out.println("\n---------------------------------------\n");
    }

    // 🔍 Reflection helper methods
    public static void printObjectDetails(Object obj) {
        Class c = obj.getClass();
        System.out.println("Class: " + c.getName());

        System.out.println("\nConstructors:");
        for (Constructor constructor : c.getConstructors())
            System.out.println("\t" + constructor);

        System.out.println("\nFields:");
        for (Field field : c.getDeclaredFields())
            System.out.println("\t" + field);

        System.out.println("\nMethods:");
        for (Method method : c.getDeclaredMethods())
            System.out.println("\t" + method);
    }

    public static void identifyWrapperFields(Object obj) {
        System.out.println("\nWrapper Fields:");
        for (Field f : obj.getClass().getDeclaredFields()) {
            Class<?> type = f.getType();
            if (type.equals(Integer.class) || type.equals(Double.class) || type.equals(Boolean.class))
                System.out.println("\t" + f.getName() + " → " + type.getSimpleName());
        }
    }

    public static void invokeGetters(Object obj) {
        System.out.println("\nInvoking Getter Methods Dynamically:");
        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.getName().startsWith("get") && m.getParameterCount() == 0 ) {
                Object value = null;
                try {
                    value = m.invoke(obj);
                } catch (IllegalAccessException ex) {
                } catch (InvocationTargetException ex) {
                }
                System.out.println("\t" + m.getName() + "(): " + (value != null ? value : "null"));
            }
        }
    }
}
