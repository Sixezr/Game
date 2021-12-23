package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.streams.ProtocolInputStream;
import ru.kpfu.itis.knives.streams.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {
    private ServerGameSession session;
    private Socket clientSocket;
    private Player player;
    private ProtocolOutputStream outputStream;
    private ProtocolInputStream inputStream;

    public Connection(Socket clientSocket, ServerGameSession session) throws ConnectionException {
        this.clientSocket = clientSocket;
        this.session = session;

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
                session.acceptMessage(this, message);
            }
        } catch (IOException e) {
            // пока неизвестно что делать в другом потоке и неизвестно нормально ли этот поток будет работать
        }
    }

    public void sendMessage(Message message) throws ConnectionException {
        // todo : another thread maybe
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
