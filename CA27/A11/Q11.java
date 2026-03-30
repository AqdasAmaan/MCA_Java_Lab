import java.util.*;

/*
Q11
Create:
1) Heap Tree (Max Heap using array)
2) Heap Sort
3) Priority Queue using Max Heap

Structure follows the lecture PDF.
*/

public class Q11 {

    /*========================================================
      MAX HEAP TREE
      Operations:
      insert(x), adjust(i,n), delMax(), heapify(), heapSort()
    ========================================================*/
    static class MaxHeapTree {
        private int[] a;
        private int n;
        private int capacity;

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
                adjustTemp(temp, i, tempN);
            }

            for (int i = tempN - 1; i >= 1; i--) {
                int x = temp[i];
                temp[i] = temp[0];
                temp[0] = x;
                adjustTemp(temp, 0, i);
            }

            return temp;
        }

        private void adjustTemp(int[] arr, int i, int size) {
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
            if (n == 0) throw new RuntimeException("Heap tree is empty.");
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

    /*========================================================
      PRIORITY QUEUE USING MAX HEAP TREE
    ========================================================*/
    static class PriorityQueueHeap {
        private MaxHeapTree t;

        public PriorityQueueHeap(int capacity) {
            t = new MaxHeapTree(capacity);
        }

        public boolean isEmpty() {
            return t.isEmpty();
        }

        public void insert(int x) {
            t.insert(x);
        }

        public int del() {
            return t.delMax();
        }

        public void display() {
            t.display();
        }
    }

    /*========================================================
      MAIN MENU
    ========================================================*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MaxHeapTree heap = new MaxHeapTree(200);
        PriorityQueueHeap pq = new PriorityQueueHeap(200);

        while (true) {
            System.out.println("\n========== Q11 MENU ==========");
            System.out.println("1. Build Heap from list");
            System.out.println("2. Insert into Heap");
            System.out.println("3. Delete Max from Heap");
            System.out.println("4. Display Heap");
            System.out.println("5. Heap Sort current Heap data");
            System.out.println("6. Priority Queue Insert");
            System.out.println("7. Priority Queue Delete");
            System.out.println("8. Display Priority Queue heap array");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 0) break;

            try {
                switch (choice) {
                    case 1: {
                        System.out.print("Enter number of elements: ");
                        int n = sc.nextInt();

                        int[] data = new int[n];
                        System.out.print("Enter elements: ");
                        for (int i = 0; i < n; i++) {
                            data[i] = sc.nextInt();
                        }

                        heap.buildFromList(data);
                        System.out.println("Heap built successfully.");
                        heap.display();
                        break;
                    }

                    case 2: {
                        System.out.print("Enter element to insert in heap: ");
                        int x = sc.nextInt();
                        heap.insert(x);
                        heap.display();
                        break;
                    }

                    case 3: {
                        int mx = heap.delMax();
                        System.out.println("Deleted max element: " + mx);
                        heap.display();
                        break;
                    }

                    case 4: {
                        heap.display();
                        break;
                    }

                    case 5: {
                        int[] sorted = heap.heapSort();
                        System.out.print("Heap sort result (ascending): ");
                        for (int x : sorted) {
                            System.out.print(x + " ");
                        }
                        System.out.println();
                        break;
                    }

                    case 6: {
                        System.out.print("Enter element to insert in priority queue: ");
                        int x = sc.nextInt();
                        pq.insert(x);
                        pq.display();
                        break;
                    }

                    case 7: {
                        int mx = pq.del();
                        System.out.println("Priority removed element: " + mx);
                        pq.display();
                        break;
                    }

                    case 8: {
                        pq.display();
                        break;
                    }

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}