package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;

public class GameStartListener extends AbstractClientMessageListener {

    protected GameStartListener() {
        super(GAME_START);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer.put(message.getData());
            int clientID = byteBuffer.getInt();
            int moveID = byteBuffer.getInt();
            this.byteBuffer.clear();
            client.initSession(clientID, moveID);
            client.move();
        }
    }
}
