package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;

import java.util.HashMap;
import java.util.Map;

import static ru.kpfu.itis.knives.Constants.MAX_PLAYER_NUM;
import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class StartGameListener extends AbstractServerMessageListener {

    private Map<Connection, Integer> countPlayersMap = new HashMap<>();

    public StartGameListener() {
        this.TYPE = CLIENT_READY;
    } //31

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }
        if((message.getData() == null) || (message.getData().length == 0)){
            try{
                Message answer = messageGenerator.createEmptyMessage(SERVER_READY); //10
                server.sendMessage(connectionFrom, answer);
                if(!countPlayersMap.containsKey(connectionFrom)){
                    countPlayersMap.put(connectionFrom, 1);
                }
            } catch (MessageGenerationException | ServerException e){
                e.printStackTrace();
            }
            if(countPlayersMap.size() == MAX_PLAYER_NUM){
                server.startGame();
                countPlayersMap.clear();
            }
        }
        else{
            try{
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format"); //40
                server.sendMessage(connectionFrom, errorAnswer);
            } catch (MessageGenerationException | ServerException e) {
                e.printStackTrace();
            }
        }
    }
}
