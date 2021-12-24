package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class ClientLeftListener extends AbstractMessageListener {

    public ClientLeftListener(){
        super(CLIENT_LEFT); //34
    }

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }
        if ((message.getData() == null) || (message.getData().length == 0)){
            try{
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format"); //40
                session.sendMessage(connectionFrom, errorAnswer);
            } catch (MessageGenerationException | ServerException e) {
                e.printStackTrace();
            }
        }
        else {
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getData());
            int clientId = byteBuffer.getInt(0);
            if ((clientId != gameController.getCurrentPlayer().getId()) && (clientId != gameController.getOpponentPlayer().getId())) {
                try{
                    Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format"); //40
                    session.sendMessage(connectionFrom, errorAnswer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                    int[] ints = new int[1];
                    ints[0] = clientId;
                    Message answer = messageGenerator.createMessage(GAME_INTERRUPT, ints); //16
                    session.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
                session.interruptGame();
                server.removeConnection(connectionFrom);
                session.endGame();
            }
        }
    }

    @Override
    public MessageListener getNewInstance() {
        ClientLeftListener newInstance = new ClientLeftListener();
        newInstance.init(server);
        return newInstance;
    }
}
