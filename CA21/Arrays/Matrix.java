import java.util.*;

class Matrix {
    final int[][] a;
    private final int rows, columns;

    public Matrix(int dim) {
        rows = columns = dim;
        a = new int[dim][dim];
    }

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        a = new int[rows][columns];
    } 

    public Matrix(int a[][]) {
        this.a = a;
        rows = a.length;
        columns = a[0].length;
    }

    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.printf("\nReading Matrix Elements of Dimension (%d x %d) -->", rows, columns);
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                a[i][j] = sc.nextInt();
            }
        }
    }

    public int get(int i, int j) {
        return a[i][j];
    }

    public int determinant() {
        return 0;
    }

    public Matrix inverse() {
        return new Matrix(0);
    }

    public void display() {

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
                sb.append(a[i][j]).append(", ");
            }
            sb.append("\b\b]");
        }

        return sb.toString();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}

class DiagonalMatrix {
    private final int a[], dim;

    public DiagonalMatrix(int dim) {
        a = new int[dim];
        this.dim = dim;
    }

    public DiagonalMatrix(Matrix m) {
        if (!m.isDiagonalMatrix())
            throw new RuntimeException("Not A Diagonal Matrix! Cannot create an object of DiagonalMatrix for the given matrix: " + m);
        dim = m.getRows();
        a = new int[dim];

        for (int i=0; i<dim; i++)
            a[i] = m.get(i,i);
    }

    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter The Diagonal Elements -->");
        for (int i=0; i<dim; i++) 
            a[i] = sc.nextInt();
    }

    public int get(int row, int column) {
        if (row != column) return 0;
        else return a[row];
    }

    public DiagonalMatrix add(DiagonalMatrix b) {
        if (dim != b.dim)
            throw new RuntimeException("Dimensions are not equal! Cannot perform addition.");
        
        DiagonalMatrix sum = new DiagonalMatrix(dim);

        for (int i=0; i<dim; i++) 
            sum.a[i] = a[i] + b.a[i];
        
        return sum;
    }
    
    public Matrix add(Matrix b) {
        if (dim != b.getRows() || dim != b.getColumns())
            throw new RuntimeException("Dimensions are not equal! Cannot perform addition.");
        
        Matrix sum = new Matrix(dim);

        for (int i=0; i<dim; i++) 
            sum.a[i][i] = a[i] + b.a[i][i];
        
        return sum;
    }
    
    public DiagonalMatrix subtract(DiagonalMatrix b) {
        if (dim != b.dim)
            throw new RuntimeException("Dimensions are not equal! Cannot perform addition.");
        
        DiagonalMatrix diff = new DiagonalMatrix(dim);

        for (int i=0; i<dim; i++) 
            diff.a[i] = a[i] - b.a[i];
        
        return diff;
    }

    public Matrix subtract(Matrix b) {
        if (dim != b.getRows() || dim != b.getColumns())
            throw new RuntimeException("Dimensions are not equal! Cannot perform subtraction.");
        
        Matrix diff = new Matrix(dim);

        for (int i=0; i<dim; i++) 
            diff.a[i][i] = a[i] + b.a[i][i];
        
        return diff;
    }

    public DiagonalMatrix multiply(DiagonalMatrix b) {
        if (dim != b.dim)
            throw new RuntimeException("Dimensions incompatible for multiplication!");

        DiagonalMatrix prod = new DiagonalMatrix(dim);

        for (int i=0; i<dim; i++) 
            prod.a[i] = a[i] * b.a[i];
        
        return prod;
    }
    
    public Matrix multiply(Matrix b) {
        if (dim != b.getRows())
            throw new RuntimeException("Dimensions incompatible for multiplication!");

        Matrix prod = new Matrix(dim, b.getColumns());

        for (int i=0; i<dim; i++) 
            prod.a[i][i] = a[i] * b.a[i][i];
        
        return prod;
    }
}

class UTM {
    final float[][] a;
    final int n;

    public UTM(int n) {
        this.n = n;
        a = new float[n][];

        for (int i=0; i<n; i++) {
            a[i] = new float[n-i];
        }
    }

    public UTM add(UTM B) {
        
    }
}