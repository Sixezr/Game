package ru.kpfu.itis.knives.generators;

import ru.kpfu.itis.knives.protocol.Message;

public interface MessageGenerator {

    Message clientReady();

    Message move(int id, float x1, float y1, float x2, float y2);

    Message movePosition(int id, float x1, float y1);

    Message clientLeft(int id);
}
