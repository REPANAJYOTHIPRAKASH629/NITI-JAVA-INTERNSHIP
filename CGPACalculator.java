import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CGPACalculator extends JFrame implements ActionListener {

    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel resultLabel;

    public CGPACalculator() {
        setTitle("CGPA Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Create text fields for subjects
        subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField();
            add(label);
            add(subjectFields[i]);
        }

        // Create the calculate button
        calculateButton = new JButton("Calculate CGPA");
        calculateButton.addActionListener(this);

        // Create label for displaying the result
        resultLabel = new JLabel("CGPA: ");

        // Add components to the frame
        add(calculateButton);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            // Calculate CGPA when the button is clicked
            double totalMarks = 0;
            int subjectsCount = 0;

            for (JTextField field : subjectFields) {
                try {
                    double marks = Double.parseDouble(field.getText());
                    totalMarks += marks;
                    subjectsCount++;
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid marks for all subjects.");
                    return;
                }
            }

            // Calculate CGPA
            double cgpa = totalMarks / (subjectsCount * 10);
            resultLabel.setText("CGPA: " + String.format("%.2f", cgpa));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CGPACalculator());
    }
}
