import java.util.Scanner;

class Matrix {
    int[][] m ;
    int rows, columns;

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        m = new int[rows][columns];
    }

    public Matrix (int[][] x){
        rows = x.length;
        columns = x[0].length;
        m = x;
    }

    public void input(Scanner sc) {
        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                m[i][j] = sc.nextInt();
            }
        }
    }

    public Matrix multiply(Matrix b) {
        Matrix a = this;
        if (a.columns != b.rows){
            System.out.println("Matrices incompatible for Multiplication!");
            return null;
        }

        int[][] product = new int[a.rows][b.columns];
    
        for(int i=0; i<product.length; i++){
            for (int j=0; j<product[0].length; j++){
                //product[i][j] = 0;
                for(int k=0; k<a.columns; k++){
                    product[i][j] += a.m[i][k] * b.m[k][j];
                }
            }
        }
        return new Matrix(product);
    }

    public void inverse() {
        if (this.isSquareMatrix()){
            int det = determinant(this);

            if (det != 0) {
                Matrix adj = getAdjoint(this);

                double inverse[][] = new double[rows][columns];
                
                System.out.println("\n\nImverse of the Matrix--->");
                for (int i=0; i<rows; i++) {
                    for (int j=0; j<columns; j++) {
                        inverse[i][j] = adj.m[i][j] / (double)det;
                        System.out.print(inverse[i][j] + "\t");
                    }
                    System.out.println();
                }
                
            }
        }
        

    }


    public static int determinant(Matrix x) {
        if (x.rows == 1) 
            return x.m[0][0];
        else if (x.rows == 2) {
            int[][] m = x.m ;
            return (m[0][0] * m[1][1] - m[1][0]*m[0][1]) ;
        }

        int det = 0;
        for (int j=0; j<x.rows; j++){
            det += Math.pow(-1, (j)) * determinant(minor(x, 0, j)); 
        }

        return det;
    }

    public static Matrix minor(Matrix x, int r, int c) {
        int[][] minor = new int[x.rows-1][x.columns-1];
        int p = 0, q;
        for(int i=0; i<x.rows; i++) {
            if (i==r)
                continue;
            q = 0;
            for (int j=0; j<x.columns; j++) {
                if (j==c)
                    continue;
                minor[p][q] = x.m[i][j];
                q++;
            }
            p++;
        }

        return new Matrix(minor);
    }

    private static Matrix getAdjoint(Matrix x) {
        int[][] adj = new int[x.rows][x.columns];

        for (int i=0; i<x.rows; i++) {
            for (int j=0; j<x.columns; j++) {
                adj[i][j] = (int)Math.pow(-1, (i+j)) * determinant(minor(x, i, j)) ;
            } 
        }

        return transpose(new Matrix(adj));
    }

    private static Matrix transpose(Matrix x){
        int[][] transposed_matrix = new int[x.columns][x.rows];
        
        for (int i=0; i<x.columns; i++) {
            for (int j=0; j<x.rows; j++) {
                transposed_matrix[i][j] = x.m[j][i];
            }
        }

        return new Matrix(transposed_matrix);
    }

    private boolean isSquareMatrix() {
        return (rows == columns);
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                s.append(m[i][j] + "\t");
            }
            s.append('\n');
        }

        return s.toString();
    }
}

public class DemoMatrix {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int ch;

        do{
            System.out.print("1. Multiply Two Matrices \n2. Determinant of a Matrix \n3. Inverse of A Matrix \n4. Exit \nEnter Your Choice: ");
            ch = sc.nextInt();
            sc.nextLine();

            switch(ch) {
                case 1:
                    System.out.print("Enter the no. of rows and columns in Matrix A :");
                    int r1 = sc.nextInt();
                    int c1 = sc.nextInt();
                    
                    System.out.print("Enter the no. of rows and columns in Matrix B :");
                    int r2 = sc.nextInt();
                    int c2 = sc.nextInt();
                    
                    if (c1 != r2) {
                        System.out.println("Dimensions incompatible for multiplication...!");
                        break;
                    }

                    Matrix A = new Matrix(r1, c1);
                    Matrix B = new Matrix(r2, c2);

                    System.out.println("Enter the elements of Matrix A--->");
                    A.input(sc);

                    System.out.println("Enter the elements of Matrix B--->");
                    B.input(sc);

                    Matrix product = A.multiply(B);

                    System.out.println("\nProduct of the Matrices A & B ---> \n" + product);
                    break;
                
                case 2:
                    System.out.print("Enter the dimensions of the Matrix(m X n): ");
                    int r = sc.nextInt();
                    int c = sc.nextInt();

                    if(r != c) {
                        System.out.println("Determinant is defined for only a square matrix...!");
                        break;
                    }

                    Matrix X = new Matrix(r, c);
                    System.out.println("Enter the elements of the Matrix");
                    X.input(sc);

                    System.out.println("Determinant : " + Matrix.determinant(X));
                    break;

                case 3:
                    System.out.print("Enter the dimensions of the Matrix(m X n): ");
                    r = sc.nextInt();
                    c = sc.nextInt();

                    if(r != c) {
                        System.out.println("Inverse is defined for only a square matrix...!");
                        break;
                    }

                    X = new Matrix(r, c);
                    System.out.println("Enter the elements of the Matrix");
                    X.input(sc);
                    break;

                case 4:
                    System.out.println("Process Terminated...!");
                    break;


                default:
                    System.out.println("Invalid Option!");
            }
        } while (ch != 4);

        sc.close();

        
    }
}