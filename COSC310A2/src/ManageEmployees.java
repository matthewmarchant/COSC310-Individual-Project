import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployees {

    JPanel ManageEmployeeScreen;
    private JFormattedTextField ManagerTitle;
    private JButton logOutButton;
    private JButton homeButton;
    private JTable EmployeeList;
    private JFormattedTextField AddEmployee;
    private JButton addEmployeeButton;
    private JLabel firstNameLabel;
    private JFormattedTextField FirstNameField;
    private JLabel usernameLabel;
    private JFormattedTextField usernameField;
    private JLabel managerLabel;
    private JCheckBox ManagerCheckbox;
    private JLabel PasswordTextBox;
    private JPasswordField PasswordField;
    private JLabel ManageEmployeesMessageOutput;
    private JLabel lastNameLabel;
    private JTextField LastNameField;

    private void updateList(){
        DefaultTableModel model = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        EmployeeList.setModel(model);
        model.addColumn("Name");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Manager");
        DBConnection con = new DBConnection();
        String[] usernames = con.getAllEmployees();
        for(String username : usernames){
            model.insertRow(0, new Object[] {username, con.getEmployeeNameByUsername(username), (con.isEmployeeManagerByUsername(username) ? "yes" : "no")});
        }
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
                String firstName = FirstNameField.getText();
                String lastName = LastNameField.getText();
                String username = usernameField.getText();
                String password = String.valueOf(PasswordField.getPassword());
                boolean manager = ManagerCheckbox.isSelected();
                if(firstName.equals("")){
                    ManageEmployeesMessageOutput.setText("Missing first name");
                    return;
                }
                if(lastName.equals("")){
                    ManageEmployeesMessageOutput.setText("Missing last name");
                    return;
                }
                if(username.equals("")){
                    ManageEmployeesMessageOutput.setText("Missing username");
                    return;
                }
                if(password.equals("")){
                    ManageEmployeesMessageOutput.setText("Missing password");
                    return;
                }
                DBConnection con = new DBConnection();
                for(String u : con.getAllEmployees()){
                    if(u.equals(username)){
                        ManageEmployeesMessageOutput.setText("Username already in use");
                        return;
                    }
                }
                con.addEmployee(firstName, lastName, username, password, manager);
                con.close();
                FirstNameField.setText("");
                LastNameField.setText("");
                usernameField.setText("");
                PasswordField.setText("");
                ManagerCheckbox.setSelected(false);
                updateList();
            }
        });
    }
}
