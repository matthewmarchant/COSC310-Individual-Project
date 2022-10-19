import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployees {

    JPanel ManageEmployeeScreen;
    private JFormattedTextField ManagerTitle;
    private JButton logOutButton;
    private JButton homeButton;
    private JButton orderFromSupplierButton;
    private JButton addSupplierButton;
    private JList EmployeeList;

    public ManageEmployees() {
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
    }
}
