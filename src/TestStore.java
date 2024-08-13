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
    int expectedUserBuysCount;

    public TestStore(Store store, User user, boolean expectedSpecialStatus, int expectedUserBuysCount) {
        this.store = store;
        this.user = user;
        this.expectedSpecialStatus = expectedSpecialStatus;
        this.expectedUserBuysCount = expectedUserBuysCount;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Anápolis", "GO"));

        Card storeCard = new Card("4296 1312 1212 1234");
        Card nonStoreCard = new Card("1234 5624 2354 1234");
        TaxCalculator calc = new TaxCalculator();

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Celular", "UNI", 150.00, calc));

        User primeUser = new User("Prime User", "123456789", storeCard, addresses, "prime");
        User regularUser = new User("Regular User", "987654321", nonStoreCard, addresses, "regular");

        Buy primeBuy = new Buy(primeUser, products, "card", primeUser.card, addresses.get(0), true);

        Buy regularBuy = new Buy(regularUser, products, "card", regularUser.card, addresses.get(0), false);
        regularBuy.date = LocalDateTime.of(2024, 6, 1, 1, 1);

        Buy regularBuyTwo = new Buy(regularUser, products, "card", regularUser.card, addresses.get(0), false);

        Store storeWithPrimeUser = new Store();
        storeWithPrimeUser.addNewBuy(primeBuy);

        Store storeWithRegularUser = new Store();
        storeWithRegularUser.datetime = LocalDateTime.of(2024, 7, 1, 1, 1);
        storeWithRegularUser.addNewBuy(regularBuy);

        Store storeWithRegularUserTwo = new Store();
        storeWithRegularUserTwo.addNewBuy(regularBuyTwo);

        Object[][] resposta = new Object[][] {
                { storeWithPrimeUser, primeUser, false, 1 },
                { storeWithRegularUser, regularUser, true, 1 },
                { storeWithRegularUserTwo, regularUser, false, 1 }
        };

        return Arrays.asList(resposta);
    }

    @Test
    public void testUserIsSpecial() {
        assertEquals(expectedSpecialStatus, store.userIsSpecial(user));
    }

    @Test
    public void testGetUserBuys() {
        assertEquals(expectedUserBuysCount, store.getUserBuys(user.id).size());
    }
}
