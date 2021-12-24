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

    public ServerGameController() {
        super();
    }

    @Override
    public int getRandomPlayerId() {
        return Math.abs(new Random().nextInt(MAX_ID));
    }

    @Override
    public Player getAnotherPlayer(int id) {
        List<Player> players = session.getPlayers();
        for (Player player : session.getPlayers()) {
            if (player.getId() == id) {
                players.remove(player);
            }
        }
        System.out.println("PLAYERS: " + session.getPlayers().get(0).getId());
        System.out.println(session.getPlayers().get(1).getId());
        return players.get(0);
    }

    @Override
    public float getRandomKnifeFallAngle() {
        return (float) (Math.random() * (MAX_ANGLE - MIN_ANGLE)) + MIN_ANGLE;
    }

    @Override
    public boolean isPointInCircle(Point point) {
        float r = START_MAX_X - START_0;
        float x = point.getX();
        float y = point.getY();
        return (!(x * x + y * y < r * r));
    }

    @Override
    public boolean checkPointBelongsToPlayerRegion(Point point, Player player) {
        return session.getPlayerRegion(player).hasPoint(point);
    }

    @Override
    public boolean checkPlayerRegionIsIsland(Player player) {
        return session.getPlayerRegion(player).isIsland();
    }


    @Override
    public boolean isPlayerHasEnoughTerritory(Player player) {
        return session.getPlayerRegion(player).getSquare() > MIN_SQUARE;
    }

    @Override
    public void setRandomCurrentPlayer() {
        int MAX = 1;
        int MIN = 0;
        session.setCurrentPlayer(session.getPlayers().get((int) (Math.random() * (MAX - MIN + 1) + MIN)));
    }

    @Override
    public void invalidateGameSession() {
        session.invalidate();
    }

    @Override
    public void createNewGameSession() {
        session.invalidate();
        session.init();
    }
}
