public class TaxCalculator {
    public static final double DF_ICMS_TAX_RATE = 0.18;
    public static final double ICMS_TAX_RATE = 0.12;
    public static final double MUNICIPAL_TAX_RATE = 0.04;

    public double calculateICMSTax(double price, String state) {
        if (state.equals("DF")) {
            return price * DF_ICMS_TAX_RATE;
        } else {
            return price * ICMS_TAX_RATE;
        }
    }

    public double calculateMunicipalTax(double price, String state) {
        if (!state.equals("DF")) {
            return price * MUNICIPAL_TAX_RATE;
        }
        return 0.0;
    }
}
