import java.util.*;

public class Matrix {
    final float[][] a;
    final int rows, columns;

    public Matrix(int dim) {
        rows = columns = dim;
        a = new float[dim][dim];
    }

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        a = new float[rows][columns];
    } 

    public Matrix(float a[][]) {
        this.a = a;
        rows = a.length;
        columns = a[0].length;
    }

    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.printf("\nReading Matrix Elements of Dimension (%d x %d) -->\n", rows, columns);
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                a[i][j] = sc.nextFloat();
            }
        }
    }

    public float get(int i, int j) {
        return a[i][j];
    }

    public Matrix add(Matrix B) {
        if (rows != B.rows || columns != B.columns)
            throw new RuntimeException("Incompatible dimensions for addition! Operation failed.");
        
        Matrix C = new Matrix(rows, columns);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                C.a[i][j] = a[i][j] + B.a[i][j];
            }
        }

        return C;
    }
    
    public Matrix multiply(Matrix B) {
        if (columns != B.rows)
            throw new RuntimeException("Incompatible dimensions for multiplication! Operation failed.");
        
        Matrix C = new Matrix(rows, B.columns);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<B.columns; j++) {
                for (int k=0; k<columns; k++) {
                    C.a[i][j] += a[i][k] * B.a[k][j];
                }
            }
        }

        return C;
    }

    public float determinant() {
        if (rows != columns)
            throw new RuntimeException("Invalid operation for the given matrix! Determinant is defined only for square matrices.");
        
        Matrix det = new Matrix(a);

        float d = 1;
        float x, y;

        for (int k=0; k<rows; k++) {
            x = det.a[k][k];

            for (int i=0; i<rows && i!=k; i++) {
                y = det.a[i][k];

                float div = (x != 0) ? y/x : 0;

                for (int j=0; j<columns; j++)
                    det.a[i][j] -= (det.a[k][j] * div); 
            }
        }

        for (int i=0; i<rows; i++) {
            d *= det.a[i][i];
        }

        return d;
    }

    public Matrix inverse() {
        if (rows != columns)
            throw new RuntimeException("Cannot find the inverse of a non-square matrix.");

        if (determinant() == 0) {
            throw new RuntimeException("Cannot find the inverse of a matrix with determinant zero.");
        }

        Matrix inv = Matrix.identity(rows), temp = new Matrix(a);

        float x, y;

        for (int k=0; k<rows; k++) {
            x = temp.a[k][k];

            if (x==0)
                continue;

            for (int i=0; i<rows && i!=k; i++) {
                y = temp.a[i][k];

                for (int j=0; j<columns; j++) {
                    temp.a[i][j] -= (temp.a[k][j] * y/x); 
                    inv.a[i][j] -= (inv.a[k][j] * y/x);
                }
            }
        }

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                inv.a[i][j] /= temp.a[i][i];
            }
        }

        return inv;
    }

    public void display() {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) 
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }
    }

    public boolean isDiagonalMatrix() {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                if (i != j && a[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix: \n");
        for (int i=0; i<rows; i++) {
            sb.append("[");
            for (int j=0; j<columns; j++) {
                sb.append(a[i][j]).append("\t");
            }
            sb.append("\b\b]\n");
        }

        return sb.toString();
    }

    // Static Methods --->
    public static Matrix identity(int dim) {
        Matrix identity = new Matrix(dim);

        for (int i=0; i<dim; i++) {
            identity.a[i][i] = 1;
        }

        return identity;
    }
}