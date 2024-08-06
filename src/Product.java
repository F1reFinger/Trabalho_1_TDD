import java.time.LocalTime;
class Product {

    TaxCalculator calc = new TaxCalculator();

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

    /*double getICMSTax(Address address) {
      if (address.state.equals("DF")){
        return this.price * DF_ICMS_TAX_RATE;
      } else {
        return this.price * ICMS_TAX_RATE;
      }
    }

    double getMunicipalTax(Address address) {
      if (!address.state.equals("DF")){
        return this.price * MUNICIPAL_TAX_RATE;
      }
      return 0.0;
    }*/
    double getICMSTax(Address address){
        return calc.calculateICMSTax(this.price, address.state);
    }

    double getMunicipalTax(Address address) {
        return calc.calculateMunicipalTax(this.price, address.state);
      }

}