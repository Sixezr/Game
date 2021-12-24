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

public class StartMoveListener extends AbstractMessageListener {

    public StartMoveListener(){
        super(MOVE);
    } //32

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
                    System.out.println("Move accepted. WRONG MOVE: " + errorText);
                    Message errorAnswer = messageGenerator.createErrorMessage(ERROR_WRONG_MOVE, errorText); //41
                    session.sendMessage(connectionFrom, errorAnswer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }

            float agile = gameController.getRandomKnifeFallAngle();
            int[] ints = new int[1];
            if(agile >= MIN_ANGLE){
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    float[] floats = new float[5];
                    floats[0] = agile;
                    floats[1] = x1;
                    floats[2] = y1;
                    floats[3] = x2;
                    floats[4] = y2;
                    Message answer = messageGenerator.createMessage(MOVE_RESULT_GOOD, floats, ints); //13
                    System.out.println("Move accepted; angle = " + agile + "; \n (" + x1 + ", " + y1 +")  (" + x2 + ", " + y2 + ");");
                    session.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
            else{
                try{
                    ints[0] = gameController.getOpponentPlayer().getId();
                    gameController.setNewCurrentPlayer(gameController.getOpponentPlayer());
                    float[] floats = new float[1];
                    floats[0] = agile;
                    System.out.println("Move accepted: angle = " + agile);
                    Message answer = messageGenerator.createMessage(MOVE_RESULT_BAD, floats, ints); //14
                    session.sendBroadcastMessage(answer);
                } catch (MessageGenerationException | ServerException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            try{
                Message errorAnswer = messageGenerator.createErrorMessage(ERROR_BAD_MESSAGE, "You can't perform this action"); //40
                session.sendMessage(connectionFrom, errorAnswer);
            } catch (MessageGenerationException | ServerException e) {
                e.printStackTrace();
            }
        }
    }

    private String isAnyError(Point point1, Point point2){
        if(gameController.isPointInCircle(point1)){
            return "The player's location is outside the circle";
        }
        if(gameController.isPointInCircle(point2)){
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

    @Override
    public MessageListener getNewInstance() {
        StartMoveListener newInstance = new StartMoveListener();
        newInstance.init(server);
        return newInstance;
    }
}
