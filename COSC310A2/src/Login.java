import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPasswordField passwordField;
    private JFormattedTextField COSC310LoginFormattedTextField;
    private JButton cashierLoginButton;
    private JButton managerLoginButton;
    private JFormattedTextField usernameFormattedTextField;
    private JFormattedTextField passwordFormattedTextField;
    JPanel LoginPanel;
    private JTextPane textPane1;
    private JFormattedTextField usernameFormattedTextField1;

    public Login() {
        managerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Need to add: if login is valid
                Main.frame.setContentPane(new Manager().ManagerScreen);
                Main.frame.pack();
            }
        });
        cashierLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Cashier().CashierScreen);
                Main.frame.pack();
            }
        });
    }
}
