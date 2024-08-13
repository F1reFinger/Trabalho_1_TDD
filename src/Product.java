import java.time.LocalTime;

class Product {

    TaxCalculator calc;
    String id;
    String description;
    String unitType;
    double price;

    Product(String descipriton, String unitType, double price, TaxCalculator calc) {
        this.id = LocalTime.now().toString();
        this.description = descipriton;
        this.unitType = unitType;
        this.price = price;
        this.calc = calc;
    };

    double getICMSTax(Address address) {
        return calc.calculateICMSTax(this.price, address.state);
    }

    double getMunicipalTax(Address address) {
        return calc.calculateMunicipalTax(this.price, address.state);
    }

}