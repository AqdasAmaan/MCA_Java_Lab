import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Expression Menu =====");
            System.out.println("1. Convert Infix to Postfix");
            System.out.println("2. Evaluate Postfix");
            System.out.println("3. Run Tests");
            System.out.println("0 Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter Infix Expression: ");
                    String infix = sc.nextLine();

                    try {
                        ArrayList<String> tokens = tokenize(infix);
                        ArrayList<String> postfix = toPostfix(tokens);

                        System.out.println("Postfix Expression: " + String.join(" ", postfix));

                        // NEW: Evaluate the converted postfix
                        double result = evalPostfix(postfix);
                        System.out.println("Evaluation Result: " + result);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("Enter Postfix Expression (space separated): ");
                    String postfixInput = sc.nextLine();

                    try {
                        ArrayList<String> postfixTokens = tokenizePostfix(postfixInput);
                        double result = evalPostfix(postfixTokens);
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Error: Invalid Postfix Expression");
                    }
                }

                case 0 -> {
                    System.out.println("Terminated.");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static ArrayList<String> tokenize(String expr) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') 
                num.append(ch);
            
            else if (ch == ' ') {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
            } 
            
            else {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
                tokens.add(Character.toString(ch));
            }
        }

        if (num.length() > 0) 
            tokens.add(num.toString());

        return tokens;
    }

    private static ArrayList<String> tokenizePostfix(String expr) {
        ArrayList<String> tokens = new ArrayList<>();
        String[] parts = expr.trim().split("\\s+");

        for (String part : parts)
            tokens.add(part);

        return tokens;
    }

    private static ArrayList<String> toPostfix(ArrayList<String> tokens) {
        ArrayList<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();

        for (String token : tokens) {

            if (isNumber(token)) 
                output.add(token);
            
            else if (isOperator(token)) {
                while (!ops.isEmpty() &&
                    isOperator(ops.peek()) &&
                    precedence(ops.peek()) >= precedence(token)) {

                    output.add(ops.pop());
                }
                ops.push(token);
            } 
            
            else if (token.equals("(")) 
                ops.push(token);

            else if (token.equals(")")) {

                while (!ops.isEmpty() && !ops.peek().equals("("))
                    output.add(ops.pop());

                if (!ops.isEmpty() && ops.peek().equals("("))
                    ops.pop(); // discard "("

                else 
                    throw new RuntimeException("Mismatched parentheses");
            }

            else 
                throw new RuntimeException("Invalid token: " + token);
        }

        while (!ops.isEmpty()) {
            if (ops.peek().equals("(") || ops.peek().equals(")")) {
                throw new RuntimeException("Mismatched parentheses");
            }
            output.add(ops.pop());
        }

        return output;
    }

    private static boolean isNumber(String token) {
        return Character.isDigit(token.charAt(0));
    }

    private static  boolean isOperator(String token) {
        return token.equals("+") ||
            token.equals("-") ||
            token.equals("*") ||
            token.equals("/") ||
            token.equals("%");
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            default -> -1;
        }; 
    }

    private static double evalPostfix(ArrayList<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    case "%": stack.push(a % b); break;
                }
            }
        }

        if (stack.isEmpty())
            throw new RuntimeException("Invalid expression");

        double result = stack.pop();

        if (!stack.isEmpty())
            throw new RuntimeException("Invalid expression");

        return result;
        // return stack.pop();
    }
}