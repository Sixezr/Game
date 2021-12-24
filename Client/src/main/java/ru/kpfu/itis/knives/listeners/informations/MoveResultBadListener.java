package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.MOVE_RESULT_BAD;

public class MoveResultBadListener extends AbstractClientMessageListener {

    public MoveResultBadListener() {
        super(MOVE_RESULT_BAD);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            float angle = byteBuffer.getFloat();
            int moveID = byteBuffer.getInt();
            byteBuffer.clear();
            client.paintAngle(angle);
            client.setMove(moveID);
        }
    }
}
