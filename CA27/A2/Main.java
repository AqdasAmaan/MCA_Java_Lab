import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the dimensions of matrix -->");
        
        System.out.print("Rows: ");
        int rows = sc.nextInt();
        
        System.out.print("Columns: ");
        int columns = sc.nextInt();

        Matrix m = new Matrix(rows, columns);

        m.read();
        m.display();

        System.out.println("Determinant: " + m.determinant());
        System.out.println("Inverse: ");
        Matrix inverse = m.inverse();
        inverse.display();
    }
}