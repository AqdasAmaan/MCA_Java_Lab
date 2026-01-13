import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String buttons[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "CE", "%", "√"
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            display.setText(display.getText() + command);
        }
        else if (command.matches("[+\\-*/%]") ) {
            display.setText(display.getText() + " " + command + " ");
        }
        else if (command.equals("√")) {
            try {
                double val = Double.parseDouble(display.getText());
                double result = Math.sqrt(val);
                if (result == (long) result)
                    display.setText(String.valueOf((long) result));
                else
                    display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else if (command.equals("=")) {
            try {
                double result = evaluateExpression(display.getText());
                if (result == (long) result)
                    display.setText(String.valueOf((long) result));
                else
                    display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        else if (command.equals("C") || command.equals("CE")) {
            display.setText("");
        }
    }

    // ======================= Manual Expression Evaluation =========================
    private double evaluateExpression(String expression) {
        ArrayList<String> tokens = tokenize(expression);
        ArrayList<String> rpn = toRPN(tokens);
        return evalRPN(rpn);
    }

    private ArrayList<String> tokenize(String expr) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                num.append(ch);
            } else if (ch == ' ') {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
            } else {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
                tokens.add(Character.toString(ch));
            }
        }
        if (num.length() > 0) tokens.add(num.toString());

        return tokens;
    }

    private ArrayList<String> toRPN(ArrayList<String> tokens) {
        ArrayList<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();

        Map<String, Integer> precedence = Map.of(
                "+", 1,
                "-", 1,
                "*", 2,
                "/", 2,
                "%", 2
        );

        for (String token : tokens) {
            if (token.matches("[0-9.]+")) {
                output.add(token);
            } else if (precedence.containsKey(token)) {
                while (!ops.isEmpty() && precedence.containsKey(ops.peek()) &&
                        precedence.get(ops.peek()) >= precedence.get(token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            }
        }

        while (!ops.isEmpty()) output.add(ops.pop());
        return output;
    }

    private double evalRPN(ArrayList<String> rpn) {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn) {
            if (token.matches("[0-9.]+")) {
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
        return stack.pop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}