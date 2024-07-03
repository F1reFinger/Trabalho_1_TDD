import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestUser {

    User primeUser;
    User regularUser;
    Buy primeBuy;
    Buy regularBuy;

    @Before
    public void setup() {
        Card storeCard = new Card("4296 13XX XXXX XXXX");
        Card nonStoreCard = new Card("1234 56XX XXXX XXXX");

        ArrayList<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address("Anápolis", "GO"));

        Product product2 = new Product("Celular", "UNI", 100.00);

        ArrayList<Product> products = new ArrayList<Product>();

        products.add(product2);

        primeUser = new User("Prime User", "123456789", storeCard, addresses, "prime");
        regularUser = new User("Regular User", "987654321", nonStoreCard, addresses, "regular");

        primeBuy = new Buy(primeUser, products, "card", primeUser.card);
        regularBuy = new Buy(regularUser, products, "card", regularUser.card);
    }

    @Test
    public void testPrimeUserWithStoreCard() {
        primeUser.setCashbackBalance(primeBuy);
        assertEquals(5.0, primeUser.getCashbackBalance(), 0.0);
    }

    @Test
    public void testPrimeUserWithNonStoreCard() {
        primeUser.setCashbackBalance(regularBuy);
        assertEquals(3.0, primeUser.getCashbackBalance(), 0.0);
    }

    @Test
    public void testRegularUserWithNonStoreCard() {
        assertFalse(regularUser.setCashbackBalance(regularBuy));
        assertEquals(-1, regularUser.getCashbackBalance(), 0.0);
    }
}
