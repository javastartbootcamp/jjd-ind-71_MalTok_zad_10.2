package pl.javastart.task.contract;

public class SubscriptionContract implements Contract {
    private double monthlyCost;

    public SubscriptionContract(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public String getBalance() {
        return String.format("Rachunek %.2f zł\n", monthlyCost);
    }

    @Override
    public boolean sendSms() {
        return true;
    }

    @Override
    public boolean sendMms() {
        return true;
    }

    @Override
    public int call(int seconds) {
        return seconds;
    }

    @Override
    public String toString() {
        return "Abonament:\n" + getBalance();
    }
}
