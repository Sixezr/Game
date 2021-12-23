package ru.kpfu.itis.knives.protocol;

public class Protocol {
    // Message contains of: START_BYTES(2), MESSAGE_TYPE(1), DATA_LENGTH(2), DATA(N)
    public static final byte[] START_BYTES = new byte[]{0xB, 0xD};
    public static final int START_BYTES_LENGTH = START_BYTES.length;

    public static final int TYPE_LENGTH = 1;
    // from server
    public static final byte SERVER_READY = 10;
    public static final byte GAME_START = 11;
    public static final byte GAME_END = 12;
    public static final byte GAME_INTERRUPT = 16;
    public static final byte MOVE_RESULT_GOOD = 13;
    public static final byte MOVE_RESULT_BAD = 14;
    public static final byte MOVE_RESULT = 15;
    public static final byte ERROR_BAD_MESSAGE = 40;
    public static final byte ERROR_WRONG_MOVE = 41;
    public static final byte ERROR_WRONG_POS = 42;
    public static final byte ERROR_SERVER = 50;
    //from client
    public static final byte CLIENT_READY = 31;
    public static final byte MOVE = 32;
    public static final byte MOVE_POSITION = 33;
    public static final byte CLIENT_LEFT = 34;

    public static final int PAYLOAD_LENGTH = 2;
    public static final int MAX_MESSAGE_LENGTH = 1000;
}
