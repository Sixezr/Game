package ru.kpfu.itis.knives.generators;

import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class MessageGeneratorImpl implements MessageGenerator {

    private ByteBuffer byteBuffer;

    @Override
    public Message clientReady() {
        return new Message(CLIENT_READY);
    }

    @Override
    public Message move(int id, float x1, float y1, float x2, float y2) {
        byteBuffer = ByteBuffer.allocate(4 * 5);
        byteBuffer.putInt(id).putFloat(x1).putFloat(y1).putFloat(x2).putFloat(y2);
        byte[] information = byteBuffer.array();
        byteBuffer.clear();
        return new Message(MOVE, information);
    }

    @Override
    public Message movePosition(int id, float x1, float y1) {
        byteBuffer = ByteBuffer.allocate(4 * 3);
        byteBuffer.putInt(id).putFloat(x1).putFloat(y1);
        byte[] information = byteBuffer.array();
        byteBuffer.clear();
        return new Message(MOVE_POSITION, information);
    }

    @Override
    public Message clientLeft(int id) {
        byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(id);
        byte[] information = byteBuffer.array();
        byteBuffer.clear();
        return new Message(CLIENT_LEFT, information);
    }
}
