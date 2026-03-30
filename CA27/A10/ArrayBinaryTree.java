import java.util.*;

class ArrayBinaryTree {

    private char[] a;
    private int h;

    public ArrayBinaryTree(int h) {
        this.h = h;
        int size = (1 << h) - 1;
        a = new char[size];
        Arrays.fill(a, '\0');
    }

    private int leftIndex(int i) {
        return 2 * (i + 1) - 1;
    }

    private int rightIndex(int i) {
        return 2 * (i + 1);
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    public void build(int i, Scanner sc) {
        if (i < 0 || i >= a.length) {
            return;
        }

        if (i == 0) {
            System.out.print("Enter root node: ");
        } else if (i % 2 == 1) {
            System.out.print("Enter left child of " + a[parentIndex(i)] + ": ");
        } else {
            System.out.print("Enter right child of " + a[parentIndex(i)] + ": ");
        }

        a[i] = sc.next().charAt(0);

        char ans;
        int li = leftIndex(i);
        int ri = rightIndex(i);

        System.out.print("Does " + a[i] + " have left child (Y/y)? ");
        ans = sc.next().charAt(0);
        if ((ans == 'Y' || ans == 'y') && li < a.length) {
            build(li, sc);
        }

        System.out.print("Does " + a[i] + " have right child (Y/y)? ");
        ans = sc.next().charAt(0);
        if ((ans == 'Y' || ans == 'y') && ri < a.length) {
            build(ri, sc);
        }
    }

    public boolean isEmpty() {
        return a[0] == '\0';
    }

    public int size() {
        int sz = 0;
        for (char ch : a) {
            if (ch != '\0') {
                sz++;
            }
        }
        return sz;
    }

    public void preOrder(int i) {
        if (i < 0 || i >= a.length || a[i] == '\0') {
            return;
        }

        System.out.print(a[i] + " ");

        int li = leftIndex(i);
        int ri = rightIndex(i);

        if (li < a.length && a[li] != '\0') {
            preOrder(li);
        }
        if (ri < a.length && a[ri] != '\0') {
            preOrder(ri);
        }
    }

    public void inOrder(int i) {
        if (i < 0 || i >= a.length || a[i] == '\0') {
            return;
        }

        int li = leftIndex(i);
        int ri = rightIndex(i);

        if (li < a.length && a[li] != '\0') {
            inOrder(li);
        }
        System.out.print(a[i] + " ");
        if (ri < a.length && a[ri] != '\0') {
            inOrder(ri);
        }
    }

    public void postOrder(int i) {
        if (i < 0 || i >= a.length || a[i] == '\0') {
            return;
        }

        int li = leftIndex(i);
        int ri = rightIndex(i);

        if (li < a.length && a[li] != '\0') {
            postOrder(li);
        }
        if (ri < a.length && a[ri] != '\0') {
            postOrder(ri);
        }
        System.out.print(a[i] + " ");
    }

    public void levelOrder() {
        if (isEmpty()) {
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int i = q.poll();
            System.out.print(a[i] + " ");

            int li = leftIndex(i);
            int ri = rightIndex(i);

            if (li < a.length && a[li] != '\0') {
                q.offer(li);
            }
            if (ri < a.length && a[ri] != '\0') {
                q.offer(ri);
            }
        }
    }

    public int height() {
        return h;
    }

    public int search(char x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public void displayArray() {
        System.out.println("Array Representation:");
        for (char ch : a) {
            if (ch == '\0') {
                System.out.print("\\0 "); 
            }else {
                System.out.print(ch + " ");
            }
        }
        System.out.println();
    }
}
