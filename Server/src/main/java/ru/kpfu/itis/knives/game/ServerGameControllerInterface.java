package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.entities.Point;
import ru.kpfu.itis.knives.entities.Region;

public interface ServerGameControllerInterface {
    int getRandomPlayerId();
    Player getCurrentPlayer();
    float getRandomKnifeFallAngle();
    boolean checkPointIsInCircle(Point point);
    boolean checkPointBelongsToPlayerRegion(Point point, Player player);
    boolean checkRegionIsIsland(Region region);
    boolean isPlayerHasEnoughTerritory(Player player);
    boolean checkPointBelongsToRegion(Point point, Region region);
    void setRandomCurrentPlayer();
}
