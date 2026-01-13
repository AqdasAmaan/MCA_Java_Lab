import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalaryCalculator extends JFrame {

    private JTextField bsField, gpField;
    private JButton calcBtn;
    private JTable table;
    private DefaultTableModel model;

    public SalaryCalculator() {
        setTitle("Salary Calculator");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Basic Salary (BS):"));
        bsField = new JTextField();
        inputPanel.add(bsField);
        inputPanel.add(new JLabel("Grade Pay (GP):"));
        gpField = new JTextField();
        inputPanel.add(gpField);

        calcBtn = new JButton("Calculate Salary");
        calcBtn.setAlignmentX(CENTER_ALIGNMENT);
        inputPanel.add(calcBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {
            "BS", "GP", "DA", "HRA", "TA", "GS", "IT", "NS"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button Action
        calcBtn.addActionListener((ActionEvent e) -> {
            calculate();
        });
    }

    private void calculate() {
        try {
            double BS = Double.parseDouble(bsField.getText());
            double GP = Double.parseDouble(gpField.getText());

            // DA calculation
            double DA;
            if (GP >= 500) DA = 300;
            else if (GP >= 400) DA = 200;
            else if (GP >= 200) DA = 150;
            else DA = 100;

            // HRA = 10% of (BS + GP)
            double HRA = 0.10 * (BS + GP);

            // TA = 10% of GP
            double TA = 0.10 * GP;

            // GS = BS + GP + DA + HRA + TA
            double GS = BS + GP + DA + HRA + TA;

            // IT = 10% of GS
            double IT = 0.10 * GS;

            // NS = GS – IT
            double NS = GS - IT;

            // Add to table
            model.addRow(new Object[]{
                BS, GP, DA, HRA, TA, GS, IT, NS
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid input! Please enter numeric values.");
        }
    }

    public static void main(String[] args) {
        new SalaryCalculator().setVisible(true);
    }
}
