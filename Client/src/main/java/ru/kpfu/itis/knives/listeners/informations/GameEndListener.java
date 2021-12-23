package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_END;

public class GameEndListener extends AbstractClientMessageListener {

    protected GameEndListener() {
        super(GAME_END);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer.put(message.getData());
            int winnerID = byteBuffer.getInt();
            byteBuffer.clear();
            client.end(winnerID);
        }
    }
}
