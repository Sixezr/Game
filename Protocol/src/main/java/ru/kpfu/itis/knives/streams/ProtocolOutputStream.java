package ru.kpfu.itis.knives.streams;

import ru.kpfu.itis.knives.protocol.Message;

import java.io.IOException;
import java.io.OutputStream;

public class ProtocolOutputStream extends OutputStream {
    private final OutputStream outputStream;

    public ProtocolOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeMessage(Message message) throws IOException {
        byte type = message.getType();
        byte[] data = message.getData();
        int length = message.getDataLength();
        outputStream.write(type);
        outputStream.write(length >> 8);
        outputStream.write(length);
        outputStream.write(data);
        outputStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
