import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

class Buy {
  String id;
  LocalDateTime date;
  User user;
  ArrayList<Product> products;
  String paymentType;
  Address buyAddress;
  Card card;
  private boolean shouldUseCashback = false;

  Buy(User user, ArrayList<Product> products, String paymentType, Card card, Address buyAddress,
      boolean shouldUseCashback) {
    this.id = LocalTime.now().toString();
    this.user = user;
    this.products = products;
    this.date = LocalDateTime.now();
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

  double calculateSpecialDiscount() {
    double subTotalWithTaxes = calculateSubTotalWithTaxes();
    if (card != null && card.isFromStoreCard()) {
      return subTotalWithTaxes * 0.2;
    }
    return subTotalWithTaxes * 0.1;
  }

  double calculateSubTotalWithTaxes() {
    return subTotal() + calculateTaxMunicipal() + calculateTaxICMS();
  }

  double calculatePrimeUserDiscount() {
    return user.getCashbackBalance();
  }

  double calculateTotalDiscount() {
    if (user.userType == "special") {
      return this.calculateSpecialDiscount();
    } else if (user.userType == "prime" && shouldUseCashback) {
      return this.calculatePrimeUserDiscount();
    } else {
      return 0.0;
    }
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
    FreightCalculator freightCalculator = new FreightCalculator();
    return freightCalculator.calculateFreight(user, buyAddress);
  }
}
