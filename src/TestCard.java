import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCard {

    Card card;
    Object expected;

    public TestCard(Card card, Object expected) {
        this.card = card;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] resposta = new Object[][] {
                { new Card("4296 1312 1212 1234"), true },
                { new Card("1234 5624 2354 1234"), false },
        };
        return Arrays.asList(resposta);
    }

    @Test
    public void test() {
        assertEquals(expected, card.isFromStoreCard());
    }

}
