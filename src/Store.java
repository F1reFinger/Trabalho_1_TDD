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

        for (Buy buy : buys) {
            if (buy.user.id.equals(userId)) {
                userBuys.add(buy);
            }
        }

        return userBuys;
    }

    @Test
    boolean userIsSpecial(User user) {
        if (user.userType == "prime")
            return false;

        ArrayList<Buy> userBuys = getUserBuys(user.id);

        double lastMonthSpent = 0.0;
        LocalDateTime dateTimeNow = LocalDateTime.now();

        for (Buy buy : userBuys) {
            int timeNowMonthValue = dateTimeNow.getMonthValue();
            if ((timeNowMonthValue == 1 ? 12 : timeNowMonthValue - 1) == buy.date.getMonthValue()) {
                lastMonthSpent += buy.subTotal();
            }
        }

        return lastMonthSpent > 100;

    }
}
