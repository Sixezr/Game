package ru.kpfu.itis.knives;

import ru.kpfu.itis.knives.server.KnivesServer;

public class Main {

    public static void main(String[] args) {
        KnivesServer knivesServer = new KnivesServer();
        knivesServer.startServer();
    }
}
