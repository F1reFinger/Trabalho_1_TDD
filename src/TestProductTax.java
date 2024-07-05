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
        private double expectedTax;

        public TestProductTax(Product product, Address address, double expectedTax) {
            this.product = product;
            this.address = address;
            this.expectedTax = expectedTax;
        }

        @Parameters
        public static Collection<Object[]> getParameters() {
            // Define addresses and products for testing
            Address address1 = new Address("Anápolis", "GO");
            Address address2 = new Address("SomeCity", "XY");

            Product product1 = new Product("Celular", "UNI", 100.00);
            Product product2 = new Product("Laptop", "UNI", 2000.00);

            // Prepare test cases
            Object[][] testData = new Object[][] {
                { product1, address1, 0.0 },  // Expected tax for product1 and address1
                { product2, address1, 0.0 },  // Expected tax for product2 and address1
                { product1, address2, 0.0 },  // Expected tax for product1 and address2
                { product2, address2, 0.0 },  // Expected tax for product2 and address2
            };

            return Arrays.asList(testData);
        }

        @Test
        public void testGetICMSTax() {
            double actualTax = product.getICMSTax(address);
            assertEquals(expectedTax, actualTax, 0.001);  // Adjust delta as needed
        }

        @Test
        public void testGetMunicipalTax() {
            double actualTax = product.getMunicipalTax(address);
            assertEquals(expectedTax, actualTax, 0.001);  // Adjust delta as needed
        }
    }
