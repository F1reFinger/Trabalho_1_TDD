import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserUtils {

    public static double calculateLastMonthSpent(User user, ArrayList<Buy> buys) {
        ArrayList<Buy> userBuys = getUserBuys(user.getId(), buys);

        double lastMonthSpent = 0.0;
        LocalDateTime dateTimeNow = LocalDateTime.now();
        int previousMonthValue = getPreviousMonthValue(dateTimeNow);

        for (Buy buy : userBuys) {
            if (buy.getDate().getMonthValue() == previousMonthValue) {
                lastMonthSpent += buy.subTotal();
            }
        }

        return lastMonthSpent;
    }

    private static ArrayList<Buy> getUserBuys(String userId, ArrayList<Buy> buys) {
        ArrayList<Buy> userBuys = new ArrayList<>();

        for (Buy buy : buys) {
            if (buy.getUser().getId().equals(userId)) {
                userBuys.add(buy);
            }
        }

        return userBuys;
    }

    private static int getPreviousMonthValue(LocalDateTime dateTimeNow) {
        int currentMonth = dateTimeNow.getMonthValue();
        return currentMonth == 1 ? 12 : currentMonth - 1;
    }
}
