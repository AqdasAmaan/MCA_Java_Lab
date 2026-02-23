import java.util.Scanner;

class Node {
    int data;
    Node link;
}

class LinkedList {
    Node first;
    int size;

    public LinkedList() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        Node curr = first;

        for (int i=0; i<index; i++)
            curr = curr.link;

        return curr.data;
    }

    public int indexOf(int data) {
        int index = 0;

        Node curr = first;

        while (curr.link != null) {
            if (curr.data == data)
                return index;
            curr = curr.link;
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
                first = new Node();
                first.data = x;
                curr = first;
            }
            else {
                curr.link = new Node();
                curr.link.data = x;
                curr = curr.link;
            }
        }
        size = n;
    }

    public void insert(int x, int index) {
        if (index < 0 || index > size) 
            throw new RuntimeException("Invalid Index!");

        Node newNode = new Node();
        newNode.data = x;

        if (index == 0) {
            newNode.link = first.link;
            first = newNode;
        }

        else {
            Node prev = first;

            for (int i=0; i<index-1; i++)
                prev = prev.link;

            newNode.link = prev.link;
            prev.link = newNode;
        }

        size++;
    }

    public int del(int index) {
        if (size == 0) 
            throw new RuntimeException("List is empty!");
        if (index < 0 || index > size) 
            throw new RuntimeException("Invalid Index!");

        int x ;
        if (index == 0) {
            x = first.data;
            first = first.link;
        }

        else {
            Node prev = first;
            
            for (int i=0; i<index-1; i++)
                prev = prev.link;

            Node del = prev.link;
            prev.link = del.link;
            del.link = null;
            x = del.data;
        }

        size--;

        return x;
    }

    public void display() {
        Node curr = first;

        System.out.print("[");
        while (curr != null) {
            System.out.print(curr.data + ", ");
            curr = curr.link;
        }
        System.out.println("\b\b]");
    }

}