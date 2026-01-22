import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();
        Array a = getSeries(n);

        a.display();
    }

    public static Array getSeries(int n) {
        Array a = new Array(n);

        for (int i=1; i<=n; i++) {
            if (i % 2 == 0)
                a.insert(i, i/2 - 1);
            else
                a.insert(i, i - 1);
        }

        return a;
    }
}