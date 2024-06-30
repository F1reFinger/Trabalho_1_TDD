import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

class Address {
    String state;
    String city;

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Test
    String getRegion() {
        return "";
    }

    @Test
    public boolean isCapital() {
        return true;
    }
}
