package ru.kpfu.itis.knives;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.listeners.ClientLeftListener;
import ru.kpfu.itis.knives.listeners.StartGameListener;
import ru.kpfu.itis.knives.listeners.StartMoveListener;
import ru.kpfu.itis.knives.listeners.TerritoryChoiceListener;
import ru.kpfu.itis.knives.protocol.Protocol;
import ru.kpfu.itis.knives.server.KnivesServer;

public class Main {

    public static void main(String[] args) {
        try {
            KnivesServer knivesServer = new KnivesServer(Protocol.PORT);
            knivesServer.registerListener(new StartGameListener());
            knivesServer.registerListener(new StartMoveListener());
            knivesServer.registerListener(new TerritoryChoiceListener());
            knivesServer.registerListener(new ClientLeftListener());

            knivesServer.startServer();
        } catch (ServerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
