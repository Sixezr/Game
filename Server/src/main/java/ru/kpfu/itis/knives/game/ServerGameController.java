package ru.kpfu.itis.knives.game;

import ru.kpfu.itis.knives.entities.GameControllerInterface;
import ru.kpfu.itis.knives.entities.Player;

public interface ServerGameController extends GameControllerInterface {
    int getRandomPlayerId();
    Player getAnotherPlayer(int id);
    float getRandomKnifeFallAngle();
    boolean checkPlayerRegionIsIsland(Player player);
    boolean isPlayerHasEnoughTerritory(Player player);
    void setRandomCurrentPlayer();
    void invalidateGameSession();
}
