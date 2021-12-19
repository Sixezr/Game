package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.AbstractGameController;
import ru.kpfu.itis.knives.entities.GameSession;

import java.util.Random;

public class ServerGameController extends AbstractGameController implements ServerGameControllerInterface {
    public ServerGameController(GameSession gameSession) {
        super(gameSession);
    }

    @Override
    public int getRandomPlayerId() {
        return (new Random().nextInt());
    }
}
