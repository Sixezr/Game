package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.entities.Point;

public interface ServerGameControllerInterface {
    int getRandomPlayerId();
    Player getCurrentPlayer();
    Player getOpponentPlayer();
    void setNewCurrentPlayer(Player player);
    float getRandomKnifeFallAngle();
    boolean checkPointIsInCircle(Point point);
    void recalculateRegions(Point currentUserPoint, Point knifeFallPoint);
    boolean checkPointBelongsToPlayerRegion(Point point, Player player);
    boolean checkPlayerRegionIsIsland(Player player);
    boolean isPlayerHasEnoughTerritory(Player player);
    void setRandomCurrentPlayer();
}
