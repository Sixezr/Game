package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;

public interface KnivesServerInterface extends ServerInterface {

    /**
     * Изменение статуса сервера, рассылка сообщений игрокам о начале игры
     * @throws ServerException
     */
    void startGame() throws ServerException;


    /**
     * Измененние статуса сервера, рассылка сообщений об окончании игры
     * @throws ServerException
     */
    void endGame() throws ServerException;

}
