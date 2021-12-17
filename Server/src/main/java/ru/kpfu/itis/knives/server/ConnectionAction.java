package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;

public class ConnectionAction implements Runnable {
    // соединение с клиентом, здесь хранится сокет, сессия,
    // при получении сообщения, соединение отдаёт сигнал серверу,
    // чтобы тот его обработал (точнее он тоже это проделегирует дальше ХАХАХАХ)


    @Override
    public void run() {

    }

    public void sendMessage(Message message) throws IOException {

    }
}
