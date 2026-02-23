import java.util.Scanner;

class ConvexHull {


    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        DCLL list = new DCLL();

        // System.out.print("Enter the no. of points to be stored: ");
        // int n = sc.nextInt();

        // if (n < 3) {
        //     System.out.println("Insufficient no. of points!");
        //     return;
        // }

        // list.create(n);
        list.createStatic();
        list.display();
        Point p0 = p0(list);
        // System.out.println(p0);
        // list.insert(list.del(list.indexOf(p0)), 0);

        // System.out.println("Index of p0 = " + list.indexOf(p0));
        sort(list, p0);

        System.out.println("Sorted List: ");
        list.display();
        getConvexHull(list);
        

    }

    public static Point p0 (DCLL list) {
        Point min;

        Node curr = list.first;
        min = curr.data;

        curr = curr.next;

        while (curr != list.first) {
            if (curr.data.y < min.y)
                min = curr.data;
            else if (curr.data.y == min.y) {
                if (curr.data.x < min.x)
                    min = curr.data; 
            }
            curr = curr.next;
        }
        
        return min;
    }

    public static void sort(DCLL list, Point p0) {
        // System.out.print("List before sorting: ");
        // list.display();
        // System.out.println("Point p0: " + p0);
        if (list.size < 3) 
            throw new RuntimeException("Insufficient nodes/points");
        Node f = list.first;
        for (int i=1; i<list.size; i++) {
            Node prev = f, curr = f.next;
        // System.out.println("Check condition: " + prev.data.isGreaterThan(curr.data, p0));
            for (int j=0; j<list.size - i; j++) {
                if (prev.data.isGreaterThan(curr.data, p0)) {
                    Point temp = prev.data;
                    prev.data = curr.data;
                    curr.data = temp;
                }

                prev = prev.next;
                curr = curr.next;
            }

        }
    }

    public static void getConvexHull(DCLL list) {
        Node x = list.first, xr, xrr;
        xr = x.next;
        int emptyPass = 0;
        
        System.out.println("      X     |     XR     |     XRR  ");
        while (list.size() >= 3 && emptyPass <= list.size()) {
            xrr = xr.next;

            System.out.println("Current Size: " + list.size());
            System.out.println(x.data + "|" + xr.data  + "|" + xrr.data);
            
            if (x.data.isLE180(xr.data, xrr.data)) {
                System.out.println("Inside if: " + x.data + "|" + xr.data  + "|" + xrr.data + "(before modification)");
                int index = list.indexOf(xr.data);
                System.out.println("Index of element to be deleted: " + index);

                list.del(index);
                // list.del(xr);
                emptyPass = 0;
                // System.out.println("Address of x: " + x);
                // System.out.println("Address of x.prev: " + x.prev);
                // System.out.println("Address of x.next: " + x.next);
                // System.out.println("Address of xr: " + xr);

                xr = x;
                x = x.prev;
                System.out.println("Inside if: " + x.data + "|" + xr.data  + "|" + xrr.data + "(after modification)");
            }
            else {
                System.out.println("Inside else: " + x.data + "|" + xr.data  + "|" + xrr.data + "(before modification)");
                emptyPass++;
                x = xr;
                xr = xrr;

                System.out.println("Inside else: " + x.data + "|" + xr.data  + "|" + xrr.data + "(after modification)");
            }
            list.display();
        }
        
        System.out.println("Vertices of Convex Hull: ");

        System.out.println("Size = " + list.size());
        list.display();
    }
}