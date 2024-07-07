import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Buy {
  String id;
  LocalDateTime date;
  User user;
  ArrayList<Product> products;
  String paymentType;
  Address buyAddress;
  Card card;
  private boolean shouldUseCashback = false;

  private static final Map<String, Map<Boolean, Double>> FREIGHT_VALUES;

  static {
    FREIGHT_VALUES = new HashMap<>();

    Map<Boolean, Double> centroOesteMap = new HashMap<>();
    centroOesteMap.put(true, 10.0);
    centroOesteMap.put(false, 13.0);
    FREIGHT_VALUES.put("Centro-Oeste", centroOesteMap);

    Map<Boolean, Double> nordesteMap = new HashMap<>();
    nordesteMap.put(true, 15.0);
    nordesteMap.put(false, 18.0);
    FREIGHT_VALUES.put("Nordeste", nordesteMap);

    Map<Boolean, Double> norteMap = new HashMap<>();
    norteMap.put(true, 20.0);
    norteMap.put(false, 25.0);
    FREIGHT_VALUES.put("Norte", norteMap);

    Map<Boolean, Double> sudesteMap = new HashMap<>();
    sudesteMap.put(true, 7.0);
    sudesteMap.put(false, 10.0);
    FREIGHT_VALUES.put("Sudeste", sudesteMap);

    Map<Boolean, Double> sulMap = new HashMap<>();
    sulMap.put(true, 10.0);
    sulMap.put(false, 13.0);
    FREIGHT_VALUES.put("Sul", sulMap);
  }

  Buy(User user, ArrayList<Product> products, String paymentType, Card card, Address buyAddress, boolean shouldUseCashback) {
    this.id = LocalTime.now().toString();
    this.user = user;
    this.products = products;
    this.date = LocalDateTime.of(2024, 6, 1, 1, 1);
    this.paymentType = paymentType;
    this.card = card;
    this.buyAddress = buyAddress;
    this.shouldUseCashback = user.userType == "prime" ? shouldUseCashback : false;
  };

  double total() {
    return subTotal()
        + calculateTaxMunicipal()
        + calculateTaxICMS()
        + calculateFreight()
        - calculateTotalDiscount();
  }

  double subTotal() {
    double total = 0.0;

    for (Product product : products) {
      total += product.price;
    }

    return total;
  }

  double calculateTotalDiscount() {
    if (user.userType == "special") {
      double subTotalWithTaxes = subTotal() + calculateTaxMunicipal() + calculateTaxICMS();
      if (card.isFromStoreCard()) return subTotalWithTaxes * 0.2;
      return subTotalWithTaxes * 0.1;
    } else if (user.userType == "prime" && shouldUseCashback) {
      return user.getCashbackBalance();
    }
    return 0.0;
  }

  double calculateTaxMunicipal() {
    double totalMunicipal = 0.0;

    for (Product product : products) {
      totalMunicipal += product.getMunicipalTax(buyAddress); 
    }

    return totalMunicipal;
  }

  double calculateTaxICMS() {
    double totalICMS = 0.0;

    for (Product product : products) {
      totalICMS += product.getICMSTax(buyAddress); 
    }

    return totalICMS;
  }

  double calculateFreight() {
    String region = this.buyAddress.getRegion();
    Boolean isCapital = this.buyAddress.isCapital();

    double freightValue;

    if (this.buyAddress.state == "DF") freightValue = 5.0;
    else freightValue = FREIGHT_VALUES.get(region).get(isCapital);

    if (user.userType == "special") {
      return freightValue * 0.7;
    } else if (user.userType == "standard") {
      return freightValue;
    }

    return 0.0;
  }

}
