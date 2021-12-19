package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.listeners.ServerMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import java.net.Socket;

public interface ServerInterface {
    /**
     * Инициализация параметров сервера
     * @throws ServerException
     */
    void initServer() throws ServerException;

    /**
     * Инициализация обработчиков сообщений
      * @param listener
     * @throws ServerException
     */
    void registerListener(ServerMessageListener listener) throws ServerException;

    /**
     * Начало работы сервера, получение точек соединения с клиентами, создание Connection с каждым из них посредством вызова createConnection()
     * @throws ServerException
     */
    void startServer() throws ServerException;

    /**
     * Создание точки соединения с клиентом, отсюда сообщения приходят и сюда же отправляются
     * @param clientSocket
     * @return
     * @throws ServerException
     */
    Connection createConnection(Socket clientSocket) throws ServerException;

    // тут обойдёмся без жаба-дока, итак понятно, шо эти методы делают (по факту, они будут делегировать отправку сообщений классу соединение.... есть мысль создать аб
    // абстрактный класс сервера, где прописать эту логику..............
    void sendMessage(Connection connection, Message message) throws ServerException;
    void sendBroadcastMessage(Message message) throws ServerException;

    /**
     * Принятие сервером сообщения, чтобы он передал его нужному обработчику
     * @param connectionFrom откуда пришло сообщение
     * @param message
     */
    void acceptMessage(Connection connectionFrom, Message message);

}
