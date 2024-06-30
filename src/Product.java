import java.time.LocalTime;

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
}
