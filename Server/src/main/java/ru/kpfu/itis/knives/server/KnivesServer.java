package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.GameSession;
import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameController;
import ru.kpfu.itis.knives.listeners.MessageGenerator;
import ru.kpfu.itis.knives.listeners.MessageGeneratorInterface;
import ru.kpfu.itis.knives.listeners.ServerMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.Constants.MAX_PLAYER_NUM;
import static ru.kpfu.itis.knives.Constants.PORT;
import static ru.kpfu.itis.knives.server.constants.ServerStates.*;

public class KnivesServer implements KnivesServerInterface {
    private int currentState;
    private List<ServerMessageListener> listeners;
    private ServerSocket serverSocket;
    private List<Connection> connections;
    private GameSession gameSession;
    private ServerGameController gameController;
    private MessageGeneratorInterface messageGenerator;

    public KnivesServer() {
        initServer();
    }

    @Override
    public void initServer() throws ServerException {
        this.currentState = STARTING;
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new ServerException(e);
        }
        listeners = new ArrayList<>();
        connections = new ArrayList<>();
        gameSession = new GameSession();
        gameController = new ServerGameController(gameSession);
        messageGenerator = new MessageGenerator();
    }

    @Override
    public void registerListener(ServerMessageListener listener) throws ServerException {
        listener.init(this, gameController, messageGenerator);
        listeners.add(listener);
    }

    @Override
    public void startServer() throws ServerException {
        while (connections.size() < MAX_PLAYER_NUM) {
            try {
                Socket client = serverSocket.accept();
                if (currentState == STARTING) {
                    connections.add(createConnection(client));
                } else {
                   // sendErrorMaybeBecauseGameStartedAlready()
                }
            } catch (IOException e) {
                throw new ServerException(e);
            }
        }
        startGame();
    }

    @Override
    public Connection createConnection(Socket clientSocket) throws ServerException {
        Connection connection = new Connection(clientSocket, this);
        Player player = new Player(gameController.getRandomPlayerId());
        connection.setPlayer(player);

        return connection;
    }

    @Override
    public void startGame() throws ServerException {
        if (currentState != GAME_STARTED) {
            currentState = GAME_STARTED;
            for (Connection connection : connections) {
                ConnectionThread connectionThread = new ConnectionThread(connection);
                connectionThread.start();
                gameController.addPlayer(connection.getPlayer());
            }
        } else {
            throw new ServerException("Illegal state of server.");
        }
    }

    @Override
    public void sendMessage(Connection connection, Message message) throws ServerException {
        try {
            connection.sendMessage(message);
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void sendBroadcastMessage(Message message) throws ServerException {
        try {
            for (Connection connection : connections) {
                connection.sendMessage(message);
            }
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void acceptMessage(Connection connectionFrom, Message message) {
        for (ServerMessageListener listener : listeners) {
            if (listener.getType() == message.getType()) {
                listener.handleMessage(connectionFrom, message);
            }
        }
    }

    @Override
    public void endGame() throws ServerException {
        if (currentState == GAME_STARTED) {
            currentState = GAME_END;
            // todo
        } else {
            throw new ServerException("Illegal state.");
        }
    }
}
