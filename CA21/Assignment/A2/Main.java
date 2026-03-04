/*
Write a java program that accepts a sorted list of integers in an array and return a complete BST as an array.

*/


class Main {

    static int current = 0;

    public static void main(String[] args) {

        int[] sorted = {1,2,3,4,5,6,7,8,9};
        int[] tree = new int[sorted.length];

        current = 0;
        fillInorder(tree, sorted, 0);

        System.out.println("Input:");
        print(sorted);

        System.out.println("Complete BST: ");
        print(tree);
    }

    static void fillInorder(int[] tree, int[] sorted, int index) {

        if (index >= tree.length)
            return;

        // Left
        fillInorder(tree, sorted, 2*index + 1);

        // Root
        tree[index] = sorted[current++];

        // Right
        fillInorder(tree, sorted, 2*index + 2);
    }

    static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i != a.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

}