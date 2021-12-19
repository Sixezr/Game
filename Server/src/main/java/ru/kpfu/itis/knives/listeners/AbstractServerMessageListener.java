package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.ServerGameControllerInterface;
import ru.kpfu.itis.knives.server.KnivesServerInterface;

public abstract class AbstractServerMessageListener implements ServerMessageListener {
    protected int TYPE;
    protected ServerGameControllerInterface gameController;
    protected KnivesServerInterface server;

    public AbstractServerMessageListener() {
    }

    @Override
    public void init(KnivesServerInterface knivesServer, ServerGameControllerInterface gameController) {
        this.server = knivesServer;
        this.gameController = gameController;
    }

    @Override
    public int getType() {
        return TYPE;
    }

//    private abstract class EventMessageParser {
//        void parseMessage() {
//
//        }
//    }
}
