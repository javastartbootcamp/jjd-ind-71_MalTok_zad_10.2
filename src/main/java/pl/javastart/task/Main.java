package pl.javastart.task;

import pl.javastart.task.contract.MixContract;

public class Main {

    public static void main(String[] args) {
//        Phone phone = new Phone(new CardContract(1, 0.1, 0.2, 0.5));
//
//        phone.printAccountState();
//
//        phone.sendSms();
//        phone.printAccountState();
//
//        phone.sendSms();
//        phone.printAccountState();
//
//        phone.sendSms();
//        phone.printAccountState();

        Phone phone1 = new Phone(new MixContract(5, 0.1, 0.2, 0.5,
                0, 5, 20));

        phone1.printAccountState();

        phone1.sendSms();
        phone1.printAccountState();

        phone1.sendMms();
        phone1.printAccountState();

        phone1.call(181);
        phone1.printAccountState();

        phone1.call(10);
        phone1.printAccountState();

        System.out.println(phone1.getContract());
    }
}
