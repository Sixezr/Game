package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.*;

import java.util.Random;

public class ServerGameController extends AbstractGameController implements ServerGameControllerInterface {
    public ServerGameController(GameSession gameSession) {
        super(gameSession);
    }

    @Override
    public int getRandomPlayerId() {
        return (new Random().nextInt());
    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public float getRandomKnifeFallAngle() {
        return 0;
    }

    @Override
    public boolean checkPointIsInCircle(Point point) {
        return false;
    }

    @Override
    public boolean checkPointBelongsToPlayerRegion(Point point, Player player) {
        return false;
    }

    @Override
    public boolean checkRegionIsIsland(Region region) {
        return false;
    }

    @Override
    public boolean isPlayerHasEnoughTerritory(Player player) {
        return false;
    }

    @Override
    public boolean checkPointBelongsToRegion(Point point, Region region) {
        return false;
    }
}
