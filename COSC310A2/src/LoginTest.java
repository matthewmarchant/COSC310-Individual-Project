import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest extends DBConnection {

    @Test
    public void getEmployeeNameByUsernameTest() {
        String user = getEmployeeNameByUsername("bbrown");
        assertEquals("Bob Brown",user);
    }

    @Test
    public void isEmployeeManagerByUsernameTest(){
        Boolean employee = isEmployeeManagerByUsername("bbrown");
        assertEquals(false, employee);
    }
    
}