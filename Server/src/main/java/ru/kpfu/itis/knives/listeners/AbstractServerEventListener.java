package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.GameSession;

public abstract class AbstractServerEventListener implements ServerEventListener {
    private final int TYPE;
    private GameSession gameSession;

    public AbstractServerEventListener(int type) {
        this.TYPE = type;
    }

    @Override
    public void init(GameSession session) {
        this.gameSession = gameSession;
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
