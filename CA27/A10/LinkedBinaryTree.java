import java.util.*;

class LinkedBinaryTree {

    static class Node {

        char data;
        Node left, right;

        Node() {
        }

        Node(char data) {
            this.data = data;
        }
    }

    private Node root;

    public LinkedBinaryTree() {
        root = null;
    }

    private Node createNode() {
        return new Node();
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void build(Scanner sc) {
        root = build(root, null, sc);
    }

    private Node build(Node r, Node p, Scanner sc) {
        if (r == null) {
            r = createNode();
            if (p == null) {
                root = r;
                System.out.print("Enter root node: ");
            }
        }

        if (p != null) {
            if (p.left == r) {
                System.out.print("Enter left child of " + p.data + ": ");
            } else {
                System.out.print("Enter right child of " + p.data + ": ");
            }
        }

        r.data = sc.next().charAt(0);

        char ans;
        System.out.print("Does " + r.data + " have left child (Y/y)? ");
        ans = sc.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            r.left = createNode();
            build(r.left, r, sc);
        }

        System.out.print("Does " + r.data + " have right child (Y/y)? ");
        ans = sc.next().charAt(0);
        if (ans == 'Y' || ans == 'y') {
            r.right = createNode();
            build(r.right, r, sc);
        }

        return r;
    }

    public int size() {
        if (root == null) {
            return 0;
        }

        int sz = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node r = q.poll();
            sz++;

            if (r.left != null) {
                q.offer(r.left);
            }
            if (r.right != null) {
                q.offer(r.right);
            }
        }
        return sz;
    }

    public int getSize(Node r) {
        if (r == null) {
            return 0;
        }
        return getSize(r.left) + getSize(r.right) + 1;
    }

    public void preOrder(Node r) {
        if (r == null) {
            return;
        }
        System.out.print(r.data + " ");
        if (r.left != null) {
            preOrder(r.left);
        }
        if (r.right != null) {
            preOrder(r.right);
        }
    }

    public void inOrder(Node r) {
        if (r == null) {
            return;
        }
        if (r.left != null) {
            inOrder(r.left);
        }
        System.out.print(r.data + " ");
        if (r.right != null) {
            inOrder(r.right);
        }
    }

    public void postOrder(Node r) {
        if (r == null) {
            return;
        }
        if (r.left != null) {
            postOrder(r.left);
        }
        if (r.right != null) {
            postOrder(r.right);
        }
        System.out.print(r.data + " ");
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node r = q.poll();
            System.out.print(r.data + " ");
            if (r.left != null) {
                q.offer(r.left);
            }
            if (r.right != null) {
                q.offer(r.right);
            }
        }
    }

    public int height(Node r) {
        if (r == null) {
            return 0;
        }
        int lh = height(r.left);
        int rh = height(r.right);
        return Math.max(lh, rh) + 1;
    }

    public Node search(Node r, char key) {
        if (r == null) {
            return null;
        }
        if (r.data == key) {
            return r;
        }

        Node res1 = search(r.left, key);
        if (res1 != null) {
            return res1;
        }

        return search(r.right, key);
    }
}
