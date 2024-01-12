import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterApp {
    private JFrame frame;
    private JTextField amountTextField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;

    public CurrencyConverterApp() {
        frame = new JFrame("Currency Converter");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        // Components
        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();
        fromCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        toCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        // Convert Button Action Listener
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Adding components to the frame
        frame.add(amountLabel);
        frame.add(amountTextField);
        frame.add(new JLabel("From Currency:"));
        frame.add(fromCurrencyComboBox);
        frame.add(new JLabel("To Currency:"));
        frame.add(toCurrencyComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

            // Assuming fixed conversion rates for simplicity
            double conversionRateUSDToEUR = 0.85;
            double conversionRateUSDtoGBP = 0.73;
            double conversionRateEURtoGBP = 0.86;
            double conversionRateUSDtoINR = 74.89;
            double conversionRateEURtoINR = 88.23;
            double conversionRateGBPtoINR = 102.52;

            double result;

            if (fromCurrency.equals("USD")) {
                result = convertFromUSD(amount, toCurrency, conversionRateUSDToEUR, conversionRateUSDtoGBP, conversionRateUSDtoINR);
            } else if (fromCurrency.equals("EUR")) {
                result = convertFromEUR(amount, toCurrency, conversionRateUSDToEUR, conversionRateEURtoGBP, conversionRateEURtoINR);
            } else if (fromCurrency.equals("GBP")) {
                result = convertFromGBP(amount, toCurrency, conversionRateUSDtoGBP, conversionRateEURtoGBP, conversionRateGBPtoINR);
            } else if (fromCurrency.equals("INR")) {
                result = convertFromINR(amount, toCurrency, conversionRateUSDtoINR, conversionRateEURtoINR, conversionRateGBPtoINR);
            } else {
                result = amount; // Same currency
            }

            resultLabel.setText("Result: " + result + " " + toCurrency);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double convertFromUSD(double amount, String toCurrency, double toEUR, double toGBP, double toINR) {
        if (toCurrency.equals("EUR")) {
            return amount * toEUR;
        } else if (toCurrency.equals("GBP")) {
            return amount * toGBP;
        } else if (toCurrency.equals("INR")) {
            return amount * toINR;
        } else {
            return amount; // Same currency
        }
    }

    private double convertFromEUR(double amount, String toCurrency, double toUSD, double toGBP, double toINR) {
        if (toCurrency.equals("USD")) {
            return amount / toUSD;
        } else if (toCurrency.equals("GBP")) {
            return amount * (1 / toGBP);
        } else if (toCurrency.equals("INR")) {
            return amount * toINR;
        } else {
            return amount; // Same currency
        }
    }

    private double convertFromGBP(double amount, String toCurrency, double toUSD, double toEUR, double toINR) {
        if (toCurrency.equals("USD")) {
            return amount / toUSD;
        } else if (toCurrency.equals("EUR")) {
            return amount / (1 / toEUR);
        } else if (toCurrency.equals("INR")) {
            return amount * toINR;
        } else {
            return amount; // Same currency
        }
    }

    private double convertFromINR(double amount, String toCurrency, double toUSD, double toEUR, double toGBP) {
        if (toCurrency.equals("USD")) {
            return amount / toUSD;
        } else if (toCurrency.equals("EUR")) {
            return amount / toEUR;
        } else if (toCurrency.equals("GBP")) {
            return amount / toGBP;
        } else {
            return amount; // Same currency
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterApp();
            }
        });
    }
}
