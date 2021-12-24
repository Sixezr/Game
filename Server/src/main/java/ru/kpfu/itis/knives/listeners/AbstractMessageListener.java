package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.ServerGameController;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.server.Server;

public abstract class AbstractMessageListener implements MessageListener {
    protected int TYPE;
    protected Server server;
    protected ServerGameSession session;
    protected ServerGameController gameController;
    protected MessageGenerator messageGenerator;

    public AbstractMessageListener(int type) {
        this.TYPE = type;
    }

    @Override
    public void init(Server knivesServer) {
        this.server = knivesServer;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public void setGameController(ServerGameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void setGameSession(ServerGameSession session) {
        this.session = session;
    }
}
