package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_END;

public class GameEndListener extends AbstractClientMessageListener {

    public GameEndListener() {
        super(GAME_END);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            int winnerID = byteBuffer.getInt(0);
            byteBuffer.clear();
            client.end(winnerID);
        }
    }
}
