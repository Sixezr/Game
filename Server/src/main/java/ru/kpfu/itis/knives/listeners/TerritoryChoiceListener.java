package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;

import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class TerritoryChoiceListener extends AbstractServerMessageListener{

    public TerritoryChoiceListener(){
        this.TYPE = MOVE_POSITION;
    }

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getData());
        int clientId = byteBuffer.getInt(0);
        if(clientId == gameController.getCurrentPlayer().getId()){
            float x = byteBuffer.getFloat(4);
            float y = byteBuffer.getFloat(8);
            Point point = new Point(x, y);

            String errorText = isAnyError(point);
            if(errorText != null){
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_WRONG_POS, errorText);
                try{
                    server.sendMessage(connectionFrom, errorAnswer);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
            }


        }
        else{
            Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format");
            try{
                server.sendMessage(connectionFrom, errorAnswer);
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
    }

    private String isAnyError(Point point){
        if(!gameController.checkPointIsInCircle(point)){
            return "Chosen point is outside the circle";
        }
        if(gameController.checkPointBelongsToPlayerRegion(point, gameController.getOpponentPlayer())){
            return "Chosen point is not on the player's territory";
        }
        return null;
    }
}
