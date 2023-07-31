package pl.javastart.task.contract;

public class MixContract extends CardContract {
    private int smsPackage;
    private int mmsPackage;
    private double callMinutes;

    public MixContract(double remainingFunds, double pricePerSms, double pricePerMms, double pricePerMinuteCall,
                       int smsPackage, int mmsPackage, int callMinutes) {
        super(remainingFunds, pricePerSms, pricePerMms, pricePerMinuteCall);
        this.smsPackage = smsPackage;
        this.mmsPackage = mmsPackage;
        this.callMinutes = callMinutes;
    }

    @Override
    public String getBalance() {
        return String.format("Pozostało SMSów: %d\nPozostało MMSów: %d\nPozostało minut: %.2f\nNa koncie zostało %.2f zł\n",
                smsPackage, mmsPackage, callMinutes, remainingFunds);
    }

    @Override
    public boolean sendSms() {
        if (smsPackage > 0) {
            smsPackage--;
            return true;
        }
        return super.sendSms();
    }

    @Override
    public boolean sendMms() {
        if (mmsPackage > 0) {
            mmsPackage--;
            return true;
        }
        return super.sendMms();
    }

    @Override
    public int call(int seconds) {
        double availableSeconds = callMinutes * 60;
        if (availableSeconds >= seconds) {
            callMinutes = (availableSeconds - seconds) / 60;
            return seconds;
        } else {
            int remainingSeconds = (int) (seconds - availableSeconds);
            int secondsCall = super.call(remainingSeconds);
            callMinutes = 0;
            return (int) availableSeconds + secondsCall;
        }
    }

    @Override
    public String toString() {
        return "Mix:\n" + getBalance()
                + getPrices();
    }
}
