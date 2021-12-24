package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.exceptions.MessageListenerException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static ru.kpfu.itis.knives.Constants.MAX_PLAYER_NUM;
import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class StartGameListener extends AbstractMessageListener {

    private Set<Connection> connections;

    public StartGameListener() {
        super(CLIENT_READY);
        connections = new HashSet<>();
        messageGenerator = new MessageGeneratorImpl();
    } //31

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }

        if ((message.getData() == null) || (message.getData().length == 0)) {
            try{
                //System.out.println("Player " + connectionFrom.getPlayer().getId() + " is ready to start game;");
                Message answer = messageGenerator.createEmptyMessage(SERVER_READY); //10
                server.sendMessage(connectionFrom, answer);
                connectionFrom.setReady(true);
                connections.add(connectionFrom);
            } catch (MessageGenerationException | ServerException e){
                throw new MessageListenerException(e);
            }
            if (connections.size() == MAX_PLAYER_NUM) {
                this.session = server.initSession(new ArrayList<>(connections));
                session.startGame();
                System.out.println("Сессия игры инициализирована");
                connections.clear();
            }
        }
        else {
            try {
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format"); //40
                server.sendMessage(connectionFrom, errorAnswer);
            } catch (MessageGenerationException | ServerException e) {
                throw new MessageListenerException(e);
            }
        }
    }

    @Override
    public MessageListener getNewInstance() {
        StartGameListener newInstance = new StartGameListener();
        newInstance.init(server);
        return newInstance;
    }
}
