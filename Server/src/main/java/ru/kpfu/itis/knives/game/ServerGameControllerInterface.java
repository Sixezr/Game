package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.GameControllerInterface;
import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.entities.Point;

public interface ServerGameControllerInterface extends GameControllerInterface {
    int getRandomPlayerId();
    float getRandomKnifeFallAngle();
    boolean isPointInCircle(Point point);
    boolean checkPointBelongsToPlayerRegion(Point point, Player player);
    boolean checkPlayerRegionIsIsland(Player player);
    boolean isPlayerHasEnoughTerritory(Player player);
    void setRandomCurrentPlayer();
    void invalidateGameSession();
    void createNewGameSession();
}
