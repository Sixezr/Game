package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_INTERRUPT;

public class GameInterruptListener extends AbstractClientMessageListener {

    protected GameInterruptListener() {
        super(GAME_INTERRUPT);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            int leaverID = byteBuffer.getInt();
            client.left(leaverID);
        }
    }
}
