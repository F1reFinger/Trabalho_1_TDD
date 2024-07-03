// abstract public class User {

import java.time.LocalTime;

import org.junit.Test;

class User {
    String id;
    String name;
    String phone;
    Address address;
    String userType;
    Card card;
    private double cashbackBalance;

    User(String name, String phone, Card card, Address address, String userType) {
        this.id = LocalTime.now().toString();
        this.name = name;
        this.phone = phone;
        this.card = card;
        this.address = address;
        this.userType = userType;
        this.cashbackBalance = 0.0;
    };

    
    boolean setCashbackBalance(double newCashback, Buy buy) {
        if (userType == "prime") {
        // Cashback DE R$ 0.03 A CADA REAL gasto na loja (total * 0.03)
        // Comprando no cartão da loja cashback de R$ 0.05 (total * 0.05)
        this.cashbackBalance = newCashback;
        return true;
        }

        return false;
    }

    @Test
    double getCashbackBalance() {
        if (userType == "prime") {
        return cashbackBalance;
        }

        return -1;
    }
}
