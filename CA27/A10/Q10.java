import java.util.*;

/*
Q10
Create:
1) Array representation of Binary Tree
2) Linked representation of Binary Tree
3) Linked representation of Expression Tree

Structure follows the lecture PDF.
*/

public class Q10 {

    /*========================================================
      1) ARRAY REPRESENTATION OF BINARY TREE
      Data members: a[0..2^h-2], h
      Member functions:
      build(i), isEmpty(), size(), preOrder(i), inOrder(i),
      postOrder(i), levelOrder(), height(), search(x)
    ========================================================*/
    static class ArrayBinaryTree {
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
            if (i < 0 || i >= a.length) return;

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
                if (ch != '\0') sz++;
            }
            return sz;
        }

        public void preOrder(int i) {
            if (i < 0 || i >= a.length || a[i] == '\0') return;

            System.out.print(a[i] + " ");

            int li = leftIndex(i);
            int ri = rightIndex(i);

            if (li < a.length && a[li] != '\0') preOrder(li);
            if (ri < a.length && a[ri] != '\0') preOrder(ri);
        }

        public void inOrder(int i) {
            if (i < 0 || i >= a.length || a[i] == '\0') return;

            int li = leftIndex(i);
            int ri = rightIndex(i);

            if (li < a.length && a[li] != '\0') inOrder(li);
            System.out.print(a[i] + " ");
            if (ri < a.length && a[ri] != '\0') inOrder(ri);
        }

        public void postOrder(int i) {
            if (i < 0 || i >= a.length || a[i] == '\0') return;

            int li = leftIndex(i);
            int ri = rightIndex(i);

            if (li < a.length && a[li] != '\0') postOrder(li);
            if (ri < a.length && a[ri] != '\0') postOrder(ri);
            System.out.print(a[i] + " ");
        }

        public void levelOrder() {
            if (isEmpty()) return;

            Queue<Integer> q = new LinkedList<>();
            q.offer(0);

            while (!q.isEmpty()) {
                int i = q.poll();
                System.out.print(a[i] + " ");

                int li = leftIndex(i);
                int ri = rightIndex(i);

                if (li < a.length && a[li] != '\0') q.offer(li);
                if (ri < a.length && a[ri] != '\0') q.offer(ri);
            }
        }

        public int height() {
            return h;
        }

        public int search(char x) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] == x) return i;
            }
            return -1;
        }

        public void displayArray() {
            System.out.println("Array Representation:");
            for (char ch : a) {
                if (ch == '\0') System.out.print("\\0 ");
                else System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /*========================================================
      2) LINKED REPRESENTATION OF BINARY TREE
      Data member: root
      Member functions:
      build(r,p), isEmpty(), size(), getSize(r), preOrder(r),
      inOrder(r), postOrder(r), levelOrder(), height(r),
      search(r,x), getRoot()
    ========================================================*/
    static class LinkedBinaryTree {
        static class Node {
            char data;
            Node left, right;

            Node() {}
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
            if (root == null) return 0;

            int sz = 0;
            Queue<Node> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                Node r = q.poll();
                sz++;

                if (r.left != null) q.offer(r.left);
                if (r.right != null) q.offer(r.right);
            }
            return sz;
        }

        public int getSize(Node r) {
            if (r == null) return 0;
            return getSize(r.left) + getSize(r.right) + 1;
        }

        public void preOrder(Node r) {
            if (r == null) return;
            System.out.print(r.data + " ");
            if (r.left != null) preOrder(r.left);
            if (r.right != null) preOrder(r.right);
        }

        public void inOrder(Node r) {
            if (r == null) return;
            if (r.left != null) inOrder(r.left);
            System.out.print(r.data + " ");
            if (r.right != null) inOrder(r.right);
        }

        public void postOrder(Node r) {
            if (r == null) return;
            if (r.left != null) postOrder(r.left);
            if (r.right != null) postOrder(r.right);
            System.out.print(r.data + " ");
        }

        public void levelOrder() {
            if (root == null) return;

            Queue<Node> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                Node r = q.poll();
                System.out.print(r.data + " ");
                if (r.left != null) q.offer(r.left);
                if (r.right != null) q.offer(r.right);
            }
        }

        public int height(Node r) {
            if (r == null) return 0;
            int lh = height(r.left);
            int rh = height(r.right);
            return Math.max(lh, rh) + 1;
        }

        public Node search(Node r, char key) {
            if (r == null) return null;
            if (r.data == key) return r;

            Node res1 = search(r.left, key);
            if (res1 != null) return res1;

            return search(r.right, key);
        }
    }

    /*========================================================
      3) LINKED REPRESENTATION OF EXPRESSION TREE
      Data member: root
      Member functions:
      create(P), inOrder(r), preOrder(r), postOrder(r), evaluate(r)
    ========================================================*/
    static class ExpressionTree {
        static class Node {
            String data;
            Node left, right;

            Node(String data) {
                this.data = data;
            }
        }

        private Node root;

        public Node getRoot() {
            return root;
        }

        private boolean isOperator(String x) {
            return x.equals("+") || x.equals("-") || x.equals("*")
                    || x.equals("/") || x.equals("^");
        }

        private double applyOperator(double a, double b, String op) {
            switch (op) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/":
                    if (b == 0) throw new ArithmeticException("Division by zero.");
                    return a / b;
                case "^": return Math.pow(a, b);
                default: throw new IllegalArgumentException("Invalid operator.");
            }
        }

        public void create(String postfix) {
            Stack<Node> s = new Stack<>();
            String[] tokens = postfix.trim().split("\\s+");

            for (String x : tokens) {
                if (isOperator(x)) {
                    if (s.size() < 2) {
                        throw new IllegalArgumentException("Invalid postfix expression.");
                    }
                    Node t1 = s.pop();
                    Node t2 = s.pop();

                    Node newNode = new Node(x);
                    newNode.left = t2;
                    newNode.right = t1;
                    s.push(newNode);
                } else {
                    s.push(new Node(x));
                }
            }

            if (s.size() != 1) {
                throw new IllegalArgumentException("Invalid postfix expression.");
            }

            root = s.pop();
        }

        public void inOrder(Node r) {
            if (r == null) return;

            boolean op = isOperator(r.data);
            if (op) System.out.print("( ");

            if (r.left != null) inOrder(r.left);
            System.out.print(r.data + " ");
            if (r.right != null) inOrder(r.right);

            if (op) System.out.print(") ");
        }

        public void preOrder(Node r) {
            if (r == null) return;
            System.out.print(r.data + " ");
            if (r.left != null) preOrder(r.left);
            if (r.right != null) preOrder(r.right);
        }

        public void postOrder(Node r) {
            if (r == null) return;
            if (r.left != null) postOrder(r.left);
            if (r.right != null) postOrder(r.right);
            System.out.print(r.data + " ");
        }

        public double evaluate(Node r) {
            if (r == null) {
                throw new IllegalStateException("Expression tree is empty.");
            }

            if (!isOperator(r.data)) {
                return Double.parseDouble(r.data);
            }

            double leftOperand = evaluate(r.left);
            double rightOperand = evaluate(r.right);

            return applyOperator(leftOperand, rightOperand, r.data);
        }
    }

    /*========================================================
      MAIN MENU
    ========================================================*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== Q10 MENU ==========");
            System.out.println("1. Array Representation of Binary Tree");
            System.out.println("2. Linked Representation of Binary Tree");
            System.out.println("3. Expression Tree from Postfix");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 0) break;

            switch (choice) {
                case 1: {
                    System.out.print("Enter height of binary tree: ");
                    int h = sc.nextInt();

                    ArrayBinaryTree t = new ArrayBinaryTree(h);
                    t.build(0, sc);

                    System.out.println();
                    t.displayArray();
                    System.out.println("isEmpty(): " + t.isEmpty());
                    System.out.println("size(): " + t.size());
                    System.out.println("height(): " + t.height());

                    System.out.print("Preorder: ");
                    t.preOrder(0);
                    System.out.println();

                    System.out.print("Inorder: ");
                    t.inOrder(0);
                    System.out.println();

                    System.out.print("Postorder: ");
                    t.postOrder(0);
                    System.out.println();

                    System.out.print("Level order: ");
                    t.levelOrder();
                    System.out.println();

                    System.out.print("Enter character to search: ");
                    char key = sc.next().charAt(0);
                    int idx = t.search(key);
                    if (idx == -1) {
                        System.out.println(key + " not found.");
                    } else {
                        System.out.println(key + " found at index " + idx);
                    }
                    break;
                }

                case 2: {
                    LinkedBinaryTree t = new LinkedBinaryTree();
                    t.build(sc);

                    System.out.println("\nisEmpty(): " + t.isEmpty());
                    System.out.println("size() using queue: " + t.size());
                    System.out.println("getSize(root) recursive: " + t.getSize(t.getRoot()));
                    System.out.println("height(root): " + t.height(t.getRoot()));

                    System.out.print("Preorder: ");
                    t.preOrder(t.getRoot());
                    System.out.println();

                    System.out.print("Inorder: ");
                    t.inOrder(t.getRoot());
                    System.out.println();

                    System.out.print("Postorder: ");
                    t.postOrder(t.getRoot());
                    System.out.println();

                    System.out.print("Level order: ");
                    t.levelOrder();
                    System.out.println();

                    System.out.print("Enter character to search: ");
                    char key = sc.next().charAt(0);
                    LinkedBinaryTree.Node res = t.search(t.getRoot(), key);
                    if (res == null) {
                        System.out.println(key + " not found.");
                    } else {
                        System.out.println(key + " found.");
                    }
                    break;
                }

                case 3: {
                    sc.nextLine();
                    System.out.println("Enter postfix expression with spaces between tokens");
                    System.out.println("Example: 5 3 - 2 4 * +");
                    System.out.print("Postfix: ");
                    String postfix = sc.nextLine();

                    try {
                        ExpressionTree et = new ExpressionTree();
                        et.create(postfix);

                        System.out.print("\nInorder (infix): ");
                        et.inOrder(et.getRoot());
                        System.out.println();

                        System.out.print("Preorder (prefix): ");
                        et.preOrder(et.getRoot());
                        System.out.println();

                        System.out.print("Postorder (postfix): ");
                        et.postOrder(et.getRoot());
                        System.out.println();

                        System.out.println("Evaluation: " + et.evaluate(et.getRoot()));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}