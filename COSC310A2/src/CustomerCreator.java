import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerCreator {
    JPanel CustomerScreen;
    private JFormattedTextField NewCustomer;
    private JFormattedTextField CustomerName;
    private JFormattedTextField NameField;
    private JFormattedTextField eMailAddressFormattedTextField;
    private JFormattedTextField EmailField;
    private JFormattedTextField phoneNumberFormattedTextField;
    private JFormattedTextField PhoneNumberField;
    private JButton createCustomerButton;

    public CustomerCreator() {
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phonenumber = PhoneNumberField.getText();
                String name = NameField.getText();
                String email = EmailField.getText();
                // Save customer in database
                Cashier.CloseCustomerCreator();
            }
        });
    }
}
