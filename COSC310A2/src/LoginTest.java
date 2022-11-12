import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends DBConnection {

    @Test
    void TestLogin() {
        // Tests Valid Login
        assertTrue(checkLogin("stumcg","password", true));
        // Tests Invalid Password
        assertThrows(IllegalArgumentException.class ,()->checkLogin("stumcg","wrongpassword",true));
        // Test Invalid Username
        assertThrows(IllegalArgumentException.class ,()->checkLogin("invalidname","password",false));
        // Tests Valid login but not a manager
        assertThrows(IllegalArgumentException.class ,()->checkLogin("bbrown","password",true));
    }

    @Test
    public void getEmployeeNameByUsernameTest() {
        assertEquals(getEmployeeNameByUsername("stumcg"),"Stu McGorman");
        assertEquals(getEmployeeNameByUsername("invalid"),"");
    }

    @Test
    public void isEmployeeManagerByUsernameTest(){
        assertTrue(isEmployeeManagerByUsername("stumcg"));
        assertFalse(isEmployeeManagerByUsername("bbrown"));
    }
    
}