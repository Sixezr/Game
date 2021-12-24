package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.net.Socket;
import java.util.List;

public interface ServerInterface {
    void initServer() throws ServerException;
    void registerListener(MessageListener listener) throws ServerException;
    void startServer() throws ServerException;
    ServerGameSession initSession(List<Connection> connectionList);
    void removeSession(ServerGameSession serverGameSession);
    void sendMessage(Connection connection, Message message);
    void acceptMessage(Connection connection, Message message);
    void sendBroadcastMessage(Message message);
    Connection createConnection(Socket socket);
    void removeConnection(Connection connection);
}