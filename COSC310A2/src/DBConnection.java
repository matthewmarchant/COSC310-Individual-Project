import java.sql.*;

public class DBConnection{
    private Connection con;
    public DBConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/310 project","root","now2is41");
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select * from product");
             System.out.println(rs);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void test(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Staff");
            System.out.println(rs);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean checkLogin(String user, String pword, Boolean manager){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT password, manager FROM Staff WHERE username = \"" + user + "\";");
            if(!rs.next()) {
                System.out.println("invalid username");
                return false;
            }
            if(rs.getString("password").equals(pword)) {
                System.out.println("invalid password");
                return false;
            }
            if(manager = true && rs.getBoolean("manager") == false){
                System.out.println("Not a manager");
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
