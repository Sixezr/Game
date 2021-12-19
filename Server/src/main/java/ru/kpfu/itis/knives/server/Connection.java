package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.streams.ProtocolInputStream;
import ru.kpfu.itis.knives.streams.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {
    private KnivesServerInterface server;
    private Socket clientSocket;
    private Player player;
    private ProtocolOutputStream outputStream;
    private ProtocolInputStream inputStream;

    public Connection(Socket clientSocket, KnivesServerInterface server) throws ConnectionException {
        this.clientSocket = clientSocket;
        this.server = server;

        try {
            inputStream = new ProtocolInputStream(clientSocket.getInputStream());
            outputStream = new ProtocolOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            throw new ConnectionException(ex);
        }
    }

    @Override
    public void run() {
        Message message;
        try {
            while ((message = inputStream.readMessage()) != null) {
                server.acceptMessage(this, message);
            }
        } catch (IOException e) {
            // пока неизвестно что делать в другом потоке и неизвестно нормально ли этот поток будет работать
        }
    }

    public void sendMessage(Message message) throws ConnectionException {
        try {
            outputStream.writeMessage(message);
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
