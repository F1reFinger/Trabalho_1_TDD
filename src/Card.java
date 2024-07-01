import org.junit.Test;

class Card {
    String number;

    Card(String number) {
        this.number = number;
    }

    @Test
    boolean isFromStoreCard() {
        // return number.startsWith("4296 13");
        return true;
    }
}
