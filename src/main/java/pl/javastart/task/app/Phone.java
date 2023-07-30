package pl.javastart.task.app;

import pl.javastart.task.interfaces.Contract;

public class Phone {
    private Contract contract;
    private int smsSent = 0;
    private int mmsSent = 0;
    private int secondsUsed = 0;

    public Phone(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    void setContract(Contract contract) {
        this.contract = contract;
    }

    public void sendSms() {
        if (contract.canSendSms()) {
            System.out.println("SMS wysłany\n");
            smsSent++;
        } else {
            System.out.println("Nie udało się wysłać SMSa\n");
        }
    }

    public void sendMms() {
        if (contract.canSendMms()) {
            System.out.println("MMS wysłany\n");
            mmsSent++;
        } else {
            System.out.println("Nie udało się wysłać MMSa\n");
        }
    }

    public void call(int seconds) {
        int secondsCall = contract.canCall(seconds);
        if (secondsCall < seconds) {
            System.out.println("Brak wystarczających środków, połączenie przerwane po " + secondsCall + " sek\n");
        } else {
            System.out.println("Rozmowa trwała " + seconds + " sek\n");
        }
        secondsUsed += secondsCall;
    }

    public void printAccountState() {
        System.out.println("=== STAN KONTA ===");
        System.out.println("Wysłanych SMSów: " + smsSent);
        System.out.println("Wysłanych MMSów: " + mmsSent);
        System.out.println("Liczba sekund rozmowy: " + secondsUsed);
        System.out.println(contract.balance());
    }
}
