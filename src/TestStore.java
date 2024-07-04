import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestStore {
    Store store;
    User user;
    boolean expectedSpecialStatus;

    public TestStore(Store store, User user, boolean expectedSpecialStatus) {
        this.store = store;
        this.user = user;
        this.expectedSpecialStatus = expectedSpecialStatus;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        ArrayList<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address("Anápolis", "GO"));

        Card storeCard = new Card("4296 1312 1212 1234");
        Card nonStoreCard = new Card("1234 5624 2354 1234");

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Celular", "UNI", 100.00));

        User primeUser = new User("Prime User", "123456789", storeCard, addresses, "prime");
        User regularUser = new User("Regular User", "987654321", nonStoreCard, addresses, "regular");
        User specialUser = new User("Special User", "192837465", storeCard, addresses, "special");

        Store store = new Store();

        Buy buyLastMonth = new Buy(specialUser, products, "card", storeCard);
        buyLastMonth.date = LocalDateTime.now().minusMonths(1);

        store.addNewBuy(buyLastMonth);

        Object[][] resposta = new Object[][] {
                { store, primeUser, false },
                { store, specialUser, true },
                { store, regularUser, false }
        };

        return Arrays.asList(resposta);
    }

    @Test
    public void testUserIsSpecial() {
        assertEquals(expectedSpecialStatus, store.userIsSpecial(user));
    }
}
