package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.ServerGameControllerInterface;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.server.ServerInterface;

public abstract class AbstractMessageListener implements MessageListener {
    protected int TYPE;
    protected ServerInterface server;

    protected ServerGameSession session;
    protected ServerGameControllerInterface gameController;
    protected MessageGeneratorInterface messageGenerator;

    public AbstractMessageListener(int type) {
        this.TYPE = type;
    }

    @Override
    public void init(ServerInterface knivesServer) {
        this.server = knivesServer;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public void setGameController(ServerGameControllerInterface gameController) {
        this.gameController = gameController;
    }

    @Override
    public void setMessageGenerator(MessageGeneratorInterface messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void setGameSession(ServerGameSession session) {
        this.session = session;
    }
}
