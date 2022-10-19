import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    JPanel ManagerScreen;
    private JTabbedPane tabbedPane;
    private JTable InventoryTable; // Display Inventory Data from Database
    private JList SalesList;
    private JList WarningsList;
    private JList OrdersList;
    private JFormattedTextField SalesTitle;
    private JFormattedTextField RecentSalesTitle;
    private JProgressBar SalesProgress;
    private JFormattedTextField managerFormattedTextField;
    private JButton homeButton;
    private JButton pendingOrdersButton;
    private JButton suppliersButton;
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
