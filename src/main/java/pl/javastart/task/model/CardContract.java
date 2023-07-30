package pl.javastart.task.model;

import pl.javastart.task.interfaces.Contract;

public class CardContract implements Contract {
    private double remainingFunds;
    private double pricePerSms;
    private double pricePerMms;
    private double pricePerMinuteCall;

    public CardContract(double remainingFunds, double pricePerSms, double pricePerMms, double pricePerMinuteCall) {
        this.remainingFunds = remainingFunds;
        this.pricePerSms = pricePerSms;
        this.pricePerMms = pricePerMms;
        this.pricePerMinuteCall = pricePerMinuteCall;
    }

    public double getRemainingFunds() {
        return remainingFunds;
    }

    public void setRemainingFunds(double remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public double getPricePerSms() {
        return pricePerSms;
    }

    public void setPricePerSms(double pricePerSms) {
        this.pricePerSms = pricePerSms;
    }

    public double getPricePerMms() {
        return pricePerMms;
    }

    public void setPricePerMms(double pricePerMms) {
        this.pricePerMms = pricePerMms;
    }

    public double getPricePerMinuteCall() {
        return pricePerMinuteCall;
    }

    public void setPricePerMinuteCall(double pricePerMinuteCall) {
        this.pricePerMinuteCall = pricePerMinuteCall;
    }

    @Override
    public String balance() {
        return String.format("Na koncie zostało %.2f zł\n", remainingFunds);
    }

    @Override
    public boolean canSendSms() {
        if (remainingFunds >= pricePerSms) {
            remainingFunds -= pricePerSms;
            return true;
        }
        return false;
    }

    @Override
    public boolean canSendMms() {
        if (remainingFunds >= pricePerMms) {
            remainingFunds -= pricePerMms;
            return true;
        }
        return false;
    }

    @Override
    public int canCall(int seconds) {
        double requiredFunds = pricePerMinuteCall * seconds / 60;
        if (remainingFunds >= requiredFunds) {
            remainingFunds -= requiredFunds;
            return seconds;
        } else {
            double secondsCall = remainingFunds * 60 / pricePerMinuteCall;
            remainingFunds = 0;
            return (int) secondsCall;
        }
    }

    public String getPrices() {
        return "Cena za SMS: " + pricePerSms + " zł/sms\n"
                + "Cena za MMS: " + pricePerMms + " zł/mms\n"
                + "Cena za połączenie: " + pricePerMinuteCall + " zł/min\n";
    }

    @Override
    public String toString() {
        return "Na kartę:\n" + balance() + getPrices();
    }
}
