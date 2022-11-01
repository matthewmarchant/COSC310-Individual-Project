import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerCreator {
    JPanel CustomerScreen;
    private JFormattedTextField NewCustomer;
    private JLabel CustomerName;
    private JFormattedTextField LastNameField;
    private JLabel eMailAddressLabel;
    private JFormattedTextField EmailField;
    private JLabel phoneNumberLabel;
    private JFormattedTextField PhoneNumberField;
    private JButton createCustomerButton;
    private JFormattedTextField FirstNameField;
    private JLabel CustomerCreatorMessageOutput;

    public CustomerCreator(Cashier parent) {
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = FirstNameField.getText();
                String lastName = LastNameField.getText();
                String phone = PhoneNumberField.getText();
                String email = EmailField.getText();
                if(firstName.equals("")){
                    CustomerCreatorMessageOutput.setText("Missing first name");
                    return;
                }
                if(lastName.equals("")){
                    CustomerCreatorMessageOutput.setText("Missing last name");
                    return;
                }
                if(phone.equals("")){
                    CustomerCreatorMessageOutput.setText("Missing phone number");
                    return;
                }
                if(email.equals("")){
                    CustomerCreatorMessageOutput.setText("Missing email");
                    return;
                }
                if(!phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")){
                    CustomerCreatorMessageOutput.setText("invalid phone number");
                    return;
                }
                if(!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
                    CustomerCreatorMessageOutput.setText("invalid email");
                    return;
                }
                DBConnection con = new DBConnection();
                int id = con.addNewCustomer(firstName, lastName, phone.replaceAll("-", ""), email);
                parent.setCurrentCustomer(id);
                con.close();
                parent.CloseCustomerCreator();
            }
        });
    }
}
