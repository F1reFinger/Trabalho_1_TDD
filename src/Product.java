import java.time.LocalTime;

import org.junit.Test;

class Product {
    String id;
    String description;
    String unitType;
    double price;

    Product(String descipriton, String unitType, double price) {
        this.id = LocalTime.now().toString();
        this.description = descipriton;
        this.unitType = unitType;
        this.price = price;
    };

    @Test
    double getICMSTax(Address adress) {
        return 0.0;
    }

    @Test
    double getMunicipalTax(Address adress) {
        return 0.0;
    }
}
