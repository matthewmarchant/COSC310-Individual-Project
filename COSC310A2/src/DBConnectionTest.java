import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest extends DBConnection{

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
    void getEmployeeNameByUsernameTest() {
        assertEquals(getEmployeeNameByUsername("stumcg"),"Stu McGorman");
        assertEquals(getEmployeeNameByUsername("invalid"),"");
    }

    @Test
    void isEmployeeManagerByUsernameTest() {
        assertTrue(isEmployeeManagerByUsername("stumcg"));
        assertFalse(isEmployeeManagerByUsername("bbrown"));
    }

    @Test
    void checkProductExistsTest() {
        assertTrue(checkProductExists(1));
        assertFalse(checkProductExists(999));
    }

    @Test
    void SetAndGetProductStockTest() {
        setProductStock(1,5);
        assertEquals(getProductStock(1),5);
    }

    @Test
    void SetAndGetProductPriceTest() {
        setProductPrice(1,669.99);
        assertEquals(getProductPrice(1),669.99);
    }

    @Test
    void getProductNameTest() {
        assertEquals(getProductName(1),"Skis");
    }

    @Test
    void getProductSupplierTest() {
        assertEquals(getProductSupplier(1),"CoolGear");
    }

    @Test
    void getLowStockProductIdsTest() {
        setProductStock(1,0);
        setProductStock(2,10);
        int[] lowstockproducts = getLowStockProductIds();
        boolean haslowstock = false;
        boolean nolowstock = false;
        for(int x : lowstockproducts){
            if(x == 1){
                haslowstock = true;
                break;
            }
        }
        for(int x : lowstockproducts){
            if(x == 2){
                nolowstock = true;
                break;
            }
        }
        assertTrue(haslowstock);
        assertFalse(nolowstock);
    }

    @Test
    void getSupplierNameByIdTest() {
        assertEquals(getSupplierNameById(1),"CoolGear");
    }
}