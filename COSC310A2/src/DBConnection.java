import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DBConnection{
    private Connection con;
    public DBConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cosc310database","root","password");
             Statement stmt=con.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean checkLogin(String user, String pword, Boolean manager){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT password, manager FROM Staff WHERE username = \"" + user + "\";");
            if(!rs.next()) {
                throw new IllegalArgumentException("invalid username");
            }
            if(!rs.getString("password").equals(pword)) {
                throw new IllegalArgumentException("invalid password");
            }
            if(manager && !rs.getBoolean("manager")){
                throw new IllegalArgumentException("Not a manager");
            }
            return true;
        }catch(IllegalArgumentException e){
            throw e;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean checkProductExists(int id){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Product WHERE id = " + id + ";");
            if(!rs.next()) {
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean checkProductInStock(int id){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT stock FROM Product WHERE id = " + id + ";");
            if(!rs.next()) {
                return false;
            }
            if(rs.getInt("stock") < 1){
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public double getProductPrice(int id){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT price FROM Product WHERE id = " + id + ";");
            if(!rs.next()) {
                return 0;
            }
            return rs.getDouble("price");
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public String getProductName(int id){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT product_name FROM Product WHERE id = " + id + ";");
            if(!rs.next()) {
                return null;
            }
            return rs.getString("product_name");
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void changeProductStock(int id, int amount){
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate("UPDATE Product SET stock = stock + "+ amount + " WHERE id = " + id + ";");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void recordSale(ArrayList<Integer> productIds)
    {
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO Purchase VALUES (Null, CURDATE(), Null);");
            ResultSet rs=stmt.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int purchaseId = rs.getInt(1);
            while(productIds.size() > 0){
                int productId = productIds.get(0);
                int amount = Collections.frequency(productIds, productId);
                productIds.removeAll(Collections.singletonList(productId));
                stmt.executeUpdate("INSERT INTO PurchasedProduct VALUES (" + purchaseId + ", " + productId + ", " + amount + ");");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void close(){
        try{
            con.close();
        }catch(Exception e) {}
    }
}
