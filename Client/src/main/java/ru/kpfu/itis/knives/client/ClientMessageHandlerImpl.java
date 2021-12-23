package ru.kpfu.itis.knives.client;

import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.streams.ProtocolInputStream;
import ru.kpfu.itis.knives.streams.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;

public class ClientMessageHandlerImpl implements ClientMessagesHandler {
    private SocketClient socketClient;
    private Socket socket;
    private ProtocolInputStream inputStream;
    private ProtocolOutputStream outputStream;

    public ClientMessageHandlerImpl(SocketClient socketClient, Socket socket) {
        this.socket = socket;
        this.socketClient = socketClient;
        try {
            inputStream = new ProtocolInputStream(socket.getInputStream());
            outputStream = new ProtocolOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            // todo exception handling
            e.printStackTrace();
        }
    }

    @Override
    public void readMessage() {
        Thread newThread = new Thread(() -> {
                while (true) {
                    try {
                        Message message;
                        while ((message = inputStream.readMessage()) != null) {
                            socketClient.acceptMessage(message);
                        }
                    } catch (IOException e) {
                    // todo exceptions handling
                    e.printStackTrace();
                }
                }
        });
    }

    @Override
    public void sendMessage(Message message) {
        Thread newThread = new Thread(() -> {
            try {
                outputStream.writeMessage(message);
                outputStream.flush();
            } catch (IOException e) {
                // todo exceptions handling
                e.printStackTrace();
            }
        });
    }
}
