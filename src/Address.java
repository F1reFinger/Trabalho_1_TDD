import java.util.HashMap;
import java.util.Map;

class Address {
    String state;
    String city;

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    String getRegion() {
        return switch (state) {
            case "GO", "MT", "MS", "DF" -> "Centro-Oeste";
            case "AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE" -> "Nordeste";
            case "AC", "AP", "AM", "PA", "RO", "RR", "TO" -> "Norte";
            case "ES", "MG", "RJ", "SP" -> "Sudeste";
            case "PR", "RS", "SC" -> "Sul";
            default -> "Estado não identificado";
        };
    }

    public boolean isCapital() {
        Map<String, String> capitals = new HashMap<>();
        capitals.put("GO", "Goiânia");
        capitals.put("MT", "Cuiabá");
        capitals.put("MS", "Campo Grande");
        capitals.put("DF", "Brasília");
        capitals.put("AL", "Maceió");
        capitals.put("BA", "Salvador");
        capitals.put("CE", "Fortaleza");
        capitals.put("MA", "São Luís");
        capitals.put("PB", "João Pessoa");
        capitals.put("PE", "Recife");
        capitals.put("PI", "Teresina");
        capitals.put("RN", "Natal");
        capitals.put("SE", "Aracaju");
        capitals.put("AC", "Rio Branco");
        capitals.put("AP", "Macapá");
        capitals.put("AM", "Manaus");
        capitals.put("PA", "Belém");
        capitals.put("RO", "Porto Velho");
        capitals.put("RR", "Boa Vista");
        capitals.put("TO", "Palmas");
        capitals.put("ES", "Vitória");
        capitals.put("MG", "Belo Horizonte");
        capitals.put("RJ", "Rio de Janeiro");
        capitals.put("SP", "São Paulo");
        capitals.put("PR", "Curitiba");
        capitals.put("RS", "Porto Alegre");
        capitals.put("SC", "Florianópolis");

        String capital = capitals.get(state);
        return capital != null && city.equals(capital);
    }
}
