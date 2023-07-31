package pl.javastart.task.contract;

public class CardContract implements Contract {
    protected double remainingFunds;
    protected double pricePerSms;
    protected double pricePerMms;
    protected double pricePerMinuteCall;

    public CardContract(double remainingFunds, double pricePerSms, double pricePerMms, double pricePerMinuteCall) {
        this.remainingFunds = remainingFunds;
        this.pricePerSms = pricePerSms;
        this.pricePerMms = pricePerMms;
        this.pricePerMinuteCall = pricePerMinuteCall;
    }

    double getRemainingFunds() {
        return remainingFunds;
    }

    void setRemainingFunds(double remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    double getPricePerSms() {
        return pricePerSms;
    }

    void setPricePerSms(double pricePerSms) {
        this.pricePerSms = pricePerSms;
    }

    double getPricePerMms() {
        return pricePerMms;
    }

    void setPricePerMms(double pricePerMms) {
        this.pricePerMms = pricePerMms;
    }

    double getPricePerMinuteCall() {
        return pricePerMinuteCall;
    }

    void setPricePerMinuteCall(double pricePerMinuteCall) {
        this.pricePerMinuteCall = pricePerMinuteCall;
    }

    @Override
    public String getBalance() {
        return String.format("Na koncie zostało %.2f zł\n", remainingFunds);
    }

    @Override
    public boolean sendSms() {
        if (remainingFunds >= pricePerSms) {
            remainingFunds -= pricePerSms;
            return true;
        }
        return false;
    }

    @Override
    public boolean sendMms() {
        if (remainingFunds >= pricePerMms) {
            remainingFunds -= pricePerMms;
            return true;
        }
        return false;
    }

    @Override
    public int call(int seconds) {
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

    String getPrices() {
        return "Cena za SMS: " + pricePerSms + " zł/sms\n"
                + "Cena za MMS: " + pricePerMms + " zł/mms\n"
                + "Cena za połączenie: " + pricePerMinuteCall + " zł/min\n";
    }

    @Override
    public String toString() {
        return "Na kartę:\n" + getBalance() + getPrices();
    }
}
