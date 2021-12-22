package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.ServerGameControllerInterface;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import ru.kpfu.itis.knives.server.KnivesServerInterface;

public interface ServerMessageListener {
    void init(KnivesServerInterface knivesServer, ServerGameControllerInterface gameController, MessageGeneratorInterface messageGenerator);
    void handleMessage(Connection connectionFrom, Message message);
    int getType();
}
