import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest extends DBConnection{
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
}