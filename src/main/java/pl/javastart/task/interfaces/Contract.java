package pl.javastart.task.interfaces;

public interface Contract {
    String balance();

    boolean canSendSms();

    boolean canSendMms();

    int canCall(int seconds);
}
