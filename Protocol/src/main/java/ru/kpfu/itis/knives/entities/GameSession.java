package ru.kpfu.itis.knives.entities;

import java.util.*;

public class GameSession {
    private int id;
    private final Map<Player, Region> playerRegionMap;
    private Player currentPlayer;

    public GameSession() {
        id = new Random().nextInt();
        playerRegionMap = new HashMap<>();
        currentPlayer = null;
    }

    public void putNewPayerRegionPair(Player player, Region region) {
        playerRegionMap.put(player, region);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Player> getPlayers() {
       return new ArrayList<>(playerRegionMap.keySet());
    }

    public Region getPlayerRegion(Player player) {
       return playerRegionMap.get(player);
    }

    public int getPlayersCount() {
        return playerRegionMap.size();
    }

    @Override
    public boolean equals(Object o) {
        // вообще, тут можно сессии только по id сравнивать, но эта логика нам ни к чему... зачем я пишу тут комментарии....
        if (this == o) return true;
        if (!(o instanceof GameSession)) return false;
        GameSession that = (GameSession) o;
        return id == that.id && Objects.equals(playerRegionMap, that.playerRegionMap) && Objects.equals(currentPlayer, that.currentPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerRegionMap, currentPlayer);
    }
}
