package ru.kpfu.itis.knives.client;

import ru.kpfu.itis.knives.controllers.*;
import ru.kpfu.itis.knives.entities.*;
import ru.kpfu.itis.knives.generators.MessageGenerator;
import ru.kpfu.itis.knives.generators.MessageGeneratorImpl;
import ru.kpfu.itis.knives.helpers.KnifeState;
import ru.kpfu.itis.knives.listeners.ClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClientImpl implements SocketClient {
    private ClientMessagesHandler connection;
    private GameControllerInterface regionsController;
    private MessageGenerator messageGenerator;
    private GameSession session;
    private Player player;
    private AbstractController controller;

    private List<ClientMessageListener> listeners = new ArrayList<>();

    public SocketClientImpl(InetAddress host, int port) {
        try {
            this.connection = new ClientMessageHandlerImpl(this, new Socket(host, port));
            connection.readMessage();
            session = new GameSession();
            regionsController = new RegionController(session);
            messageGenerator = new MessageGeneratorImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ready() {
        sendMessage(messageGenerator.clientReady());
    }

    @Override
    public void initSession(int thisId, int opponentId, int currentId) {
        player = new Player(thisId);
        regionsController.addPlayer(player);
        regionsController.addPlayer(new Player());
        AbstractController startingController = new StartingController(controller.getStage(), controller.getSocketClient(), thisId == currentId);
        startingController.createScene();
    }

    @Override
    public void registerListener(ClientMessageListener listener) {
        listener.init(this, messageGenerator);
        listeners.add(listener);
    }

    @Override
    public void acceptMessage(Message message) {
        for (ClientMessageListener listener : listeners) {
            if (listener.getType() == message.getType()) {
                listener.handleMessage(message);
            }
        }
    }

    @Override
    public void move(Point from, Point to) {
        Player currentPlayer = regionsController.getCurrentPlayer();
        Player opponentPlayer = regionsController.getOpponentPlayer();
        if(regionsController.checkPointBelongsToPlayerRegion(from, currentPlayer) &&
        regionsController.checkPointBelongsToPlayerRegion(to, opponentPlayer)) {
            regionsController.divideOpponentRegion(from, to);
        }
    }

    @Override
    public void end(int winnerID) {
        AbstractController gameOverController = new GameOverController(controller.getStage(), controller.getSocketClient(), getPlayer().getId() == winnerID);
        gameOverController.createScene();
    }

    @Override
    public void paintAngle(float angle) {
        GameController gameController = (GameController) controller;
        KnifeState state = angle >= 30 ? KnifeState.success : KnifeState.failure;
        gameController.getKnifeLocationCanvas().drawKnifeWithIncline(angle, state);
    }

    @Override
    public void setMove(int moveID) {
        for(Player player : session.getPlayers()) {
            if (player.getId() == moveID) {
                regionsController.setNewCurrentPlayer(player);
                break;
            }
        }
    }

    @Override
    public void setNotice(String information) {
        AlertController alert = new AlertController();
        alert.createErrorAlert("Something wrong:", information);
    }

    @Override
    public void choiceRegion(Point point) {
        regionsController.recalculateRegions(point);
    }

    @Override
    public int getID() {
        return player.getId();
    }

    @Override
    public void left(int leaverID) {
        for(Player p : session.getPlayers()) {
            if (p.getId() == leaverID) {
                messageGenerator.clientLeft(leaverID);
                break;
            }
        }
    }

    @Override
    public void sendMessage(Message message) {
        connection.sendMessage(message);
    }

    public ClientMessagesHandler getConnection() {
        return connection;
    }

    public void setConnection(ClientMessagesHandler connection) {
        this.connection = connection;
    }

    public GameControllerInterface getRegionsController() {
        return regionsController;
    }

    public void setRegionsController(GameControllerInterface regionsController) {
        this.regionsController = regionsController;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public List<ClientMessageListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ClientMessageListener> listeners) {
        this.listeners = listeners;
    }

    public GameSession getSession() {
        return session;
    }
}
