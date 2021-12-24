package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.GameSession;
import ru.kpfu.itis.knives.exceptions.*;
import ru.kpfu.itis.knives.listeners.MessageGeneratorImpl;
import ru.kpfu.itis.knives.listeners.MessageGenerator;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import ru.kpfu.itis.knives.server.Server;

import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.protocol.Protocol.GAME_END;
import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;
import static ru.kpfu.itis.knives.server.constants.GameSessionStates.*;

public class ServerGameSessionImpl extends GameSession implements ServerGameSession {
    private int currentState;
    private final Server server;
    private List<MessageListener> listeners;
    private List<Connection> connections;
    private ServerGameController gameController;
    private MessageGenerator messageGenerator;

    public ServerGameSessionImpl(Server server, Connection client1, Connection client2) {
        this.server = server;
        initSession();
        connections.add(client1);
        connections.add(client2);
        currentState = STARTING;
    }

    private void initSession() {
        listeners = new ArrayList<>();
        connections = new ArrayList<>();
        gameController = new ServerGameControllerImpl(this);
        messageGenerator = new MessageGeneratorImpl();
    }

    @Override
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
    public void acceptMessage(Connection connectionFrom, Message message) {
        for (MessageListener listener : listeners) {
            if (listener.getType() == message.getType()) {
                try {
                    listener.handleMessage(connectionFrom, message);
                } catch (IllegalMessageTypeException ignored) {
                } catch (MessageListenerException e) {
                    throw new GameSessionException(e);
                }
            }
        }
    }

    @Override
    public void startGame() throws GameSessionException {
        if (currentState != GAME_STARTED) {
            currentState = GAME_STARTED;
            for (Connection connectionImpl : connections) {
                gameController.addPlayer(connectionImpl.getPlayer());
            }
            gameController.setRandomCurrentPlayer();
            System.out.println(gameController.getSession().getPlayerRegion(gameController.getCurrentPlayer()));
            System.out.println(gameController.getSession().getPlayerRegion(gameController.getOpponentPlayer()));
            for (Connection connectionImpl : connections) {
                int[] ids = new int[3];
                ids[0] = connectionImpl.getPlayer().getId();
                ids[1] = gameController.getAnotherPlayer(ids[0]).getId();
                ids[2] = gameController.getCurrentPlayer().getId();
                connectionImpl.sendMessage(messageGenerator.createMessage(GAME_START, ids));
            }
        } else {
            throw new GameSessionException("Illegal state of session.");
        }
    }

    @Override
    public void interruptGame() throws GameSessionException {
        this.currentState = GAME_INTERRUPTED;
        endGame();
    }

    @Override
    public void endGame() throws GameSessionException {
        if (currentState != GAME_STARTED) {
            currentState = GAME_ENDED;
            int[] ids = new int[1];
            ids[0] = gameController.getCurrentPlayer().getId();
            sendBroadcastMessage(messageGenerator.createMessage(GAME_END, ids));
            for (Connection connection : connections) {
                connection.setReady(false);
            }
        } else {
            throw new GameSessionException("Illegal state.");
        }
        server.removeSession(this);
        gameController.invalidateGameSession();
    }

    @Override
    public void sendMessage(Connection connectionImpl, Message message) throws ServerException {
        try {
            connectionImpl.sendMessage(message);
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void sendBroadcastMessage(Message message) throws ServerException {
        try {
            for (Connection connectionImpl : connections) {
                connectionImpl.sendMessage(message);
            }
        } catch (ConnectionException e) {
            throw new ServerException(e);
        }
    }
}
