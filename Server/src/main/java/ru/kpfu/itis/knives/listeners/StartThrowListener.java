package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;

import static ru.kpfu.itis.knives.protocol.Protocol.MOVE;

public class StartThrowListener extends AbstractServerMessageListener {

    public StartThrowListener(){
        this.TYPE = MOVE;
    }

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }

    }
}
