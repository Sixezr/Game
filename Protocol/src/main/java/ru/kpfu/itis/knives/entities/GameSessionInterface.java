package ru.kpfu.itis.knives.entities;

import java.util.List;

public interface GameSessionInterface {
    void putNewPayerRegionPair(Player player, Region region);

    void updatePlayerRegionPair(Player player, Region region);

    Player getCurrentPlayer();

    void setCurrentPlayer(Player currentPlayer);

    List<Player> getPlayers();

    Region getPlayerRegion(Player player);

    int getPlayersCount();

    void invalidate();

    void init();
}
