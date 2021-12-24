package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageListenerException;
import ru.kpfu.itis.knives.game.ServerGameController;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import ru.kpfu.itis.knives.server.Server;

public interface MessageListener {
    void init(Server knivesServer);
    void handleMessage(Connection connectionFrom, Message message) throws IllegalMessageTypeException, MessageListenerException;
    void setMessageGenerator(MessageGenerator messageGenerator);
    void setGameController(ServerGameController gameController);
    void setGameSession(ServerGameSession session);
    MessageListener getNewInstance();
    int getType();
}
