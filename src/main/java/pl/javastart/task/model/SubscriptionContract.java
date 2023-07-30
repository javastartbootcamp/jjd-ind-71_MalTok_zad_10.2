package pl.javastart.task.model;

import pl.javastart.task.interfaces.Contract;

public class SubscriptionContract implements Contract {
    private double monthlyCost;

    @Override
    public String balance() {
        return String.format("Rachunek %.2f zł\n", monthlyCost);
    }

    @Override
    public boolean canSendSms() {
        return true;
    }

    @Override
    public boolean canSendMms() {
        return true;
    }

    @Override
    public int canCall(int seconds) {
        return seconds;
    }

    @Override
    public String toString() {
        return "Abonament:\n" + balance();
    }
}
