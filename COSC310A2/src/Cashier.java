import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier {
    private JFormattedTextField cashierFormattedTextField;
    JPanel CashierScreen;
    private JButton logOutButton;
    DefaultListModel Items;
    private JButton a1Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton button11;
    private JButton clearButton;
    private JButton addItemButton;
    private JTextField CalculatorDisplay;
    private JTextField totalTextField;
    private JTextField TotalDisplay;
    private JTextField totalPaidTextField;
    private JTextField PaidDisplay;
    private JButton addPaymentButton;
    private JTextPane ItemDisplay;
    private JFormattedTextField itemListFormattedTextField;
    private JFormattedTextField ErrorDisplay;
    private JTextField AmountDue;
    private JTextField AmountDueField;
    private JButton recordSaleButton;

    public Cashier() {
        final int[] i = {1};
        final double[] ordertotal = {0};
        final double[] paymenttotal = {0};
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a1Button.getText());
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a2Button.getText());
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a3Button.getText());
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a4Button.getText());
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a5Button.getText());
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a6Button.getText());
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a7Button.getText());
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a8Button.getText());
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a9Button.getText());
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + button11.getText());
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    double itemcost = Double.parseDouble(CalculatorDisplay.getText());
                    ItemDisplay.setText(ItemDisplay.getText() + System.lineSeparator() + "Item " + i[0] + ": $" + itemcost);
                    i[0]++;
                    ordertotal[0] = ordertotal[0] + itemcost;
                    TotalDisplay.setText(String.valueOf(ordertotal[0]));
                    CalculatorDisplay.setText("");
            }
        });
        addPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double paymentvalue = Double.parseDouble(CalculatorDisplay.getText());
                paymenttotal[0] = paymenttotal[0] + paymentvalue;
                PaidDisplay.setText(String.valueOf(paymenttotal[0]));
                CalculatorDisplay.setText("");
                AmountDueField.setText(String.valueOf(Double.parseDouble(TotalDisplay.getText())-Double.parseDouble(PaidDisplay.getText())));
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Login().LoginPanel);
                Main.frame.pack();
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a0Button.getText());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText("");
            }
        });
        recordSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add Sale to Database
            }
        });
    }
}
