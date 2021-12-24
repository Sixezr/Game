package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.protocol.Message;

public interface Connection extends Runnable {
    void setSession(ServerGameSession session);

    void sendMessage(Message message);

    void setPlayer(Player player);

    Player getPlayer();

    void setReady(boolean ready);

    boolean isReady();
}
