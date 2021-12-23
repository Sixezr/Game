package ru.kpfu.itis.knives.client;

import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.listeners.ClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

public interface SocketClient {
    void ready();

    void initSession(int thisId, int opponentId, int currentId);

    void move(Point from, Point to);

    void end(int winnerID);

    void paintAngle(float angle);

    void setMove(int moveID);

    void setNotice(String information);

    void choiceRegion(Point point);

    int getID();

    void left(int leaverID);

    void sendMessage(Message message);

    void acceptMessage(Message message);

    void registerListener(ClientMessageListener listener);
}
