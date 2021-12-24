package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageListenerException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameControllerImpl;
import ru.kpfu.itis.knives.game.ServerGameController;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.game.ServerGameSessionImpl;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.protocol.Protocol.CLIENT_READY;

public class KnivesServer implements Server {
    private ServerSocket serverSocket;
    private final int port;

    private List<MessageListener> listeners;
    private List<ServerGameSession> sessions;
    private List<Connection> clients;
    private ServerGameController serverGameController;

    public KnivesServer(int port) {
        this.port = port;
        initServer();
        System.out.println("Server initialized.");
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
        serverGameController = new ServerGameControllerImpl();
    }

    @Override
    public void registerListener(MessageListener listener) throws ServerException {
        listener.init(this);
        listeners.add(listener);
    }

    @Override
    public void startServer() throws ServerException {
        System.out.println("Server is listening to clients...");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                clients.add(createConnection(client));
                System.out.println("New connection with client created.");
            } catch (IOException e) {
                throw new ServerException(e);
            }
        }
    }

    @Override
    public Connection createConnection(Socket clientSocket) {
        Connection connectionImpl = new ConnectionImpl(this, clientSocket);
        Player player = new Player(serverGameController.getRandomPlayerId());
        connectionImpl.setPlayer(player);

        ConnectionSession connectionSession = new ConnectionSession(connectionImpl);
        connectionSession.start();

        return connectionImpl;
    }

    @Override
    public ServerGameSession initSession(List<Connection> connectionImplList) {
        ServerGameSession gameSession = new ServerGameSessionImpl(this, connectionImplList.get(0), connectionImplList.get(1));
        for (Connection connection : connectionImplList){
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
        if (message.getType() == CLIENT_READY) {
            for (MessageListener listener : listeners) {
                if (listener.getType() == message.getType()) {
                    try {
                        listener.handleMessage(connectionFrom, message);
                    } catch (IllegalMessageTypeException ignored) {
                    } catch (MessageListenerException e) {
                        throw new ServerException(e);
                    }
                }
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
    public void removeConnection(Connection connection) {
        clients.remove(connection);
    }

    @Override
    public List<Connection> getClients() {
        return clients;
    }
}
