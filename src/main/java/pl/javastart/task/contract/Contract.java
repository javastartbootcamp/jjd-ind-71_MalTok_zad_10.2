package pl.javastart.task.contract;

public interface Contract {
    String getBalance();

    boolean sendSms();

    boolean sendMms();

    int call(int seconds);
}
