package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.GameSession;
import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.listeners.MessageGenerator;
import ru.kpfu.itis.knives.listeners.MessageGeneratorInterface;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import ru.kpfu.itis.knives.server.ConnectionSession;
import ru.kpfu.itis.knives.server.ServerInterface;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_END;
import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;
import static ru.kpfu.itis.knives.server.constants.SessionStates.*;

public class ServerGameSession extends GameSession implements Runnable {
    private int currentState;
    private ServerInterface server;
    private List<MessageListener> listeners;
    private List<Connection> connections;
    private ServerGameControllerInterface gameController;
    private MessageGeneratorInterface messageGenerator;

    public ServerGameSession(ServerInterface server, Socket client1, Socket client2) {
        this.server = server;
        initSession();
        connections.add(createConnection(client1));
        connections.add(createConnection(client2));
    }

    private void initSession() {
        listeners = new ArrayList<>();
        connections = new ArrayList<>();
        gameController = new ServerGameController(this);
        messageGenerator = new MessageGenerator();
    }

    public void initListeners(List<MessageListener> serverListeners) {
        for (MessageListener l : serverListeners) {
            this.listeners.add(l.getNewInstance());
        }
        for (MessageListener listener : listeners) {
            listener.setGameSession(this);
            listener.setGameController(gameController);
            listener.setMessageGenerator(messageGenerator);
        }
    }

    @Override
    public void run() {
        for (Connection connection : connections) {
            ConnectionSession connectionSession = new ConnectionSession(connection);
            connectionSession.start();
        }
    }

    public void acceptMessage(Connection connectionFrom, Message message) {
        for (MessageListener listener : listeners) {
            if (listener.getType() == message.getType()) {
                listener.handleMessage(connectionFrom, message);
            }
        }
    }

    public void startGame() throws ServerException {
        if (currentState != GAME_STARTED) {
            currentState = GAME_STARTED;
            gameController.createNewGameSession();
            for (Connection connection : connections) {
                gameController.addPlayer(connection.getPlayer());
            }
            gameController.setRandomCurrentPlayer();
            for (Connection connection : connections) {
                int[] ids = new int[2];
                ids[0] = connection.getPlayer().getId();
                ids[1] = gameController.getCurrentPlayer().getId();
                connection.sendMessage(messageGenerator.createMessage(GAME_START, ids));
            }
        } else {
            throw new ServerException("Illegal state of server.");
        }
    }

    public void interruptGame() throws ServerException {
        this.currentState = GAME_INTERRUPTED;
    }

    public void endGame() throws ServerException {
        if (currentState == GAME_STARTED) {
            currentState = GAME_ENDED;
            int[] ids = new int[1];
            ids[0] = gameController.getCurrentPlayer().getId();
            sendBroadcastMessage(messageGenerator.createMessage(GAME_END, ids));
            for (Connection connection: connections) {
                removeConnection(connection);
            }
            gameController.invalidateGameSession();
        } else if (currentState == GAME_INTERRUPTED) {
            gameController.invalidateGameSession();
        } else {
            throw new ServerException("Illegal state.");
        }
        server.removeSession(this);
    }

    public Connection createConnection(Socket clientSocket) throws ServerException {
        Connection connection = new Connection(clientSocket, this);
        Player player = new Player(gameController.getRandomPlayerId());
        connection.setPlayer(player);

        return connection;
    }

    public void sendMessage(Connection connection, Message message) throws ServerException {
        try {
            connection.sendMessage(message);
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    public void sendBroadcastMessage(Message message) throws ServerException {
        try {
            for (Connection connection : connections) {
                connection.sendMessage(message);
            }
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    public void removeConnection(Connection connection) throws ServerException {
        int indexOfConn = connections.indexOf(connection);
        if (indexOfConn != -1) {
            connections.remove(indexOfConn);
            gameController.invalidateGameSession();
        }
    }
}
