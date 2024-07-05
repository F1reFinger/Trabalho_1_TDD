import static org.junit.Assert.assertEquals;

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
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Anápolis", "GO"));

        Card storeCard = new Card("4296 1312 1212 1234");
        Card nonStoreCard = new Card("1234 5624 2354 1234");

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Celular", "UNI", 150.00));

        User primeUser = new User("Prime User", "123456789", storeCard, addresses, "prime");
        User regularUser = new User("Regular User", "987654321", nonStoreCard, addresses, "regular");

        Buy primeBuy = new Buy(primeUser, products, "card", primeUser.card);
        Buy regularBuy = new Buy(regularUser, products, "card", regularUser.card);

        Store storeWithPrimeUser = new Store();
        storeWithPrimeUser.addNewBuy(primeBuy);

        Store storeWithRegularUser = new Store();
        storeWithRegularUser.addNewBuy(regularBuy);

        Object[][] resposta = new Object[][] {
                { storeWithPrimeUser, primeUser, false },
                { storeWithRegularUser, regularUser, true }
        };

        return Arrays.asList(resposta);
    }

    @Test
    public void testUserIsSpecial() {
        assertEquals(expectedSpecialStatus, store.userIsSpecial(user));
    }
}
