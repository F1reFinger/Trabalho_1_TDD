// abstract public class User {

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

class User {
    String id;
    String name;
    String phone;
    ArrayList<Address> address;
    String userType;
    Card card;
    private double cashbackBalance;

    User(String name, String phone, Card card, ArrayList<Address> address, String userType) {
        this.id = LocalTime.now().toString();
        this.name = name;
        this.phone = phone;
        this.card = card;
        this.address = address;
        this.userType = userType;
        this.cashbackBalance = 0.0;
    };

    @Test
    boolean setCashbackBalance(Buy buy) {
        if (userType == "prime") {
            if (buy.card != null && buy.card.isFromStoreCard()) {
                double newCashback = buy.subTotal() * 0.05;
                this.cashbackBalance = newCashback;
            } else {
                double newCashback = buy.subTotal() * 0.03;
                this.cashbackBalance = newCashback;
            }
            return true;
        }

        return false;
    }

    @Test
    double getCashbackBalance() {
        if (userType == "prime") {
            return cashbackBalance;
        }

        return -1.0;
    }
}
