import java.util.ArrayList;

public class App {
        public static void main(String[] args) {

                Store store = new Store();

                ArrayList<Address> addresses = new ArrayList<>();
                addresses.add(new Address("Brasília", "DF"));

                User userStandard = new User(
                                "Teste 1",
                                "123456",
                                new Card("4296 13XX XXXX XXXX"),
                                addresses,
                                "special");

                User userPrime = new User(
                                "Teste 2",
                                "123456",
                                new Card("4296 13XX XXXX XXXX"),
                                addresses,
                                "prime");

                User userSpecial = new User(
                                "Teste 3",
                                "123456",
                                new Card("4296 13XX XXXX XXXX"),
                                addresses,
                                "standard");

                TaxCalculator calc = new TaxCalculator();

                Product product1 = new Product("Maçã", "KG", 0.89, calc);
                Product product2 = new Product("Celular", "UNI", 2000.00, calc);

                final ArrayList<Product> products = new ArrayList<Product>();

                products.add(product1);
                products.add(product2);

                System.out.println("Produto 1" + " " + product1.price);
                System.out.println("Produto 2" + " " + product2.price);

                Buy buy = new Buy(userStandard, products, "pix", userStandard.card, addresses.get(0), false);
                userStandard.setCashbackBalance(buy);
                store.addNewBuy(buy);

                if (store.userIsSpecial(userStandard)) {
                        userStandard.userType = "special";
                }

                System.out.println("\nuserStandard");
                System.out.println("Discount" + " " + buy.calculateTotalDiscount());
                System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
                System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
                System.out.println("Freight" + " " + buy.calculateFreight());
                System.out.println("User is sepecial?" + " " + store.userIsSpecial(userStandard));

                buy = new Buy(userPrime, products, "pix", userPrime.card, addresses.get(0), false);
                userPrime.setCashbackBalance(buy);
                store.addNewBuy(buy);
                if (store.userIsSpecial(userPrime)) {
                        userPrime.userType = "special";
                }

                System.out.println("\nuserPrime");
                System.out.println("Discount" + " " + buy.calculateTotalDiscount());
                System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
                System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
                System.out.println("Freight" + " " + buy.calculateFreight());

                buy = new Buy(userSpecial, products, "pix", userSpecial.card, addresses.get(0), false);
                userSpecial.setCashbackBalance(buy);
                store.addNewBuy(buy);
                if (store.userIsSpecial(userSpecial)) {
                        userPrime.userType = "special";
                }

                System.out.println("\nuserSpecial");
                System.out.println("Discount" + " " + buy.calculateTotalDiscount());
                System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
                System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
                System.out.println("Freight" + " " + buy.calculateFreight());

        }
}
