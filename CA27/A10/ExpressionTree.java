
import java.util.Stack;

class ExpressionTree {

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
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator.");
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
        if (r == null) {
            return;
        }

        boolean op = isOperator(r.data);
        if (op) {
            System.out.print("( ");
        }

        if (r.left != null) {
            inOrder(r.left);
        }
        System.out.print(r.data + " ");
        if (r.right != null) {
            inOrder(r.right);
        }

        if (op) {
            System.out.print(") ");
        }
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
