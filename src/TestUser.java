import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUser {
    User user;
    Buy buy;
    Object expectedCashback;

    public TestUser(User user, Buy buy, Object expectedCashback) {
        this.user = user;
        this.expectedCashback = expectedCashback;
        this.buy = buy;
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

        Buy primeBuy = new Buy(primeUser, products, "card", primeUser.card, addresses.getFirst(), true);
        Buy primeBuyPix = new Buy(primeUser, products, "pix", null, addresses.getFirst(), true);
        Buy regularBuy = new Buy(regularUser, products, "card", regularUser.card, addresses.getFirst(), false);

        Object[][] resposta = new Object[][] {
                { new User("Prime User", "123456789", storeCard, addresses, "prime"), primeBuy, 5.0 },
                { new User("Prime User", "123456789", nonStoreCard, addresses, "prime"), primeBuyPix, 3.0 },
                { new User("Regular User", "987654321", nonStoreCard, addresses, "regular"), regularBuy, -1.0 },
        };

        return Arrays.asList(resposta);
    }

    @Test
    public void test() {
        user.setCashbackBalance(buy);
        assertEquals(expectedCashback, user.getCashbackBalance());
    }
}
