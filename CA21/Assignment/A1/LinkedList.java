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

    public String get(int index) {
        Node curr = first;

        for (int i=0; i<index; i++)
            curr = curr.next;

        return curr.data;
    }

    public int indexOf(int data) {
        int index = 0;

        Node curr = first;

        while (curr.next != null) {
            if (curr.data.equals(data))
                return index;
            curr = curr.next;
            index++;
        }

        return -1;
    }

    public void create(int n) {
        Scanner sc = new Scanner(System.in);
        Node curr = first;
        String x;

        for (int i=0; i<n; i++) {
            System.out.print("Enter data: ");
            x = sc.next();

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

    public void insert (String x) {
        this.insert(x, 0);
    }

    public void insert (String x, int index) {
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

    public String del(int index) {
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

    public void display() {
        Node curr = first;

        System.out.print("[");
        while (curr != null) {
            System.out.print(curr.data + ", ");
            curr = curr.next;
        }
        System.out.println("\b\b]");
    }
}