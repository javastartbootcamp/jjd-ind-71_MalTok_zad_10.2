package pl.javastart.task.model;

import pl.javastart.task.interfaces.Contract;

public class MixContract extends CardContract implements Contract {
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

    public int getSmsPackage() {
        return smsPackage;
    }

    public void setSmsPackage(int smsPackage) {
        this.smsPackage = smsPackage;
    }

    public int getMmsPackage() {
        return mmsPackage;
    }

    public void setMmsPackage(int mmsPackage) {
        this.mmsPackage = mmsPackage;
    }

    public double getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(int callMinutes) {
        this.callMinutes = callMinutes;
    }

    @Override
    public String balance() {
        return String.format("Pozostało SMSów: %d\nPozostało MMSów: %d\nPozostało minut: %.2f\nNa koncie zostało %.2f zł\n",
                smsPackage, mmsPackage, callMinutes, getRemainingFunds());
    }

    @Override
    public boolean canSendSms() {
        if (smsPackage > 0) {
            smsPackage--;
            return true;
        } else if (getRemainingFunds() >= getPricePerSms()) {
            setRemainingFunds(getRemainingFunds() - getPricePerSms());
            return true;
        }
        return false;
    }

    @Override
    public boolean canSendMms() {
        if (mmsPackage > 0) {
            mmsPackage--;
            return true;
        } else if (getRemainingFunds() >= getPricePerMms()) {
            setRemainingFunds(getRemainingFunds() - getPricePerMms());
            return true;
        }
        return false;
    }

    @Override
    public int canCall(int seconds) {
        double availableSeconds = callMinutes * 60;
        if (availableSeconds >= seconds) {
            callMinutes = (availableSeconds - seconds) / 60;
            return seconds;
        } else {
            int remainingSeconds = (int) (seconds - availableSeconds);
            double requiredFunds = getPricePerMinuteCall() * remainingSeconds / 60;
            callMinutes = 0;
            if (getRemainingFunds() >= requiredFunds) {
                setRemainingFunds(getRemainingFunds() - requiredFunds);
                return seconds;
            } else {
                double secondsCall = getRemainingFunds() * 60 / getPricePerMinuteCall();
                setRemainingFunds(0);
                return (int) (availableSeconds + secondsCall);
            }
        }
    }

    @Override
    public String toString() {
        return "Mix:\n" + balance()
                + getPrices();
    }
}
