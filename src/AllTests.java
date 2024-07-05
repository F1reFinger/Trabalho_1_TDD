
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddress.class, TestCard.class, TestUser.class, TestStore.class })
public class AllTests {

}
