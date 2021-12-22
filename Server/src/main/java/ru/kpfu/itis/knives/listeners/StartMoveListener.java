package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.exceptions.IllegalMessageTypeException;
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
        int clientId = byteBuffer.get(0);
        if(clientId == gameController.getCurrentPlayer().getId()){
            float x1 = byteBuffer.get(1);
            float y1 = byteBuffer.get(2);
            float x2 = byteBuffer.get(3);
            float y2 = byteBuffer.get(4);
            Point point1 = new Point(x1, y1);
            Point point2 = new Point(x2, y2);

            String errorText = null;
            boolean isAnyError = false;
            if(gameController.checkPointIsInCircle(point1)){
                errorText = "The player's location is outside the circle";
                isAnyError = true;
            }
            if(gameController.checkPointIsInCircle(point2)){
                errorText = "The throwing point is outside the circle";
                isAnyError = true;
            }
            if(gameController.checkPointBelongsToPlayerRegion(point1, gameController.getOpponentPlayer())){
                errorText = "The player is not on his territory";
                isAnyError = true;
            }
            if(gameController.checkPointBelongsToPlayerRegion(point2, gameController.getCurrentPlayer())){
                errorText = "The throwing point is not the opponent's territory";
                isAnyError = true;
            }
            if(isAnyError){
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_WRONG_MOVE, errorText);
                try{
                    server.sendMessage(connectionFrom, errorAnswer);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
            }

            float agile = gameController.getRandomKnifeFallAngle();
            float[] floats = new float[1];
            floats[0] = agile;
            int[] ints = new int[1];
            if(agile >= MIN_ANGLE){
                ints[0] = gameController.getCurrentPlayer().getId();
                Message answer = messageGenerator.createMessage(MOVE_RESULT_GOOD, floats, ints); //13
                try{
                    server.sendMessage(connectionFrom, answer);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
            }
            else{
                ints[0] = gameController.getOpponentPlayer().getId();
                Message answer = messageGenerator.createMessage(MOVE_RESULT_BAD, floats, ints); //14
                try{
                    server.sendMessage(connectionFrom, answer);
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
}
