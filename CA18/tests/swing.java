import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class swing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculate Salary");

        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        //UIManager.put("Panel.background", Color.GRAY);
        SwingUtilities.updateComponentTreeUI(frame);


        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.getContentPane().setBackground(Color.RED);

        JPanel heading = new JPanel();
        heading.setBackground(Color.BLUE);
        heading.setBackground(Color.LIGHT_GRAY);
        heading.add(new JLabel("Simple Salary Calculator"));
        frame.add(heading, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JTextField salaryField = new JTextField(20);
        JButton calculateButton = new JButton("Calculate");
        
        salaryField.setText("Enter salary");
        salaryField.setForeground(Color.GRAY);

        salaryField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                String text = salaryField.getText();
                boolean valid = !(text.equals("Enter salary") || text.isEmpty());
                calculateButton.setEnabled(valid);
            }
        });

        salaryField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (salaryField.getText().equals("Enter salary")) {
                    salaryField.setText("");
                    salaryField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (salaryField.getText().isEmpty()) {
                    salaryField.setText("Enter salary");
                    salaryField.setForeground(Color.GRAY);
                }
            }
        });


        panel.add(salaryField);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String salaryText = salaryField.getText();
                if (!salaryText.equals("Enter salary") && !salaryText.isEmpty()) {
                    try {
                        double salary = Double.parseDouble(salaryText);
                        double tax = salary * 0.2; // Example tax calculation
                        double netSalary = salary - tax;
                        JOptionPane.showMessageDialog(frame, "Net Salary after tax: " + netSalary);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number for salary.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter your salary.");
                }
            }
        });

        panel.add(calculateButton);
        calculateButton.setEnabled(false);


        
        frame.add(panel);
        
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }
}
