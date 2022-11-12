import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppliersTest extends DBConnection{
    @Test
    void getSupplierNameByIdTest() {
        assertEquals(getSupplierNameById(1),"CoolGear");
    }

    @Test
    void getProductSupplierTest() {
        assertEquals(getProductSupplier(1),"CoolGear");
    }

}