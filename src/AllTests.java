
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddress.class, TestCard.class, TestUser.class, TestBuy.class, TestStore.class, TestProductTax.class })
public class AllTests {

}
