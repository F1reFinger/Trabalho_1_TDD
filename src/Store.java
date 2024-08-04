import java.time.LocalDateTime;
import java.util.ArrayList;

class Store {
    private ArrayList<Buy> buys;

    public Store() {
        this.buys = new ArrayList<>();
    }

    public void addNewBuy(Buy newBuy) {
        this.buys.add(newBuy);
    }

    public ArrayList<Buy> getBuys() {
        return new ArrayList<>(buys); 
    }

    public ArrayList<Buy> getUserBuys(String userId) {
        return UserUtils.getUserBuys(userId, buys);
    }

    public boolean userIsSpecial(User user) {
        if (isPrimeUser(user)) {
            return true;
        }

        double lastMonthSpent = UserUtils.calculateLastMonthSpent(user, buys);

        return lastMonthSpent > 100;
    }

    private boolean isPrimeUser(User user) {
        return "prime".equals(user.getUserType());
    }
}
