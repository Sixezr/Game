package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.MOVE_RESULT_GOOD;

public class MoveResultGoodListener extends AbstractClientMessageListener {

    protected MoveResultGoodListener() {
        super(MOVE_RESULT_GOOD);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer.put(message.getData());
            float tangle = byteBuffer.getFloat();
            int moveID = byteBuffer.getInt();
            Point first = new Point(byteBuffer.getFloat(), byteBuffer.getFloat());
            Point second = new Point(byteBuffer.getFloat(), byteBuffer.getFloat());
            byteBuffer.clear();
            // TODO отрисовка
            client.paintTangle(tangle);
            if (client.getID() == moveID) {
                client.choiceRegion();
            }
        }
    }
}
