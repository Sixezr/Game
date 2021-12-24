package ru.kpfu.itis.knives.protocol;

import java.util.Arrays;
import java.util.Objects;

import static ru.kpfu.itis.knives.protocol.Protocol.START_BYTES;

public class Message {
    private byte type;
    private byte[] data;

    public Message(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public Message(byte type) {
        this.type = type;
        data = new byte[0];
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength() {
        return data.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return type == message.type && Arrays.equals(data, message.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] data = this.getData();
        String delimiter = " ";
        String newLine = System.getProperty("line.separator");

        stringBuilder.append("\t Message : ");
        stringBuilder.append(newLine);
        stringBuilder.append("Type = ").append(this.getType()).append(newLine);
        stringBuilder.append("Data Length = ").append(this.getDataLength()).append(newLine);
        stringBuilder.append("Data = ");
        for (int i = 0; i < this.getDataLength(); i++) {
            stringBuilder.append(data[i]);
            stringBuilder.append(delimiter);
        }

        return stringBuilder.toString();
    }
}
