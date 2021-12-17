package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.listeners.ServerEventListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.net.Socket;

public class KnivesServer implements KnivesServerInterface {

    public KnivesServer() {
        initServer();
    }

    @Override
    public void initServer() throws ServerException {

    }

    @Override
    public void registerListener(ServerEventListener listener) throws ServerException {

    }


    @Override
    public void startServer() throws ServerException {

    }

    @Override
    public Connection createConnection(Socket clientSocket) throws ServerException {
        return null;
    }

    @Override
    public void startGame() throws ServerException {

    }

    @Override
    public void sendMessage(Connection connection, Message message) throws ServerException {

    }

    @Override
    public void sendBroadcastMessage(Message message) throws ServerException {

    }

    @Override
    public void acceptMessage(Connection connectionFrom, Message message) {

    }

    @Override
    public void endGame() throws ServerException {

    }
}
