import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Text {
    JPanel ConfirmText;
    private JFormattedTextField sendingTextToFormattedTextField;
    private JFormattedTextField TextTo;
    private JButton sendTextButton;
    static String Phone;

    public Text(Cashier parent) {
        TextTo.setText(Phone);
        sendTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.SendText(Phone);
            }
        });
    }
    public static void SetPhone(String TextTo){
        Phone = TextTo;
    }
}

