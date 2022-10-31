import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Orders {
    private JFormattedTextField ManagerTitle;
    private JButton logOutButton;
    private JButton homeButton;
    private JTable OrderTable;
    private JFormattedTextField supplierFormattedTextField;
    private JComboBox supplierDropDown;
    JPanel OrderScreen;
    private JButton clearOrderButton;
    private JButton submitOrderButton;


    public Orders() {
        DBConnection con = new DBConnection();
        int[] supplierIds = con.getAllSuppliers();
        String[] suppliers = new String[supplierIds.length];
        int i = 0;
        for(int id : supplierIds){
            suppliers[i++] = con.getSupplierNameById(id);
        }
        supplierDropDown.setModel(new DefaultComboBoxModel(suppliers));
        DefaultTableModel model = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        OrderTable.setModel(model);
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Stock");
        model.addColumn("# to Order");
        int[] ids = con.getAllProductIds();
        String supplier = supplierDropDown.getSelectedItem().toString();
        for(int id : ids){
            if(supplier.equals(con.getProductSupplier(id))) {
                String name = con.getProductName(id);
                double price = con.getProductPrice(id);
                int stock = con.getProductStock(id);
                int OrderValue = 0;
                model.insertRow(0, new Object[]{"" + id, name, price, "" + stock, "" + OrderValue});
            }
        }
        con.close();
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
        supplierDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection con = new DBConnection();
                DefaultTableModel model = new DefaultTableModel() {
                    boolean[] canEdit = new boolean[]{
                            false, false, false, false, true
                    };
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                OrderTable.setModel(model);
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Stock");
                model.addColumn("# to Order");
                int[] ids = con.getAllProductIds();
                String supplier = supplierDropDown.getSelectedItem().toString();
                for(int id : ids){
                    if(supplier.equals(con.getProductSupplier(id))) {
                        String name = con.getProductName(id);
                        double price = con.getProductPrice(id);
                        int stock = con.getProductStock(id);
                        int OrderValue = 0;
                        model.insertRow(0, new Object[]{"" + id, name, price, "" + stock, "" + OrderValue});
                    }
                }
                con.close();
            }
        });
        clearOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;(OrderTable.getModel().getRowCount())>j;j++){
                    OrderTable.getModel().setValueAt("0",j,4);
                }
            }
           });
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add products to new order and save order in database
            }
        });
    }

}
