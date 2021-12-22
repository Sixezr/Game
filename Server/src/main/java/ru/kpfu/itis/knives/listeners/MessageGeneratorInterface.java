package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.protocol.Message;

public interface MessageGeneratorInterface {
    Message createErrorMessage(byte type, String errorMessage);
    Message createEmptyMessage(byte type);
    Message createMessage(byte type,  float[] values, int[] ids);
    Message createMessage(byte type, int[] ids);
    Message createMessage(byte type, float[] values);
}
