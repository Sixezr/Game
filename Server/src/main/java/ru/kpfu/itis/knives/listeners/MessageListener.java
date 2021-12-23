package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.ServerGameControllerInterface;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import ru.kpfu.itis.knives.server.ServerInterface;

public interface MessageListener {
    void init(ServerInterface knivesServer);
    void handleMessage(Connection connectionFrom, Message message);
    void setMessageGenerator(MessageGeneratorInterface messageGenerator);
    void setGameController(ServerGameControllerInterface gameController);
    void setGameSession(ServerGameSession session);
    MessageListener getNewInstance();
    int getType();
}
