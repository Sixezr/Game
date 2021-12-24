package ru.kpfu.itis.knives.listeners.informations;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;

public class GameStartListener extends AbstractClientMessageListener {

    public GameStartListener() {
        super(GAME_START);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            this.byteBuffer = ByteBuffer.allocate(message.getData().length);
            this.byteBuffer.put(message.getData());
            System.out.println(message);
            int clientID = byteBuffer.getInt(0);
            int opponentID = byteBuffer.getInt(4);
            int moveID = byteBuffer.getInt(8);
            this.byteBuffer.clear();
            client.initSession(clientID, opponentID, moveID);
        }
    }
}
