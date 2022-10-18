import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    JPanel ManagerScreen;
    private JFormattedTextField managerFormattedTextField;
    private JButton manageEmployeesButton;
    private JButton pendingOrdersButton;
    private JButton suppliersButton;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JList list1;
    private JList list2;
    private JProgressBar progressBar1;
    private JFormattedTextField thisMonthSSalesFormattedTextField;
    private JFormattedTextField recentSalesFormattedTextField;
    private JList list3;
    private JButton logOutButton;

    public Manager() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Login().LoginPanel);
                Main.frame.pack();
            }
        });
    }
}
