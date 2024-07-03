import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

class Buy {
    String id;
    LocalDateTime date;
    User user;
    ArrayList<Product> products;
    // ENUM ("pix, money, credit")
    String paymentType;
    // if(paymentType == "credit") cardId SHOULD BE != NULL
    Card card;
    double freight;
    // double totalDiscount;
    // double taxICMS;
    // double taxMunicipal;

    Buy(User user, ArrayList<Product> products, String paymentType, Card card) {
        this.id = LocalTime.now().toString();
        // forcing buy for last month
        this.date = LocalDateTime.of(2024, 6, 1, 1, 1);
        this.user = user;
        this.products = products;
        this.paymentType = paymentType;
        this.card = card;
    }

    @Test
    double total() {
        return subTotal()
                + calculateTaxMunicipal()
                + calculateTaxICMS()
                + calculateFreight()
                - calculateTotalDiscount();
    }

    @Test
    double subTotal() {
        double total = 0.0;

        for (Product product : products) {
            total += product.price;
        }

        return total;
    }

    @Test
    double calculateTotalDiscount() {
        if (user.userType == "special") {
            // Beneficios desconto de 10% (*0.9) no valor da compra
            // Recebe mais 10% (*0.9) de desconto se utilizar o cartão da empresa
            return 1.0;
        } else if (user.userType == "prime") {
            return 2.0;
        } else if (user.userType == "standard") {
            return 3.0;
        }
        return 0.0;
    }

    @Test
    double calculateTaxMunicipal() {
        if (user.userType == "special") {
            return 1.0;
        } else if (user.userType == "prime") {
            return 2.0;
        } else if (user.userType == "standard") {
            return 3.0;
        }
        return 0.0;
    }

    @Test
    double calculateTaxICMS() {
        // - Fora do DF 12% de ICMS e 4% de imposto municipal
        // - Dentro do DF 18% de ICMS e 0% de imposto municipal
        if (user.userType == "special") {
            return 1.0;
        } else if (user.userType == "prime") {
            return 2.0;
        } else if (user.userType == "standard") {
            return 3.0;
        }
        return 0.0;

    }

    @Test
    double calculateFreight() {
        // --------------------------- Capital -- Interior
        // ----- Distrito Federal ---- R$ 5,00 -- --------
        // ----- Regiao Centro-oeste - R$ 10,00 - R$ 13,00
        // ----- Regiao Nordeste ----- R$ 15,00 - R$ 18,00
        // ----- Regiao Norte R$ ----- R$ 20,00 - R$ 25,00
        // ----- Regiao Sudeste ------ R$ 7,00 -- R$ 10,00
        // ----- Regiao Sul ---------- R$ 10,00 - R$ 13,00
        if (user.userType == "special") {
            // Possui 30% (*0.7) de desconto no valor do frete
            return 1.0;
        } else if (user.userType == "prime") {
            // Frete grátis
            return 2.0;
        } else if (user.userType == "standard") {
            return 3.0;
        }
        return 0.0;
    }

}
