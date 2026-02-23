import java.util.Scanner;

class LinkedList {
    Node first;
    int size;

    public LinkedList() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        Node curr = first;

        for (int i=0; i<index; i++)
            curr = curr.next;

        return curr.data;
    }

    public int indexOf(int data) {
        int index = 0;

        Node curr = first;

        while (curr.next != null) {
            if (curr.data == data)
                return index;
            curr = curr.next;
            index++;
        }

        return -1;
    }

    public void create(int n) {
        Scanner sc = new Scanner(System.in);
        Node curr = first;
        int x;

        for (int i=0; i<n; i++) {
            System.out.print("Enter data: ");
            x = sc.nextInt();

            if (i==0) {
                first = new Node(x);
                curr = first;
            }
            else {
                curr.next = new Node(x);
                curr = curr.next;
            }
        }
        size = n;
    }

    public void insert (int x, int index) {
        if (index < 0 || index > size) 
            throw new RuntimeException("Invalid Index!");
        
        Node newNode = new Node(x);

        if (index == 0) {
            newNode.next = first;
            first = newNode;
        }

        else {
            Node prev = first;
            while (--index > 0) 
                prev = prev.next;
            
            newNode.next = prev.next;
            prev.next = newNode;

        }
        size++;
    }

    public int del(int index) {
        if (size == 0) 
            throw new RuntimeException("List is empty!");
        if (index < 0 || index > size) 
            throw new RuntimeException("Invalid Index!");
        
        Node del;

        if (index == 0) {
            del = first;
            first = first.next;
        }
        
        else {
            Node prev = first;

            while (--index > 0)
                prev = prev.next;
            
            del = prev.next;
            prev.next = del.next;
        }

        del.next = null;

        size--;

        return del.data;
    }

    public void sort(String type) {

        if (first == null) {
            System.out.println("List is empty!");
            return;
        }

        if (size() == 1) {
            System.out.println();
            return;
        }

        int max, min;
        max = min = first.data;

        Node curr = first;

        while (curr.next != null) {
            curr = curr.next;
            if (curr.data > max) max = curr.data;
            if (curr.data < min) min = curr.data;
        }

        switch (type.strip().toLowerCase()) {
            case "bucket" -> {

                // System.out.println("Max: " + max);
                // System.out.println("Min: " + min);

                LinkedList[] bucket = new LinkedList[max-min+1];
                for (int i=0; i<bucket.length; i++)
                    bucket[i] = new LinkedList();
                
                LinkedList b;
                curr = first;
                for (int i=0; i<size(); i++) {
                    b = bucket[curr.data - min];
                    b.insert(curr.data, 0);
                    curr = curr.next;
                }

                // System.out.println("SortedList created!");
                LinkedList sortedList = new LinkedList();
                
                for (int i=0; i<bucket.length; i++) {
                    b = bucket[i];
                    while (b.size() != 0)
                        sortedList.insert(b.del(b.size() - 1), sortedList.size());
                }

                // System.out.println("SortedList initalization complete!");
                

                first = sortedList.first;
                sortedList = null;

                System.out.println("List sorted.");
            }

            case "radix" -> {

                if (min < 0) {
                    curr = first;

                    while (curr != null) {
                        curr.data -= min;
                        curr = curr.next;
                    }
                    max += min;
                }

                LinkedList[] bucket = new LinkedList[10];
                for (int i=0; i<10; i++) 
                    bucket[i] = new LinkedList();

                // System.out.println("Empty buckets created successfully");

                int x, div = 1, n = length(max);  

                for (int i=0; i<n; i++) {
                    // System.out.println("Inside Loop (i): " + i);
                    curr = first;
                    
                    while (curr != null) {
                        x = (curr.data / div) % 10;
                        bucket[x].insert(curr.data, 0);

                        curr = curr.next;
                    }
                    
                    // System.out.println("Elements inserted in bucket.");

                    LinkedList b;
                    curr = first;

                    for (int j=0; j<10; j++) {
                        // System.out.println("Inside Loop (j): " + j);

                        b = bucket[j];
                        while (b.size() != 0) {
                            // System.out.println("Inside while... ");
                            curr.data = b.del(b.size() - 1);
                            curr = curr.next;
                            // System.out.println("Moving to next while iteration.");
                        }
                    }

                    // System.out.println("Buckets emptied.");
                    
                    div *= 10;
                }

                if (min < 0) {
                    curr = first;

                    while (curr != null) {
                        curr.data += min;
                        curr = curr.next;
                    }
                }

                System.out.println("List sorted.");
            }

            default -> throw new RuntimeException("Available Sorting Techniques: Bucket & Radix");
        }
    }

    public void display() {
        Node curr = first;

        System.out.print("[");
        while (curr != null) {
            System.out.print(curr.data + ", ");
            curr = curr.next;
        }
        System.out.println("\b\b]");
    }

    //Helper Methods

    private int length(int x) {
        int length = 0;

        while (x!=0) {
            x /= 10;
            length++;
        }

            System.out.println("Length = " + length);
        return length;
    }
}