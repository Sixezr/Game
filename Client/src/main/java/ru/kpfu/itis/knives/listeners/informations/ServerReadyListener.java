package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;
import static ru.kpfu.itis.knives.protocol.Protocol.SERVER_READY;

public class ServerReadyListener extends AbstractClientMessageListener {

    public ServerReadyListener() {
        super(SERVER_READY);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            client.ready();
        }
    }
}
