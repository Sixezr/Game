package ru.kpfu.itis.knives.listeners;

import ru.kpfu.itis.knives.exceptions.MessageGenerationException;
import ru.kpfu.itis.knives.protocol.Message;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static ru.kpfu.itis.knives.protocol.Protocol.*;

public class MessageGenerator implements MessageGeneratorInterface {

    @Override
    public Message createErrorMessage(byte type, String errorMessage) {
        if (type == ERROR_BAD_MESSAGE || type == ERROR_WRONG_MOVE || type == ERROR_WRONG_POS || type == ERROR_SERVER) {
            return new Message(type, errorMessage.getBytes(StandardCharsets.UTF_8));
        } else {
            throw new MessageGenerationException("No such error message supported by the protocol");
        }
    }

    /**
     * For SERVER_READY type of message
     * @param type type of message to create
     * @return Message that holds no data
     */
    @Override
    public Message createEmptyMessage(byte type) {
        if (type == SERVER_READY) {
            return new Message(type);
        } else {
            throw new MessageGenerationException("Messages cannot be empty.");
        }
    }

    @Override
    public Message createMessage(byte type, float[] values, int[] ids) {
        Message newMessage = new Message(type);
        switch (type) {
            case MOVE_RESULT_GOOD:
                if (ids.length != 1 && values.length != 5) {
                    throw new MessageGenerationException("Message of this type must contain 2 float value and 1 integer values");
                } else {
                    newMessage.setData(ByteBuffer.allocate(24).putFloat(values[0])
                            .putInt(ids[0])
                            .putFloat(values[1]).putFloat(values[2])
                            .putFloat(values[3]).putFloat(values[4])
                            .array());
                }
                break;
            case MOVE_RESULT_BAD:
                if (ids.length != 1 && values.length != 1) {
                    throw new MessageGenerationException("Message of this type must contain 1 float value and 1 integer values");
                } else {
                    newMessage.setData(ByteBuffer.allocate(8).putFloat(values[0]).putInt(ids[0]).array());
                }
                break;
            case MOVE_RESULT:
                if (ids.length != 1 && values.length != 2) {
                    throw new MessageGenerationException("Message of this type must contain 2 float value and 1 integer values");
                } else {
                    newMessage.setData(ByteBuffer.allocate(12).putInt(ids[0]).putFloat(values[0]).putFloat(values[1]).array());
                }
                break;
            default:
                throw new MessageGenerationException();
        }
        return newMessage;
    }

    @Override
    public Message createMessage(byte type, int[] ids) {
        Message newMessage = new Message(type);
        switch (type) {
            case GAME_START:
                if (ids.length != 3) {
                    throw new MessageGenerationException("Message of this type must contain 2 integer values");
                } else {
                    newMessage.setData(ByteBuffer.allocate(12).putInt(ids[0]).putInt(ids[1]).putInt(ids[2]).array());
                }
                break;
            case CLIENT_LEFT:
            case GAME_INTERRUPT:
            case GAME_END:
                if (ids.length != 1) {
                    throw new MessageGenerationException("Message must contain 1 int value");
                } else {
                    newMessage.setData(ByteBuffer.allocate(4).putInt(ids[0]).array());
                }
                break;
            default:
                throw new MessageGenerationException();
        }
        System.out.println("MESSAGE GENERATED : " + newMessage);
        return newMessage;
     }

    /**
     * No such messages supported by our protocol, to be honest..........
     * @param type
     * @param values
     * @return
     */
    @Override
    public Message createMessage(byte type, float[] values) {
        return new Message(type);
    }
}
