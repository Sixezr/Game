package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.server.Connection;
import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.Constants.MIN_ANGLE;
import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class StartMoveListener extends AbstractServerMessageListener {

    public StartMoveListener(){
        this.TYPE = MOVE;
    }

    @Override
    public void handleMessage(Connection connectionFrom, Message message) {
        if (message.getType() != this.getType()) {
            throw new IllegalMessageTypeException("Message type do not match to listener's one");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getData());
        int clientId = byteBuffer.getInt(0);
        if(clientId == gameController.getCurrentPlayer().getId()){
            float x1 = byteBuffer.getFloat(4);
            float y1 = byteBuffer.getFloat(8);
            float x2 = byteBuffer.getFloat(12);
            float y2 = byteBuffer.getFloat(16);
            Point point1 = new Point(x1, y1);
            Point point2 = new Point(x2, y2);

            String errorText = isAnyError(point1, point2);
            if(errorText != null){
                try{
                    Message errorAnswer = messageGenerator.createErrorMessage(ERROR_WRONG_MOVE, errorText);
                    server.sendMessage(connectionFrom, errorAnswer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }

            float agile = gameController.getRandomKnifeFallAngle();
            float[] floats = new float[1];
            floats[0] = agile;
            int[] ints = new int[1];
            if(agile >= MIN_ANGLE){
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    Message answer = messageGenerator.createMessage(MOVE_RESULT_GOOD, floats, ints); //13
                    server.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    Message answer = messageGenerator.createMessage(MOVE_RESULT_BAD, floats, ints); //14
                    server.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            try{
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "Invalid message format");
                server.sendMessage(connectionFrom, errorAnswer);
            } catch (MessageGenerationException | ServerException e) {
                e.printStackTrace();
            }
        }
    }

    private String isAnyError(Point point1, Point point2){
        if(!gameController.checkPointIsInCircle(point1)){
            return "The player's location is outside the circle";
        }
        if(!gameController.checkPointIsInCircle(point2)){
            return "The throwing point is outside the circle";
        }
        if(gameController.checkPointBelongsToPlayerRegion(point1, gameController.getOpponentPlayer())){
            return "The player is not on his territory";
        }
        if(gameController.checkPointBelongsToPlayerRegion(point2, gameController.getCurrentPlayer())){
            return "The throwing point is not the opponent's territory";
        }
        return null;
    }
}
