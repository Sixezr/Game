package ru.kpfu.itis.knives.entities;

public interface GameControllerInterface {
    Region createStartRegion(Player player, boolean isLeft);
    void addPlayer(Player player);
    void divideOpponentRegion(Point currentUserPoint, Point knifeFallPoint);
    boolean isPointInCircle(Point point);
    boolean checkPointBelongsToPlayerRegion(Point point, Player player);
    void recalculateRegions(Point opponentChoice);
    Player getCurrentPlayer();
    Player getOpponentPlayer();
    void setNewCurrentPlayer(Player player);
}
