import java.util.*;

class Main {
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