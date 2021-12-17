package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.game.GameSession;
import ru.kpfu.itis.knives.protocol.Message;

public interface ServerEventListener {
    void init(GameSession session);
    void handleMessage(Message message);
    int getType();
}
