import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private int targetNumber;
    private int attempts;
    private JLabel feedbackLabel;
    private JTextField guessTextField;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();

        JLabel titleLabel = new JLabel("Guess the Number!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        feedbackLabel = new JLabel("Take a guess!");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);

        guessTextField = new JTextField();
        guessTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(titleLabel);
        panel.add(feedbackLabel);
        panel.add(guessTextField);
        panel.add(guessButton);
        panel.add(attemptsLabel);

        add(panel);

        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                feedbackLabel.setText("Congratulations! You guessed the correct number.");
                guessTextField.setEnabled(false);
            } else if (userGuess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            attemptsLabel.setText("Attempts: " + attempts);
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
