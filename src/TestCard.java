import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TestCard {

    @Test
    public void testIsFromStoreCard() {
        Card storeCard = new Card("4296 1312 1212 1234");
        Card nonStoreCard = new Card("1234 5624 2354 1234");

        assertTrue(storeCard.isFromStoreCard());
        assertFalse(nonStoreCard.isFromStoreCard());
    }
}
