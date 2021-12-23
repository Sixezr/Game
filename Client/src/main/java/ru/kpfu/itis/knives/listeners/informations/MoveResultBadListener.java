package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.MOVE_RESULT_BAD;

public class MoveResultBadListener extends AbstractClientMessageListener {

    protected MoveResultBadListener() {
        super(MOVE_RESULT_BAD);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            float tangle = byteBuffer.getFloat();
            int moveID = byteBuffer.getInt();
            byteBuffer.clear();
            client.paintTangle(tangle);
            client.setMove(moveID);
            client.move();
        }
    }
}
