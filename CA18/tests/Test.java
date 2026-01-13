import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class Test extends JFrame {
    
    public Test() {
        setTitle("DB Entry Test");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        JButton btn1 = new JButton("Open Swing Example");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swing swingApp = new swing();
                swingApp.main(null);
            }
        });

        JButton btn2 = new JButton("Open Salary Calculator");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaryCalculator salaryApp = new SalaryCalculator();
                salaryApp.setVisible(true);
            }
        });

        add(btn1);
        add(btn2);

        pack();
        setVisible(true);

        
        

    }
        

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test testApp = new Test();
            testApp.setVisible(true);
        });
    }
}

class JDBCPractice {
    
    public static void run() {
        Random r = new Random();
        int num = r.nextInt(2);
    }

    Object obj = new Object('');
    int val = obj.;
}
