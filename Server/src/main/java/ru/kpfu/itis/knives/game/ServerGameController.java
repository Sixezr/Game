package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.*;

import java.util.List;
import java.util.Random;

import static ru.kpfu.itis.knives.Constants.MIN_SQUARE;

public class ServerGameController extends AbstractGameController implements ServerGameControllerInterface {
    private static final float DELTA = (float) 1e-3;

    public ServerGameController(GameSession gameSession) {
        super(gameSession);
    }

    @Override
    public int getRandomPlayerId() {
        return (new Random().nextInt());
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
        return 0;
    }

    @Override
    public boolean checkPointIsInCircle(Point point) {
        List<Player> players = session.getPlayers();
        for (Player player : players) {
            if (session.getPlayerRegion(player).hasPoint(point)) {
                return true;
            }
        }
        return false;
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

    }
}
