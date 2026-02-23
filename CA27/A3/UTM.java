import java.util.Scanner;

class UTM {
    final double[][] a;
    final int n;

    public UTM(int n) {
        this.n = n;
        a = new double[n][];

        for (int i=0; i<n; i++) {
            a[i] = new double[n-i];
        }
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the elements of the Upper Triangular Matrix -->");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n-i; j++) {
                System.out.print("[" + i + "," + (j+i) + "]: ");
                a[i][j] = sc.nextFloat();
            }
        }
    }

    public UTM add(UTM B) {
        if (n != B.n)
            throw new RuntimeException("Dimensions are not same! Cannot perform addition.");

        UTM C = new UTM(n);

        for (int i=0; i<n; i++) {
            for (int j=0; j<n-i; j++) {
                C.a[i][j] = a[i][j] + B.a[i][j];
            }
        }

        return C;
    }

    public UTM multiply(UTM B) {
        if (n != B.n)
            throw new RuntimeException("Dimensions are not same! Cannot perform multiplications.");

        UTM C = new UTM(n);

        for (int i=0; i<n; i++) {
            for (int j=0; j<n-i; j++) {
                for (int k=i; k<=j; k++)
                    C.a[i][j-i] += a[i][k-i] * B.a[k][j-k];
            }
        }

        return C;
    }

    public Matrix multiply (LTM B) {
        if (n != B.n) 
            throw new RuntimeException("Incompatible dimensions for multiplication! Operation failed.");

        Matrix C = new Matrix(n);

        for (int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for (int k=Math.max(i,j); k <n; k++) {
                    C.a[i][j] += a[i][k-i] * B.a[k][j];
                }
            }
        }

        return C;
    }

    public double determinant() {
        double det = 1;

        for (int i=0; i<n; i++)
            det *= a[i][0];

        return det;
    }

    public void display() {
        System.out.println("Matrix -->");
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i < j)
                    System.out.printf("%3d\t", 0);
                else
                    System.out.printf("%3.2f\t", a[i][j]);
            }

            System.out.println();
        }
    }
}
