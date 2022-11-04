import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EMail {
    private JFormattedTextField sendingEMailToFormattedTextField;
    private JFormattedTextField EMailTo;
    private JButton sendEMailButton;
    static String Email;
    JPanel ConfirmEmail;

    public EMail(Cashier parent) {
        EMailTo.setText(Email);
        sendEMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.SendEmail(Email);
            }
        });
    }
    public static void SetEmail(String EmailTo){
        Email = EmailTo;
    }
}
