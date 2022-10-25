import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Properties;

public class Cashier {
    public JPanel CashierScreen;
    private JButton logOutButton;
    private JButton a1Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton clearButton;
    private JButton addItemButton;
    private JTextField CalculatorDisplay;
    private JTextField TotalDisplay;
    private JButton removeItemButton;
    private JButton recordSaleButton;
    private JLabel messageOutput;
    private JTextPane ItemDisplay;
    private JFormattedTextField cashierFormattedTextField;
    private JTextField totalTextField;
    private JFormattedTextField newSaleFormattedTextField;
    private JButton addCustomerButton;
    private JButton eMailReceiptButton;
    double orderTotal;
    ArrayList<Integer> currentSale;
    static JFrame customercreator = new JFrame("Customer Creator");
    static JFrame emailer = new JFrame("EMail Confirmation");

    public Cashier() {
        orderTotal = 0;
        currentSale = new ArrayList<Integer>();
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a1Button.getText());
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a2Button.getText());
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a3Button.getText());
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a4Button.getText());
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a5Button.getText());
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a6Button.getText());
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a7Button.getText());
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a8Button.getText());
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a9Button.getText());
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CalculatorDisplay.getText().equals("")) return;
                int productId = Integer.parseInt(CalculatorDisplay.getText());
                DBConnection con = new DBConnection();
                if(!con.checkProductExists(productId)){
                    messageOutput.setText("Product ID does not exist");
                }else if(con.getProductStock(productId) < 1){
                    messageOutput.setText("Product out of stock");
                }else {
                    messageOutput.setText("");
                    double price = con.getProductPrice(productId);
                    String name = con.getProductName(productId);
                    con.changeProductStock(productId, -1);
                    currentSale.add(productId);
                    ItemDisplay.setText(ItemDisplay.getText() + name + " $" + price + "\n");
                    orderTotal += price;
                    TotalDisplay.setText(moneyFormat.format(orderTotal));
                }
                con.close();
                CalculatorDisplay.setText("");
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CalculatorDisplay.getText().equals("")) return;
                int productId = Integer.parseInt(CalculatorDisplay.getText());
                DBConnection con = new DBConnection();
                if(!con.checkProductExists(productId)){
                    messageOutput.setText("Product ID does not exist");
                }else if(!currentSale.contains(productId)){
                    messageOutput.setText("Item is not in current sale");
                }else{
                    messageOutput.setText("");
                    double price = con.getProductPrice(productId);
                    String name = con.getProductName(productId);
                    con.changeProductStock(productId, +1);
                    currentSale.remove(Integer.valueOf(productId));
                    ItemDisplay.setText(ItemDisplay.getText() + "Removed " + name + " -$" + price + "\n");
                    orderTotal -= price;
                    TotalDisplay.setText(moneyFormat.format(orderTotal));
                }
                con.close();
                CalculatorDisplay.setText("");
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setContentPane(new Login().LoginPanel);
                Main.frame.pack();
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText(CalculatorDisplay.getText() + a0Button.getText());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorDisplay.setText("");
            }
        });
        recordSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentSale.size() == 0) return;
                ItemDisplay.setText(ItemDisplay.getText() + "Sale recorded. Total: " + moneyFormat.format(orderTotal) + "\n");
                DBConnection con = new DBConnection();
                con.recordSale(currentSale);
                currentSale.clear();
                orderTotal = 0;
                TotalDisplay.setText(moneyFormat.format(orderTotal));
                con.close();

            }
        });
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customercreator.setContentPane(new CustomerCreator().CustomerScreen);
                customercreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customercreator.pack();
                customercreator.setVisible(true);
            }
        });

        eMailReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailto = "matthewmarchant15@gmail.com"; //replace with get current customer email
                EMail.SetEmail(emailto);
                emailer.setContentPane(new EMail().ConfirmEmail);
                emailer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                emailer.pack();
                emailer.setVisible(true);
            }
        });
    }
    public static void CloseCustomerCreator(){
        customercreator.dispose();
    }
    public static void SendEmail(String EMail){
        emailer.dispose();
        // get sale information
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.office365.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "cosc310group4@outlook.com";
        String password = "Cosc310Password";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(EMail));
            msg.setSubject("Your Purchase from Group 4 Shop:");
            msg.setText("Test");
            Transport.send(msg);
            System.out.println("sent message to: "+EMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
