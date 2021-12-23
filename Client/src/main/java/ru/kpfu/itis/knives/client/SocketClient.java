package ru.kpfu.itis.knives.client;

public interface SocketClient {

    void initSession(int anInt, int anInt1);

    void move();

    void end(int winnerID);

    void paintTangle(float tangle);

    void setMove(int moveID);

    void setNotice(String information);

    void choiceRegion();

    void repeat();

    int getID();

    void left(int leaverID);

    void ready();
}
