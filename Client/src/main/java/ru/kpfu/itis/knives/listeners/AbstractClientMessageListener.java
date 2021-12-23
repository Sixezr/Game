package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.generators.MessageGenerator;

import java.nio.ByteBuffer;

public abstract class AbstractClientMessageListener implements ClientMessageListener{
    protected final int TYPE;
    protected SocketClient client;
    protected MessageGenerator messageGenerator;
    protected ByteBuffer byteBuffer;

    protected AbstractClientMessageListener(int type) {
        TYPE = type;
    }

    @Override
    public void init(SocketClient client, MessageGenerator messageGenerator) {
        this.client = client;
        this.messageGenerator = messageGenerator;
    }

    @Override
    public int getType() {
        return this.TYPE;
    }
}
