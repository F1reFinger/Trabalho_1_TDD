import java.time.LocalTime;

public class Product {
    String id;
    String description;
    String unitType;
    double price;
    TaxCalculator taxCalculator;

    public Product(String description, String unitType, double price) {
        this.id = LocalTime.now().toString();
        this.description = description;
        this.unitType = unitType;
        this.price = price;
        this.taxCalculator = new TaxCalculator();
    }

    public double getICMSTax(Address address) {
        return taxCalculator.calculateICMSTax(this.price, address.getRegion());
    }

    public double getMunicipalTax(Address address) {
        return taxCalculator.calculateMunicipalTax(this.price, address.getRegion());
    }
}
