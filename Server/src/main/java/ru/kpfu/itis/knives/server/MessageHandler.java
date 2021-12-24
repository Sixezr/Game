package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.protocol.Message;

public interface MessageHandler {
    void acceptMessage(Connection connection, Message message);

    void sendMessage(Connection connection, Message message);

    void sendBroadcastMessage(Message message);
}
