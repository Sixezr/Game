package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ConnectionException;

public class Connection extends Thread {
    private Runnable action;

    public Connection(Runnable target, Runnable action) {
        super(target);
        this.action = action;
    }

    @Override
    public void run() {
        if (action != null) {
            action.run();
        } else {
            throw new ConnectionException("Хз что и зачем");
        }
    }
}
