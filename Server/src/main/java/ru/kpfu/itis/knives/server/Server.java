package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.listeners.MessageListener;

import java.net.Socket;
import java.util.List;

public interface Server extends MessageHandler {
    void initServer() throws ServerException;

    void registerListener(MessageListener listener) throws ServerException;

    void startServer() throws ServerException;

    ServerGameSession initSession(List<Connection> connectionList);

    void removeSession(ServerGameSession serverGameSession);

    Connection createConnection(Socket socket);

    void removeConnection(Connection connection);

    List<Connection> getClients();
}
