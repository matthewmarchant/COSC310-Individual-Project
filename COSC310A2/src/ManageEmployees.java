import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployees {

    JPanel ManageEmployeeScreen;
    private JFormattedTextField ManagerTitle;
    private JButton logOutButton;
    private JButton homeButton;
    private JList EmployeeList;
    private JFormattedTextField AddEmployee;
    private JButton addEmployeeButton;
    private JFormattedTextField employeeNameFormattedTextField;
    private JFormattedTextField nameField;
    private JFormattedTextField usernameFormattedTextField;
    private JFormattedTextField usernameField;
    private JFormattedTextField managerFormattedTextField;
    private JCheckBox ManagerCheckbox;
    private JFormattedTextField PasswordTextBox;
    private JPasswordField PasswordField;

    private void updateList(){
        DBConnection con = new DBConnection();
        String[] usernames = con.getAllEmployees();
        String[] employees = new String[usernames.length];
        int i = 0;
        for(String username : usernames){
            employees[i++] = username + "        " + con.getEmployeeNameByUsername(username) + "      " + ((con.isEmployeeManagerByUsername(username) ? "*manager*" : ""));
        }
        EmployeeList.setListData(employees);
        con.close();
    }

    public ManageEmployees() {
        updateList();
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Manager().ManagerScreen);
                Main.frame.pack();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Login().LoginPanel);
                Main.frame.pack();
            }
        });
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = PasswordField.getText();
                boolean manager = ManagerCheckbox.isValid();
                // Add new employee to database
            }
        });
    }
}
