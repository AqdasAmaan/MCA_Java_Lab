import java.util.*;

class Main {
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