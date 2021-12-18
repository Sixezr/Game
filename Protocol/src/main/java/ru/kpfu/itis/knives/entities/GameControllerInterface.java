package ru.kpfu.itis.knives.entities;

public interface GameControllerInterface {
    Region createStartRegion(Player player, boolean isLeft);
    void addPlayer(Player player);
    // ещё нужен общий метод для пересчёта границ но я пока хз как это делать
}
