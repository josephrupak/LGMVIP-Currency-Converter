import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterGUI extends JFrame {
    private JTextField amountTextField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;

    private static final String[] currencies = { "USD", "EUR", "GBP", "JPY", "INR" };
    private static final double[][] exchangeRates = {
        { 1.0, 0.85, 0.72, 109.5, 74.50 },
        { 1.18, 1.0, 0.85, 129.6, 88.10 },
        { 1.39, 1.18, 1.0, 153.5, 104.20 },
        { 0.0091, 0.0077, 0.0065, 1.0, 0.68 },
        { 0.013, 0.011, 0.0094, 1.47, 1.0 }
    };

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        amountTextField = new JTextField(10);
        add(new JLabel("Amount: "));
        add(amountTextField);

        fromCurrencyComboBox = new JComboBox<>(currencies);
        add(new JLabel("From: "));
        add(fromCurrencyComboBox);

        toCurrencyComboBox = new JComboBox<>(currencies);
        add(new JLabel("To: "));
        add(toCurrencyComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        add(convertButton);

        resultLabel = new JLabel();
        add(resultLabel);
    }

    private void convertCurrency() {
        double amount = Double.parseDouble(amountTextField.getText());
        int fromIndex = fromCurrencyComboBox.getSelectedIndex();
        int toIndex = toCurrencyComboBox.getSelectedIndex();

        double fromRate = exchangeRates[fromIndex][toIndex];
        double toRate = exchangeRates[toIndex][fromIndex];

        double convertedAmount = amount * fromRate;
        resultLabel.setText(df.format(convertedAmount) + " " + currencies[toIndex]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        CurrencyConverterGUI gui = new CurrencyConverterGUI();
        gui.setVisible(true);
    }
}
