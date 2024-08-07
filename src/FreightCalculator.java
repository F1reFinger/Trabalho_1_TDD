import java.util.HashMap;
import java.util.Map;

public class FreightCalculator {
  private static final Map<String, Map<Boolean, Double>> FREIGHT_VALUES;

  static {
    FREIGHT_VALUES = new HashMap<>();

    Map<Boolean, Double> centroOesteMap = new HashMap<>();
    centroOesteMap.put(true, 10.0);
    centroOesteMap.put(false, 13.0);
    FREIGHT_VALUES.put("Centro-Oeste", centroOesteMap);

    Map<Boolean, Double> nordesteMap = new HashMap<>();
    nordesteMap.put(true, 15.0);
    nordesteMap.put(false, 18.0);
    FREIGHT_VALUES.put("Nordeste", nordesteMap);

    Map<Boolean, Double> norteMap = new HashMap<>();
    norteMap.put(true, 20.0);
    norteMap.put(false, 25.0);
    FREIGHT_VALUES.put("Norte", norteMap);

    Map<Boolean, Double> sudesteMap = new HashMap<>();
    sudesteMap.put(true, 7.0);
    sudesteMap.put(false, 10.0);
    FREIGHT_VALUES.put("Sudeste", sudesteMap);

    Map<Boolean, Double> sulMap = new HashMap<>();
    sulMap.put(true, 10.0);
    sulMap.put(false, 13.0);
    FREIGHT_VALUES.put("Sul", sulMap);
  }

  double calculateFreight(User user, Address buyAddress) {
    String region = buyAddress.getRegion();
    Boolean isCapital = buyAddress.isCapital();

    double freightValue;

    if (buyAddress.state == "DF") freightValue = 5.0;
    else freightValue = FREIGHT_VALUES.get(region).get(isCapital);

    if (user.userType == "special") {
      return freightValue * 0.7;
    } else if (user.userType == "standard") {
      return freightValue;
    }

    return 0.0;
  }
}
