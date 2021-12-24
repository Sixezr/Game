package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.GameSessionInterface;
import ru.kpfu.itis.knives.exceptions.GameSessionException;
import ru.kpfu.itis.knives.listeners.MessageListener;
import ru.kpfu.itis.knives.server.MessageHandler;

import java.util.List;

public interface ServerGameSession extends MessageHandler, GameSessionInterface {
    void startGame() throws GameSessionException;
    void interruptGame() throws GameSessionException;
    void endGame() throws GameSessionException;
    void initListeners(List<MessageListener> listenersFromServer);
}
