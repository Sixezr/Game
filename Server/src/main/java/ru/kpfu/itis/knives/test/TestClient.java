package ru.kpfu.itis.knives.test;

import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.protocol.Protocol;
import ru.kpfu.itis.knives.streams.ProtocolInputStream;
import ru.kpfu.itis.knives.streams.ProtocolOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import static ru.kpfu.itis.knives.protocol.Protocol.CLIENT_READY;
import static ru.kpfu.itis.knives.protocol.Protocol.GAME_START;

public class TestClient {
    private Socket socket;
    private ProtocolInputStream inputStream;
    private ProtocolOutputStream outputStream;
    private int id;
    private int msgCount = 0;

    public TestClient(int port) throws IOException {
        this.socket = new Socket(InetAddress.getLocalHost(), port);
        inputStream = new ProtocolInputStream(socket.getInputStream());
        outputStream = new ProtocolOutputStream(socket.getOutputStream());
        readMessages();
    }

    private void readMessages() {
        System.out.println("Client is starting to listen to server's messages...");
        Thread newThread = new Thread(() -> {
            Message message;
            while (true) {
                try {
                    if ((message = inputStream.readMessage()) != null) {
                        System.out.println("\n NEW MESSAGE FROM SERVER:");
                        System.out.println(message);
                        msgCount++;

                        if (message.getType() == GAME_START) {
                            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getData());
                            setId(byteBuffer.getInt(0));
                            System.out.println("MY ID = " + getId());
                            send32Message(67.67f, 6.76f, 78.9f, 45f);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        newThread.start();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message send31Message() throws IOException {
        Message newMessage = new Message(CLIENT_READY);
        outputStream.writeMessage(newMessage);
        outputStream.flush();

        return inputStream.readMessage();
    }


    public Message send32Message(float x1, float y1, float x2, float y2) throws IOException {
        Message newMessage = new Message(CLIENT_READY);
        newMessage.setData(ByteBuffer.allocate(20).putInt(id)
                .putFloat(x1).putFloat(y1)
                .putFloat(x2).putFloat(y2).array());
        outputStream.writeMessage(newMessage);
        outputStream.flush();

        return inputStream.readMessage();
    }

    public Message send33Message(float x1, float y1) throws IOException {
        Message newMessage = new Message(CLIENT_READY);
        newMessage.setData(ByteBuffer.allocate(12).putInt(id)
                .putFloat(x1).putFloat(y1).array());
        outputStream.writeMessage(newMessage);
        outputStream.flush();

        return inputStream.readMessage();
    }

    public Message send34Message() throws IOException {
        Message newMessage = new Message(CLIENT_READY);
        newMessage.setData(ByteBuffer.allocate(4).putInt(id).array());
        outputStream.writeMessage(newMessage);
        outputStream.flush();

        return inputStream.readMessage();
    }

    public static void main(String[] args) {
        try {
            TestClient client = new TestClient(Protocol.PORT);
            client.send31Message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
