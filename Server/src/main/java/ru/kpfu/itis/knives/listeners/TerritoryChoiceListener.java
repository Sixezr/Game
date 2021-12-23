package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class TerritoryChoiceListener extends AbstractServerMessageListener{

    public TerritoryChoiceListener(){
        this.TYPE = MOVE_POSITION;
    } //33

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
                try{
                    Message errorAnswer = messageGenerator.createErrorMessage(ERROR_WRONG_POS, errorText); //42
                    server.sendMessage(connectionFrom, errorAnswer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }

            int[] ints = new int[1];
            if(gameController.checkPlayerRegionIsIsland(gameController.getCurrentPlayer()) ||
                    !gameController.isPlayerHasEnoughTerritory(gameController.getCurrentPlayer())){
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    Message answer = messageGenerator.createMessage(GAME_END, ints); //12
                    server.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    float[] floats = new float[2];
                    floats[0] = x;
                    floats[1] = y;
                    Message answer = messageGenerator.createMessage(MOVE_RESULT, floats, ints); //15
                    server.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
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
