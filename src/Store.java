import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;

class Store {
    ArrayList<Buy> buys;

    Store() {
        this.buys = new ArrayList<>();
    }

    void addNewBuy(Buy newBuy) {
        this.buys.add(newBuy);
    }

    ArrayList<Buy> getBuys() {
        return buys;
    }

    @Test
    ArrayList<Buy> getUserBuys(String userId) {
        ArrayList<Buy> userBuys = new ArrayList<>();

        // for (Buy buy : buys) {
        // if (buy.user.id.equals(userId)) {
        // userBuys.add(buy);
        // }
        // }

        return userBuys;
    }

    @Test
    boolean userIsSpecial(String userId) {
        // ArrayList<Buy> userBuys = getUserBuys(userId);

        // double lastMonthSpent = 0.0;
        // LocalDateTime dateTimeNow = LocalDateTime.now();

        // for (Buy buy : userBuys) {
        // if (dateTimeNow.getMonthValue() - 1 == buy.date.getMinute()) {
        // lastMonthSpent += buy.subTotal();
        // }
        // }

        // return lastMonthSpent > 100;
        return true;
    }
}
