
import java.util.Scanner;
class DCLL {
    Node first;
    int size;

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public int indexOf(Point p) {
        int index = 0;
        Node curr = first.next;

        if (first.data.isEqual(p)) 
            return 0;

        while (curr != first) {
            index++;
            if (curr.data.isEqual(p)) 
                return index;

            curr = curr.next;
        }

        return -1;
    }

    public void create(int n) {
        Scanner sc = new Scanner(System.in);
        Node curr = first;
        int x, y;

        for (int i=0; i<n; i++) {
            System.out.println("Point P1 -->");
            System.out.print("Enter x: ");
            x = sc.nextInt();
            System.out.print("Enter y: ");
            y = sc.nextInt();

            if (i==0) {
                first = new Node();
                first.data = new Point(x, y);
                curr = first;
            }
            else {
                curr.next = new Node(new Point(x, y));
                curr.next.prev = curr;
                curr = curr.next;
                curr.next = first;
            }
        }

        first.prev = curr;
        size = n;
    }

    public void createStatic() {
        
        // Test 1
        Point[] points = {
    new Point(0,0),
    new Point(4,0),
    new Point(2,0),  // collinear interior
    new Point(4,4),
    new Point(0,4)
        };
        

        

        size = points.length;

        first = new Node(points[0]);
        Node curr = first;

        for (int i = 1; i < points.length; i++) {
            Node newNode = new Node(points[i]);
            curr.next = newNode;
            newNode.prev = curr;
            curr = newNode;
        }

        // Make circular
        curr.next = first;
        first.prev = curr;
    }

    public void insert (Point data, int index) {
        if (index < 0 || index > size) 
            throw new RuntimeException("Invalid Index!");
        
        Node newNode = new Node(data);

        if (index == 0) {
            first.prev.next = newNode;
            newNode.next = first;
            newNode.prev = first.prev;
            first = newNode;
        }

        else {
            Node prev = first;
            while (--index > 0) 
                prev = prev.next;
            
            newNode.next = prev.next;
            newNode.prev = prev;
            prev.next = prev.next.prev = newNode;
        }

        size++;
    }

    public Point del(int index) {
        if (size == 0) 
            throw new RuntimeException("List is empty!");
        if (index < 0 || index >= size) 
            throw new RuntimeException("Invalid Index!");
        
        Node del;

        if (index == 0) {
            del = first;
            if (size == 1) {
                first = null;
                del.next = del.prev = null;
            }

            else {
                first.prev.next = first.next;
                first.next.prev = first.prev;
                first = first.next;
            }
            
        }
        
        else {
            Node prev = first;

            while (--index > 0)
                prev = prev.next;
            
            del = prev.next;
            prev.next = del.next;
            del.next.prev = prev;

        }

        // del.next = del.prev = null;

        size--;
        System.out.println("Node deleted: " + del.data);
        return del.data;
    }

    public void del(Node node) {
        System.out.println("Node to be deleted: " + node.data);
        if (size == 0)
            throw new RuntimeException("List is empty!");

        if (size == 1) {
            first = null;
            size = 0;
            return;
        }

        if (node == first) {
            first = first.next;
        }

        Node prev = node.prev, next = node.next;


        prev.next = node.next;
        next.prev = node.prev;

        System.out.println("Node before curr: " + prev.data);
        System.out.println("Node after curr: " + next.data);


        // node.next = node.prev = null;
        // node = null;
        

        size--;

    }

    public void display() {
        Node curr = first;

        System.out.print("[");
        int n = size;    
        while (n-- != 0) {
            // System.out.println("Address of x.prev: " + curr.prev);
            // System.out.println("Address of x: " + curr);
            // System.out.println("Address of x.next: " + curr.next);
            System.out.print(curr.data + ", ");
            curr = curr.next;
        }

        // System.out.println("\b\b]");
    }

    public void display2() {
        if (size == 0)
            return;

        Node curr = first;

        do { 
            System.out.print(curr.data + " -->");
            curr = curr.next;
        } while (curr != first);
        
        System.out.println("\b\b\b");
    }
}
