package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.listeners.MessageListener;

public interface ServerInterface {
    void initServer() throws ServerException;
    void registerListener(MessageListener listener) throws ServerException;
    void startServer() throws ServerException;
    void removeSession(ServerGameSession serverGameSession);
}
