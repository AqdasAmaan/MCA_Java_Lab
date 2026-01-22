class SeriesGenerationMovementCount {
    final Array a;
    final int moves;

    public SeriesGenerationMovementCount(Array a, int moves) {
        this.a = a;
        this.moves = moves;
    }

    public void ans() {
        System.out.print("Series Generated: ");
        a.display();
        System.out.println("Movement Count: " + moves);
    }
}

public class Main {
    public static void main(String[] args) {
        Array a = new Array(10);

        for (int i=0; i<10; i++) {
            a.insert(i+1, i);
        }

        a.display();

        Array b = insertSeries(10);
        b.display();


        // Day 1 - Questions
        SeriesGenerationMovementCount q1_i, q1_ii, q2;

        q1_i = new SeriesGenerationMovementCount(insertSeries1(10), 0);

        Array arr = insertSeries2(10);
        q1_ii = new SeriesGenerationMovementCount(arr, (arr.size() * (arr.size() - 1)) / 2);
    }

    static Array insertSeries(int n) {
        Array a = new Array(n);
        for (int i=0; i<n; i++)
            a.insert(i, i+1);

        return a;
    }
    
    // 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, ... n (where n is even)
    static Array insertSeries1(int n) {
        Array a = new Array(3*n/2);

        for (int i=1; i<=n; i++) {
            if ((i & 1) == 1)
                a.insert(i, a.size());
            else {
                a.insert(i, a.size());
                a.insert(i, a.size());
            }
        }

        return a;
    }

    // 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, ... n (where n is even) 
    static Array insertSeries2(int n) {
        Array a = new Array(3*n/2);

        for (int i=n; i>=1; i--) {
            if ((i & 1) == 1)
                a.insert(i, 0);
            else {
                a.insert(i, 0);
                a.insert(i, 0);
            }
        }

        return a;
    }

    static Array insertSeries3(int n) {
        Array a = new Array(n);

        return a;
    }    
}