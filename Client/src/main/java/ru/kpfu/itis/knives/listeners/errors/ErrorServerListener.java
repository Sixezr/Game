package ru.kpfu.itis.knives.listeners.errors;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.ERROR_SERVER;

public class ErrorServerListener extends AbstractClientMessageListener {

    public ErrorServerListener() {
        super(ERROR_SERVER);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            String information = new String(message.getData());
            client.setNotice(information);
        }
    }
}
