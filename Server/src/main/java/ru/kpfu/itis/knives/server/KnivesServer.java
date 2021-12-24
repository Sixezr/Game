package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameController;
import ru.kpfu.itis.knives.game.ServerGameControllerInterface;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class KnivesServer implements ServerInterface {
    private ServerSocket serverSocket;
    private final int port;

    private List<MessageListener> listeners;
    private List<ServerGameSession> sessions;
    private List<Connection> clients;

    private ServerGameControllerInterface serverGameController;

    public KnivesServer(int port) {
        this.port = port;
        initServer();
    }

    @Override
    public void initServer() throws ServerException {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new ServerException(e);
        }
        listeners = new ArrayList<>();
        sessions = new ArrayList<>();
        clients = new ArrayList<>();
        serverGameController = new ServerGameController();
    }

    @Override
    public void registerListener(MessageListener listener) throws ServerException {
        listener.init(this);
        listeners.add(listener);
    }

    @Override
    public void startServer() throws ServerException {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                clients.add(createConnection(client));
            } catch (IOException e) {
                throw new ServerException(e);
            }
        }
    }

    @Override
    public ServerGameSession initSession(List<Connection> connectionList) {
        ServerGameSession gameSession = new ServerGameSession(this, connectionList.get(0), connectionList.get(1));
        for(Connection connection: connectionList){
            connection.setSession(gameSession);
        }
        gameSession.initListeners(listeners);
        sessions.add(gameSession);

        return gameSession;
    }

    @Override
    public void removeSession(ServerGameSession gameSession) {
        sessions.remove(gameSession);
    }

    @Override
    public void sendMessage(Connection connection, Message message) {
        try {
            connection.sendMessage(message);
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void acceptMessage(Connection connectionFrom, Message message) {
        for (MessageListener listener : listeners) {
            if (listener.getType() == message.getType()) {
                listener.handleMessage(connectionFrom, message);
            }
        }
    }

    @Override
    public void sendBroadcastMessage(Message message) {
        try {
            for (Connection connection : clients) {
                connection.sendMessage(message);
            }
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Connection createConnection(Socket clientSocket) {
        Connection connection = new Connection(this, clientSocket);
        Player player = new Player(serverGameController.getRandomPlayerId());
        connection.setPlayer(player);

        ConnectionSession connectionSession = new ConnectionSession(connection);
        connectionSession.start();

        return connection;
    }

    @Override
    public void removeConnection(Connection connection) {
        clients.remove(connection);
    }

    public List<Connection> getClients() {
        return clients;
    }
}
