package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.*;

import java.util.List;
import java.util.Random;

import static ru.kpfu.itis.knives.Constants.*;

public class ServerGameController extends AbstractGameController implements ServerGameControllerInterface {
    private static final float DELTA = (float) 1e-3;
    private static final float MIN_ANGLE = 0.0f;
    private static final float MAX_ANGLE = 90.0f;
    private static final int MAX_ID = 1_000_000_000;

    public ServerGameController(GameSession gameSession) {
        super(gameSession);
    }

    @Override
    public int getRandomPlayerId() {
        return Math.abs(new Random().nextInt(MAX_ID));
    }

    @Override
    public Player getCurrentPlayer() {
        return session.getCurrentPlayer();
    }

    @Override
    public Player getOpponentPlayer() {
        List<Player> players = session.getPlayers();
        players.remove(getCurrentPlayer());
        return players.get(0);
    }

    @Override
    public void setNewCurrentPlayer(Player player) {
        session.setCurrentPlayer(player);
    }

    @Override
    public float getRandomKnifeFallAngle() {
        return (float) (Math.random() * (MAX_ANGLE - MIN_ANGLE)) + MIN_ANGLE;
    }

    @Override
    public boolean checkPointIsInCircle(Point point) {
        float r = START_MAX_X - START_0;
        float x = point.getX();
        float y = point.getY();
        return (x * x + y * y < r * r);
    }

    @Override
    public boolean checkPointBelongsToPlayerRegion(Point point, Player player) {
        // todo: maybe some checks
        return session.getPlayerRegion(player).hasPoint(point);
    }

    @Override
    public boolean checkRegionIsIsland(Region region) {
        return region.isIsland();
    }

    @Override
    public boolean isPlayerHasEnoughTerritory(Player player) {
        // todo: THINK BETTER
        return session.getPlayerRegion(player).getSquare() > MIN_SQUARE;
    }

    @Override
    public boolean checkPointBelongsToRegion(Point point, Region region) {
        return region.hasPoint(point);
    }

    @Override
    public void setRandomCurrentPlayer() {
        int MAX = 1;
        int MIN = 0;
        session.setCurrentPlayer(session.getPlayers().get((int) (Math.random() * (MAX - MIN + 1) + MIN)));
    }
}
