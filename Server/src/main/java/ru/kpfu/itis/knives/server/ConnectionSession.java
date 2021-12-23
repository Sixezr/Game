package ru.kpfu.itis.knives.server;

public class ConnectionSession extends Thread {
    public ConnectionSession(Runnable target) {
        super(target);
    }
}
