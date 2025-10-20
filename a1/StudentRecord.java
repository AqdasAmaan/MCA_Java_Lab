import java.math.BigInteger;
import java.util.Scanner;
import java.util.ArrayList;

class StudentRecord {
    static BigInteger[] roll_no;
    static String[] name;
    static int[][] marks;
    static int[] total;
    static float[] avg;
    static char[] grade;
    static int[] g_dist = new int[] {0, 0, 0, 0, 0}; 
    static ArrayList<Integer> toppers = new ArrayList<>();
    static int n, currentIndex = -1;
    static float max = 0, class_avg = 0 ;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        // Taking user input for the total no. of students until its within the specified range (1-50)
        do {
            System.out.print("Enter the total no. of students: ");
            n = sc.nextInt();
            sc.nextLine();
            
            if (n <= 0 || n > 50)
            System.out.println("Invalid Input(Size limit 1-50. Re-enter)");
        }
        while (n < 1 || n > 50);
        
        init();
        String menu = "\n1. Enter student data. \n2. Display student records \n3. Display class statistics \n4. Exit \nEnter your choice: ";
        
        do {
            System.out.print(menu);
            ch = sc.nextInt();
            sc.nextLine();

            switch(ch) {
                case 1:
                int range;
                    while(true) {
                        System.out.print("How many student records do you want to enter: ");
                        range = sc.nextInt();
                        sc.nextLine();
                        if (range >= 1 && range <= (n - currentIndex)) break;
                        if(currentIndex == -1) 
                            System.out.println("Invalid range(0-50) or insufficient memory(Current no. of records: " + (currentIndex+1) +" of " + n + "). Re-Enter.");
                        else 
                            System.out.println("Invalid range(0-50) or insufficient memory(Current no. of records: " + currentIndex +" of " + n + "). Re-Enter.");
                    }

                    input(sc, range);
                    break;
                
                case 2:
                    displayStudentRecords();
                    break;
                
                case 3:
                    displayClassStats();
                    break;
                
                case 4:
                    System.out.println("Exit.");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(ch != 4);



        sc.close();
    }
    
    public static void init() {
        roll_no = new BigInteger[n];
        name = new String[n];
        marks = new int[n][3];
        total = new int[n];
        avg = new float[n];
        grade = new char[n];
    }
    
    public static void input(Scanner sc, int range) {
        
        if (currentIndex == -1)
            currentIndex = 0;

        range = range + currentIndex;
        for (int i=currentIndex; i<range; i++) {
            System.out.println("\nEnter data for Student " + (i+1) + "-->");

            System.out.print("Roll No: ");
            roll_no[i] = new BigInteger(sc.nextLine().trim());

            System.out.print("Name: ");
            name[i] = sc.nextLine();

            System.out.println("Marks Obtained -->");
            for (int j=0; j<3; j++) {
                int x;
                while(true){
                    System.out.print("Subject " + (j+1) + ": ");
                    x = sc.nextInt();
                    if (x >= 0 && x <=100) break;
                    else
                        System.out.println("Marks out of range(0-100)! Re-Enter.");
                }
                marks[i][j] = x;
            }

            sc.nextLine();
        }

        calcTAG(currentIndex, range);
        currentIndex = range;
        
        //sc.close();
    }

    public static void calcTAG(int start, int end) {
        System.out.println("Start index: " + start + " \tEnd index: " + end);
        for (int i=start; i<end; i++) {
            total[i] = marks[i][0] + marks[i][1] + marks[i][2] ;
            avg[i] = total[i] / 3.0f;
            grade[i] = assign_grade(avg[i]);
        }
    }

    public static char assign_grade(float x) {
        if (x >= 90) {
            g_dist[0]++;
            return 'A';
        }
           
        else if (x >= 80 && x < 90) {
            g_dist[1]++;
            return 'B';
        }

        else if (x >= 70 && x < 80) {
            g_dist[2]++;            
            return 'C';
        }
        else if (x >= 60 && x < 70) {
            g_dist[3]++;
            return 'D';
        }
        else {
            g_dist[4]++;            
            return 'F';
        }
    }



    public static void calcClassStats() {
        class_avg = 0;
        toppers.clear();

        for (int i=0; i<currentIndex; i++) {
            class_avg += avg[i];

            if (avg[i] > max) {
                toppers.clear();
                max = avg[i];
                toppers.add(i);
            }
            else if (avg[i] == max) {
                toppers.add(i);
            }
        }

        class_avg /= n;
    }

    public static void displayStudentRecords() {
        if (currentIndex == -1) {
            System.out.println("No record found!");
            return;
        }

        System.out.printf("%20s  %15s  %10s     %6s   %8s  %5s\n", "Roll Number", "Name", "Marks", "Total", "Average", "Grade");

        for (int i=0; i<currentIndex; i++) {
            System.out.printf("%20s  %15s   %3d %3d %3d  %6d  %9.2f  %5c\n", roll_no[i],name[i], marks[i][0], marks[i][1], marks[i][2], total[i], avg[i], grade[i]);
        }
    }

    public static void displayClassStats() {
        if (currentIndex == -1) {
            System.out.println("No record found!");
            return;
        }

        calcClassStats();
        System.out.println("\n\nClass Statistics ------------------>\n");
        System.out.printf("Class Average: %4.2f", class_avg);
        System.out.println("\nGrade Distribution -->");
        System.out.println("A: " + g_dist[0] + "\tB: " + g_dist[1] + "\tC: " + g_dist[2] + "\tD: " + g_dist[3] + "\tF: " + g_dist[4]);
        System.out.print("\nTopper(s): ");
        for (int i: toppers)
            System.out.print(name[i] + "\t");
        System.out.println();
    }

}
