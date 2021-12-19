package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;

import static ru.kpfu.itis.knives.protocol.Protocol.CLIENT_READY;

public class StartGameListener extends AbstractServerMessageListener {

    public StartGameListener() {
        this.TYPE = CLIENT_READY;
    }

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }

        // от клиента пришло true - с помощью буферов прочитать это, ааааааааа
        // создать сообщение с id - connectionFrom.getPlayer().getId()
        // server.sendMessage(connectionFrom, newMessage);
    }
}
