import java.util.ArrayList;

class App {
    static void main(String[] args) {

        User userStandard = new User(
                "Teste 1",
                "123456",
                new Card("4296 13XX XXXX XXXX"),
                new Address("Brasília", "DF"),
                "special");

        User userPrime = new User(
                "Teste 2",
                "123456",
                new Card("4296 13XX XXXX XXXX"),
                new Address("Brasília", "DF"),
                "prime");

        User userSpecial = new User(
                "Teste 3",
                "123456",
                new Card("4296 13XX XXXX XXXX"),
                new Address("Brasília", "DF"),
                "standard");

        Product product1 = new Product("Maçã", "KG", 0.89);
        Product product2 = new Product("Celular", "UNI", 2000.00);

        final ArrayList<Product> products = new ArrayList<Product>();

        products.add(product1);
        products.add(product2);

        System.out.println("Produto 1" + " " + product1.price);
        System.out.println("Produto 2" + " " + product2.price);

        Buy buy = new Buy(userStandard, products, "pix", userStandard.card);

        System.out.println("userStandard");
        System.out.println("Discount" + " " + buy.calculateTotalDiscount());
        System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
        System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
        System.out.println("Freight" + " " + buy.calculateFreight());

        buy = new Buy(userPrime, products, "pix", userStandard.card);

        System.out.println("userPrime");
        System.out.println("Discount" + " " + buy.calculateTotalDiscount());
        System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
        System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
        System.out.println("Freight" + " " + buy.calculateFreight());

        buy = new Buy(userSpecial, products, "pix", userStandard.card);

        System.out.println("userSpecial");
        System.out.println("Discount" + " " + buy.calculateTotalDiscount());
        System.out.println("Municipal" + " " + buy.calculateTaxMunicipal());
        System.out.println("TaxICMS" + " " + buy.calculateTaxICMS());
        System.out.println("Freight" + " " + buy.calculateFreight());
    }
}
