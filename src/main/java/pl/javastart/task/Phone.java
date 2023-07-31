package pl.javastart.task;

import pl.javastart.task.contract.Contract;

public class Phone {
    private Contract contract;
    private int smsSent = 0;
    private int mmsSent = 0;
    private int secondsUsed = 0;

    Phone(Contract contract) {
        this.contract = contract;
    }

    Contract getContract() {
        return contract;
    }

    void sendSms() {
        if (contract.sendSms()) {
            System.out.println("SMS wysłany\n");
            smsSent++;
        } else {
            System.out.println("Nie udało się wysłać SMSa\n");
        }
    }

    void sendMms() {
        if (contract.sendMms()) {
            System.out.println("MMS wysłany\n");
            mmsSent++;
        } else {
            System.out.println("Nie udało się wysłać MMSa\n");
        }
    }

    void call(int seconds) {
        int secondsCall = contract.call(seconds);
        if (secondsCall == 0) {
            System.out.println("Brak środków, połączenie niemożliwe");
        } else if (secondsCall < seconds) {
            System.out.println("Brak wystarczających środków, połączenie przerwane po " + secondsCall + " sek\n");
        } else {
            System.out.println("Rozmowa trwała " + seconds + " sek\n");
        }
        secondsUsed += secondsCall;
    }

    void printAccountState() {
        System.out.println("=== STAN KONTA ===");
        System.out.println("Wysłanych SMSów: " + smsSent);
        System.out.println("Wysłanych MMSów: " + mmsSent);
        System.out.println("Liczba sekund rozmowy: " + secondsUsed);
        System.out.println(contract.getBalance());
    }
}
