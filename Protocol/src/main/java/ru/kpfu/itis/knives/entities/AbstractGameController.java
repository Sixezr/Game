package ru.kpfu.itis.knives.entities;

import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.Constants.*;

public abstract class AbstractGameController implements GameControllerInterface {
    private final GameSession session;

    public AbstractGameController(GameSession session) {
        this.session = session;
    }

    @Override
    public void addPlayer(Player player) {
        if (session.getPlayersCount() == MAX_PLAYER_NUM) {
            // можно выбросить исключение, можно ничего не делать
        } else {
            boolean flag = (session.getPlayersCount() == 0);                        // если true, создаётся территория для игрока впервые, пусть будет создаваться левая часть круга
            session.putNewPayerRegionPair(player, createStartRegion(player, flag));
        }
    }

    @Override
    public Region createStartRegion(Player player, boolean isLeft) {
        Point left = new Point(START_MIN_X, START_0);
        Point up = new Point(START_0, START_MAX_Y);
        Point right = new Point(START_MAX_X, START_0);
        Point down = new Point(START_0, START_MIN_Y);

        Bound bound1 = new Bound(false, up, right);
        Bound bound2 = new Bound(false, up, left);
        Bound bound3 = new Bound(false, down, left);
        Bound bound4 = new Bound(false, down, right);

        Bound line = new Bound(true, up, down);

        List<Bound> bounds = new ArrayList<>();
        if (isLeft) {
            bounds.add(bound2);
            bounds.add(bound3);
        } else {
            bounds.add(bound1);
            bounds.add(bound4);
        }
        bounds.add(line);

        return new Region(player, bounds);
    }
}
