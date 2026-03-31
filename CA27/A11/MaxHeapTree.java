
import java.util.*;

class MaxHeapTree {

    private int[] a;
    private int n;
    private final int capacity;

    public MaxHeapTree(int capacity) {
        this.capacity = capacity;
        this.a = new int[capacity];
        this.n = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(int x) {
        if (n == capacity) {
            throw new RuntimeException("Heap tree is full.");
        }

        int i = n;
        n++;

        while (i > 0 && a[parent(i)] < x) {
            a[i] = a[parent(i)];
            i = parent(i);
        }
        a[i] = x;
    }

    public void adjust(int i, int size) {
        int j = left(i);
        int x = a[i];

        while (j <= size - 1) {
            if (j < size - 1 && a[j] < a[j + 1]) {
                j = j + 1;
            }

            if (x >= a[j]) {
                break;
            }

            a[(j - 1) / 2] = a[j];
            j = 2 * j + 1;
        }

        a[(j - 1) / 2] = x;
    }

    public int delMax() {
        if (n == 0) {
            throw new RuntimeException("Heap tree is empty.");
        }

        int x = a[0];
        a[0] = a[n - 1];
        n--;

        if (n > 0) {
            adjust(0, n);
        }

        return x;
    }

    public void heapify() {
        for (int i = (n - 2) / 2; i >= 0; i--) {
            adjust(i, n);
        }
    }

    public void buildFromList(int[] data) {
        if (data.length > capacity) {
            throw new RuntimeException("Input size exceeds heap capacity.");
        }

        n = data.length;
        for (int i = 0; i < n; i++) {
            a[i] = data[i];
        }
        heapify();
    }

    public int[] heapSort() {
        int[] temp = Arrays.copyOf(a, n);
        int tempN = n;

        for (int i = (tempN - 2) / 2; i >= 0; i--) {
            adjust(temp, i, tempN);
        }

        for (int i = tempN - 1; i >= 1; i--) {
            int x = temp[i];
            temp[i] = temp[0];
            temp[0] = x;
            adjust(temp, 0, i);
        }

        return temp;
    }

    private void adjust(int[] arr, int i, int size) {
        int j = 2 * i + 1;
        int x = arr[i];

        while (j <= size - 1) {
            if (j < size - 1 && arr[j] < arr[j + 1]) {
                j = j + 1;
            }

            if (x >= arr[j]) {
                break;
            }

            arr[(j - 1) / 2] = arr[j];
            j = 2 * j + 1;
        }

        arr[(j - 1) / 2] = x;
    }

    public int top() {
        if (n == 0) {
            throw new RuntimeException("Heap tree is empty.");
        }
        return a[0];
    }

    public void display() {
        System.out.print("Heap array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
