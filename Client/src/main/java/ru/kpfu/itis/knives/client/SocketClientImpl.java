package ru.kpfu.itis.knives.client;

import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.entities.GameControllerInterface;
import ru.kpfu.itis.knives.entities.GameSession;
import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.generators.MessageGenerator;
import ru.kpfu.itis.knives.generators.MessageGeneratorImpl;
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
    private Player player;
    private AbstractController controller;

    private List<ClientMessageListener> listeners = new ArrayList<>();

    public SocketClientImpl(InetAddress host, int port) {
        try {
            this.connection = new ClientMessageHandlerImpl(this, new Socket(host, port));
            connection.readMessage();
            GameSession session = new GameSession();
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
        // нарисовать круг и 2 территории
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

    }

    @Override
    public void end(int winnerID) {

    }

    @Override
    public void paintAngle(float angle) {

    }

    @Override
    public void setMove(int moveID) {

    }

    @Override
    public void setNotice(String information) {

    }

    @Override
    public void choiceRegion(Point point) {

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void left(int leaverID) {

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
}
