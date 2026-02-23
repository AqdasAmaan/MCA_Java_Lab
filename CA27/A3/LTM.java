import java.util.Scanner;

class LTM {
    final double[][] a;
    final int n;

    public LTM(int n) {
        this.n = n;
        a = new double[n][];

        for (int i=1; i<=n; i++) {
            a[i-1] = new double[i];
        }
    }

    public LTM(double[][] a) {
        n = a.length;

        this.a = new double[n][];

        for (int i=1; i<=n; i++) {
            this.a[i-1] = new double[i];
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) 
                this.a[i][j] = a[i][j];
        }
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the elements of the Lower Triangular Matrix -->");
        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) {
                System.out.print("[" + i + "," + j + "]: ");
                a[i][j] = sc.nextFloat();
            }
        }
    }

    public LTM add(LTM B) {
        if (n != B.n)
            throw new RuntimeException("Dimensions are not same! Cannot perform addition.");

        LTM C = new LTM(n);

        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) {
                C.a[i][j] = a[i][j] + B.a[i][j];
            }
        }
        return C;
    }

    public LTM multiply (LTM B) {
        if (n != B.n) 
            throw new RuntimeException("Incompatible dimensions for multiplication! Operation failed.");

        LTM C = new LTM(n) ;

        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) {
                for (int k=j; k<=i; k++)
                    C.a[i][j] += a[i][k] * B.a[k][j];
            }
        }

        return C;
    }

    public Matrix multiply (UTM B) {
        if (n != B.n) 
            throw new RuntimeException("Incompatible dimensions for multiplication! Operation failed.");

        Matrix C = new Matrix(n);

        for (int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for (int k=0; k <= Math.min(i, j); k++) {
                    C.a[i][j] += a[i][k] * B.a[k][j];
                }
            }
        }

        return C;
    }

    public double determinant() {
        double det = 1;

        for (int i=0; i<n; i++)
            det *= a[i][i];

        return det;
    }

    public LTM inverse() {
        for (int i=0; i<n; i++) {
            if (a[i][i] == 0)
                throw new RuntimeException("Matrix is not invertible!");
        }

        LTM inv = new LTM(n), temp = new LTM(a);
        for (int i=0; i<n; i++)
            inv.a[i][i] = 1;

        double x, y;
        for (int k=0; k<n; k++) {
            x = temp.a[k][k];
            for (int i=k+1; i<n; i++) {
                y = temp.a[i][k];

                double factor = y / x;


                for (int j=0; j<=k; j++) {
                    temp.a[i][j] -= temp.a[k][j] * factor;
                    inv.a[i][j] -= inv.a[k][j] * factor;
                }
            }
        }

        double z;
        for (int i=0; i<n; i++) {
            z = temp.a[i][i];
            for (int j=0; j<=i; j++) {
                inv.a[i][j] /= z;
            }
        }
    
        return inv;
    }

    public void display() {
        System.out.println("Matrix -->");
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (j > i)
                    System.out.printf("%3d\t", 0);
                else
                    System.out.printf("%5.3f\t", a[i][j]);
            }

            System.out.println();
        }
    }
}
