package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.generators.MessageGenerator;
import ru.kpfu.itis.knives.protocol.Message;

public interface ClientMessageListener {

    void init(SocketClient client, MessageGenerator messageGenerator);

    void handleMessage(Message message);

    int getType();
}
