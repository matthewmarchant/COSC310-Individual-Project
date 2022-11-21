

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twilio.Twilio;
import org.junit.jupiter.api.Test;
import com.twilio.type.PhoneNumber;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    private JButton addCustomerButton;
    private JButton eMailReceiptButton;
    private JLabel customerNameLabel;
    private JFormattedTextField SelectedCustomerName;
    private JFormattedTextField customerIdTextInput;
    private JButton selectCustomerButton;
    private JFormattedTextField customerIDFormattedTextField;
    private JButton textReceiptButton;
    private JComboBox CurrencySelector;
    private JFormattedTextField SelectCurrency;
    double orderTotal;
    ArrayList<Integer> currentSale;
    static String Currency;
    JFrame customercreator = new JFrame("Customer Creator");
    static JFrame emailer = new JFrame("EMail Confirmation");
    static JFrame texter = new JFrame("Text Confirmation");
    int selectedCustomer = -999;
    boolean saleCompleted = false;
    int lastPurchaseId = -999;
    JsonObject conversions;
    public static final String ACCOUNT_SID = "AC11e10c94dcbd45e23305be33f1627825";
    public static final String AUTH_TOKEN = "b0f8db9a77edb2da3ff078ee613230b0";

    public Cashier() throws IOException {
        orderTotal = 0;
        currentSale = new ArrayList<Integer>();
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

        // Curreny Conversion Setup
        String url_str = "https://v6.exchangerate-api.com/v6/1ba94872fcabbb2998a1310c/latest/CAD";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        //Get All Current Exchange Rates
        conversions = jsonobj.get("conversion_rates").getAsJsonObject();


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
                if(saleCompleted){
                    saleCompleted = false;
                    setCurrentCustomer(-999);
                    eMailReceiptButton.setEnabled(false);
                    textReceiptButton.setEnabled(false);
                }
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
                    Currency = String.valueOf(CurrencySelector.getSelectedItem());
                    String name = con.getProductName(productId);
                    con.changeProductStock(productId, -1);
                    currentSale.add(productId);
                    if(Currency == "CAD"){
                        ItemDisplay.setText(ItemDisplay.getText() + name + " $" + price + "\n");}
                    else{
                        double ConvertedPrice = 0;
                        try {
                            ConvertedPrice = CurrencyConverter(price,Currency);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        ItemDisplay.setText(ItemDisplay.getText() + name + " $" + price + " ---> "+ moneyFormat.format(ConvertedPrice) + " " + Currency + "\n");
                    }
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
                if(Currency!="CAD"){
                    try {
                        ItemDisplay.setText(ItemDisplay.getText()+"Converted: Total: "+moneyFormat.format(CurrencyConverter(orderTotal,Currency))+" "+Currency+"\n");
                    } catch (IOException ex) {
                        System.out.println(e);
                    }
                }
                saleCompleted = true;
                eMailReceiptButton.setEnabled(true);
                textReceiptButton.setEnabled(true);
                DBConnection con = new DBConnection();
                lastPurchaseId = con.recordSale(currentSale, selectedCustomer);
                currentSale.clear();
                orderTotal = 0;
                TotalDisplay.setText(moneyFormat.format(orderTotal));
                con.close();
            }
        });

        selectCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = customerIdTextInput.getText();
                if(!id.matches("^[1-9]\\d*$")){
                    messageOutput.setText("invalid customer ID");
                    SelectedCustomerName.setText("");
                    selectedCustomer = -999;
                    return;
                }
                setCurrentCustomer(Integer.parseInt(id));
            }
        });
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customercreator.setContentPane(new CustomerCreator(Cashier.this).CustomerScreen);
                customercreator.pack();
                customercreator.setVisible(true);
            }
        });

        eMailReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection con = new DBConnection();
                String emailto = con.getCustomerEmailById(selectedCustomer);
                EMail.SetEmail(emailto);
                emailer.setContentPane(new EMail(Cashier.this).ConfirmEmail);
                emailer.pack();
                emailer.setVisible(true);
                saleCompleted = false;
                setCurrentCustomer(-999);
                eMailReceiptButton.setEnabled(false);
                textReceiptButton.setEnabled(false);
            }
        });
        textReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection con = new DBConnection();
                String TextTo = con.getCustomerPhoneById(selectedCustomer);
                Text.SetPhone(TextTo);
                texter.setContentPane(new Text(Cashier.this).ConfirmText);
                texter.pack();
                texter.setVisible(true);
                saleCompleted = false;
                setCurrentCustomer(-999);
                textReceiptButton.setEnabled(false);
                eMailReceiptButton.setEnabled(false);
            }
        });
    }
    public void CloseCustomerCreator(){
        customercreator.dispose();
    }

    public void setCurrentCustomer(int id){
        if(id == -999){
            selectedCustomer = -999;
            messageOutput.setText("");
            customerIdTextInput.setText("");
            SelectedCustomerName.setText("");
            return;
        }
        DBConnection con = new DBConnection();
        selectedCustomer = id;
        String name = con.getCustomerNameById(selectedCustomer);
        SelectedCustomerName.setText(name);
        con.close();
        if(name == null){
            selectedCustomer = -999;
            messageOutput.setText("invalid customer ID");
            return;
        }
        messageOutput.setText("");
    }
    public void SendEmail(String EMail){
        emailer.dispose();
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.office365.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.transport.protocol","smtp");

        String myAccountEmail = "cosc310group4@outlook.com";
        String password = "Cosc310Password";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(EMail));
            msg.setSubject("Your Purchase from The Group 4 Store:");
            DBConnection con = new DBConnection();
            msg.setText(":"+"\n\nThank you for your purchase!\nHere is your receipt:\n" + con.getSaleInfoById(lastPurchaseId) + "\n\n" + con.getSaleItemsById(lastPurchaseId));
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void SendText(String phone){
        texter.dispose();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String FormattedPhone = "+1"+phone;
        DBConnection con = new DBConnection();
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(FormattedPhone),
                new PhoneNumber("+14058701265"),
                "Thank you for your purchase!\nHere is your receipt:\n" + con.getSaleInfoById(lastPurchaseId) + "\n\n" + con.getSaleItemsById(lastPurchaseId)).create();
        System.out.println("Successfully sent. ID:"+message.getSid());
    }

    public double CurrencyConverter(double price,String Currency) throws IOException {
        //Get desired exchange rate
        double conversion = conversions.get(Currency).getAsDouble();
        //Convert price to correct currency
        double ConvertedCurrency = price*conversion;
        return ConvertedCurrency;
    }
}
