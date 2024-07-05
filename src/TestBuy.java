import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestBuy {
  private User user;
  private ArrayList<Product> products;
  private String paymentType;
  private Card card;
  private Address buyAddress;
  private double expectedTotal;
  private double expectedFreight;
  private double expectedICMS;
  private double expectedMunicipal;
  private double expectedSubtotal;
  private double expectedTotalDiscount;
  private boolean shouldUseCashback;

  public TestBuy(User user, ArrayList<Product> products, String paymentType, Card card, Address buyAddress, boolean shouldUseCashback, double expectedTotal, double expectedFreight, 
      double expectedICMS, double expectedMunicipal, double expectedSubtotal, double expectedTotalDiscount) {
    this.user = user;
    this.products = products;
    this.paymentType = paymentType;
    this.card = card;
    this.buyAddress = buyAddress;
    this.shouldUseCashback = shouldUseCashback;

    this.expectedTotal = expectedTotal;
    this.expectedFreight = expectedFreight;
    this.expectedICMS = expectedICMS;
    this.expectedMunicipal = expectedMunicipal;
    this.expectedSubtotal = expectedSubtotal;
    this.expectedTotalDiscount = expectedTotalDiscount;
  }

  @Parameters
  public static Collection<Object[]> data() {
    ArrayList<Product> products1 = new ArrayList<>(
      Arrays.asList(
        new Product("some description 1", "unit type", 100.00),
        new Product("some description 2", "unit type", 200.00)
      )
    );

    ArrayList<Product> products2 = new ArrayList<>(
      Arrays.asList(
        new Product("some description 3", "unit type", 150.0),
        new Product("some description 4", "unit type", 250.0)
      )
    );

    Card cardMock = new Card("1234 5678 9101 1121");
    Card companyCardMock = new Card("4296 1300 1234 1234");

    ArrayList<Address> addressesMock = new ArrayList<>(
        Arrays.asList(
            new Address("Brasília", "DF"),
            new Address("Arcoverde", "PE"),
            new Address("São Paulo", "SP"),
            new Address("Porto Alegre", "RS"),
            new Address("Ananás", "TO")
        )
    );

    Special userSpecialMock = new Special("special user", "999999999", cardMock, addressesMock, "special");
    Prime userPrimeMock = new Prime("prime user", "999999999", cardMock, addressesMock, "prime");
    Standard userStandardMock = new Standard("standard user", "999999999", cardMock, addressesMock, "standard");
    
    Buy cashbackBuyMock = new Buy(userPrimeMock, products2, "money", cardMock, addressesMock.get(2), false);
    userPrimeMock.setCashbackBalance(cashbackBuyMock);

    System.out.println(addressesMock.get(1).state);

    return Arrays.asList(new Object[][] {
        { userSpecialMock, products1, "credit", companyCardMock, addressesMock.get(0), false, 286.7, 3.5, 54.0, 0.0, 300.0, 70.8 },
        { userSpecialMock, products2, "credit", cardMock, addressesMock.get(1), false, 430.20000000000005, 12.6, 48.0, 16.0, 400.0, 46.400000000000006 },
        { userPrimeMock, products2, "money", cardMock, addressesMock.get(2), true, 452.0, 0.0, 48.0, 16.0, 400.0, 12.0 },
        { userPrimeMock, products2, "money", cardMock, addressesMock.get(3), false, 464.0, 0.0, 48.0, 16.0, 400.0, 0.0 },
        { userStandardMock, products1, "pix", cardMock, addressesMock.get(4), false, 373.0, 25.0, 36.0, 12.0, 300.0, 0.0 }
    });
  }

  @Test
  public void testBuyTotal() {
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedTotal, buy.total(), 0.00);
  }

  @Test
  public void testCalculateTaxICMS() {
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedICMS, buy.calculateTaxICMS(), 0.0);
  }

  @Test
  public void testCalculateTaxMunicipal() {
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedMunicipal, buy.calculateTaxMunicipal(), 0.0);
  }

  @Test
  public void testCalculateFreight() {
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedFreight, buy.calculateFreight(), 0.00);
  }

  @Test
  public void testBuySubTotal(){
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedSubtotal, buy.subTotal(), 0.00);
  }

  @Test
  public void testCalculateTotalDiscount(){
    Buy buy = new Buy(user, products, paymentType, card, buyAddress, shouldUseCashback);
    assertEquals(expectedTotalDiscount, buy.calculateTotalDiscount(), 0.00);
  }

}
