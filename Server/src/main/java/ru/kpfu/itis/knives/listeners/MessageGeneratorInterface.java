package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.protocol.Message;

public interface MessageGeneratorInterface {
    Message generateErrorMessage(byte type, String errorMessage);
    Message createEmptyMessage(byte type);
    Message generateMessage(byte type,  float[] values, int[] ids);
    Message generateMessage(byte type, int[] ids);
    Message generateMessage(byte type, float[] values);
}
