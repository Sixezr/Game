package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.entities.Player;
import ru.kpfu.itis.knives.exceptions.ConnectionException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.streams.ProtocolInputStream;
import ru.kpfu.itis.knives.streams.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ConnectionImpl implements Connection {
    private final MessageHandler server;
    private ServerGameSession session;
    private final Socket clientSocket;
    private Player player;
    private final ProtocolOutputStream outputStream;
    private final ProtocolInputStream inputStream;
    private boolean isReady;

    public ConnectionImpl(MessageHandler server, Socket clientSocket) throws ConnectionException {
        this.server = server;
        this.clientSocket = clientSocket;
        isReady = false;
        try {
            inputStream = new ProtocolInputStream(clientSocket.getInputStream());
            outputStream = new ProtocolOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            throw new ConnectionException(ex);
        }
    }

    @Override
    public void setSession(ServerGameSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        Message message;
        try {
            while ((message = inputStream.readMessage()) != null) {
                if (!isReady) {
                    // before got accepted to game session-room
                    server.acceptMessage(this, message);
                } else if (session != null) {
                    // game session created
                    session.acceptMessage(this, message);
                }
            }
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void sendMessage(Message message) throws ConnectionException {
        Thread newThread = new Thread( () -> {
            try {
                outputStream.writeMessage(message);
                outputStream.flush();
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
        });
        newThread.start();
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionImpl that = (ConnectionImpl) o;
        return Objects.equals(clientSocket, that.clientSocket) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSocket, player);
    }
}
