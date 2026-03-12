import java.util.Scanner;

public class A8 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== STACK BASED PROGRAMS MENU ======");
            System.out.println("1. Decimal to Binary");
            System.out.println("2. Factorial");
            System.out.println("3. Fibonacci");
            System.out.println("4. GCD");
            System.out.println("5. Rat In Maze");
            System.out.println("6. Tower of Hanoi");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter a decimal number: ");
                    int num = sc.nextInt();
                    System.out.println("Binary Equivalent of " + num + ": " + DecimalToBinary.toBinary(num));
                }

                case 2 -> {
                    System.out.print("Enter a number: ");
                    int factNum = sc.nextInt();
                    int result = Factorial.factorial(factNum);
                    System.out.println("Factorial = " + result);
                }

                case 3 -> {
                    System.out.print("Enter n: ");
                    int fibNum = sc.nextInt();
                    System.out.println("Fib(" + fibNum + "): " + Fibonacci.fib2(fibNum));
                }

                case 4 -> {
                    System.out.print("Enter first number: ");
                    int a = sc.nextInt();
                    System.out.print("Enter second number: ");
                    int b = sc.nextInt();
                    int gcd = GCD.gcd(a, b);
                    System.out.println("GCD = " + gcd);
                }

                case 5 -> {
                    int[][] maze = {
                        {1, 0, 0, 0},
                        {1, 1, 0, 1},
                        {0, 1, 0, 0},
                        {1, 1, 1, 1}
                    };
                    RatInMaze.solveMaze(maze);
                }

                case 6 -> {
                    System.out.print("Enter number of disks: ");
                    int disks = sc.nextInt();
                    TowerOfHanoi.hanoi(disks, 'A', 'B', 'C');
                }

                case 0 -> System.out.println("Terminated");

                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}