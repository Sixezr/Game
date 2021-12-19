package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ConnectionException;

public class ConnectionThread extends Thread {
    private final Runnable action;

    public ConnectionThread(Runnable target) {
        super(target);
        this.action = target;
    }

    @Override
    public void run() {
        if (action != null) {
            action.run();
        } else {
            throw new ConnectionException("Trouble trouble trouble");
        }
    }
}
