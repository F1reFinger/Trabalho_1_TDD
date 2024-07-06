import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestProductTax {

    private Product product;
    private Address address;
    private double expectedICMSTax;
    private double expectedMunicipalTax;
    public TestProductTax(Product product, Address address, double expectedICMSTax, double expectedMunicipalTax) {
        this.product = product;
        this.address = address;
        this.expectedICMSTax = expectedICMSTax;
        this.expectedMunicipalTax = expectedMunicipalTax;
    }
    
    @Parameters
    public static Collection<Object[]> getParameters() {
        Address address1 = new Address("Anápolis", "GO");
        Address address2 = new Address("SomeCity", "XY");
        Address address3 = new Address("Gama", "DF");

        Product product1 = new Product("Celular", "UNI", 100.00);
        Product product2 = new Product("Laptop", "UNI", 2000.00);
        Product product3 = new Product("teclado", "UNI", 157.00);

        // Prepare test cases
        Object[][] testData = new Object[][] {
            //Produto , entereço , valor do ICMS sobre o produto, valor da Taxa Municipal "0.0" caso DF
            // endereço 1 "GO"
            {product1, address1, 12.0, 4.0},
            {product2, address1, 240.0,  80.0},
            {product3, address1, 18.84, 6.28},

            // endereço 2 "XY"
            {product1, address2, 12.0, 4.0},
            {product2, address2, 240, 80},
            {product3, address2, 18.84,  6.28},

            // endereço 3 "DF"
            {product1, address3, 12.0, 4.0},
            {product2, address3, 240.0,  80.0},
            {product3, address3, 28.26,  0.0}

        };

        return Arrays.asList(testData);
    }


    @Test
    public voi GetICMSTax() {
        double icmsTaxValue = product.getICMSTax(address);
        assertEquals(expectedICMSTax, icmsTaxValue, 0.0);
    }

    @Test
    public void GetMunicipalTax() {
        double municipalTaxValue = product.getMunicipalTax(address);
        assertEquals(expectedMunicipalTax, municipalTaxValue, 0.0);
    }
}
