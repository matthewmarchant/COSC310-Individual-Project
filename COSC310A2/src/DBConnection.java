import java.sql.*;

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

    public void close(){
        try{
            con.close();
        }catch(Exception e) {}
    }
}
