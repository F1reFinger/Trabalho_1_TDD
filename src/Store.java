import java.time.LocalDateTime;
import java.util.ArrayList;

class Store {
    ArrayList<Buy> buys;
    LocalDateTime datetime;

    Store() {
        this.buys = new ArrayList<>();
        this.datetime = LocalDateTime.now();
    }

    void addNewBuy(Buy newBuy) {
        this.buys.add(newBuy);
    }

    ArrayList<Buy> getBuys() {
        return buys;
    }

    ArrayList<Buy> getUserBuys(String userId) {
        ArrayList<Buy> userBuys = new ArrayList<>();

        for (Buy buy : buys) {
            if (buy.user.id.equals(userId)) {
                userBuys.add(buy);
            }
        }

        return userBuys;
    }

    boolean userIsSpecial(User user) {
        if (user.userType == "prime")
            return false;

        ArrayList<Buy> userBuys = getUserBuys(user.id);

        double lastMonthSpent = 0.0;

        for (Buy buy : userBuys) {
            int timeNowMonthValue = this.datetime.getMonthValue();
            if ((timeNowMonthValue == 1 ? 12 : timeNowMonthValue - 1) == buy.date.getMonthValue()) {
                lastMonthSpent += buy.subTotal();
            }
        }

        return lastMonthSpent > 100;

    }
}