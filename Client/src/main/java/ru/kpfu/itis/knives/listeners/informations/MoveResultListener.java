package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.MOVE_RESULT;
import static ru.kpfu.itis.knives.protocol.Protocol.MOVE_RESULT_GOOD;

public class MoveResultListener extends AbstractClientMessageListener {

    public MoveResultListener() {
        super(MOVE_RESULT);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            int moveID = byteBuffer.getInt();
            Point point = new Point(byteBuffer.getFloat(), byteBuffer.getFloat());
            byteBuffer.clear();
            client.setMove(moveID);
        }
    }
}
