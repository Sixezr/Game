package ru.kpfu.itis.knives.client;

import ru.kpfu.itis.knives.protocol.Message;

public interface ClientMessagesHandler {
    void sendMessage(Message message);
    void readMessage();
}
