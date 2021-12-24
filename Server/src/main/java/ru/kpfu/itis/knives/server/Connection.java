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

public class Connection implements Runnable {
    private ServerInterface server;
    private ServerGameSession session;
    private Socket clientSocket;
    private Player player;
    private ProtocolOutputStream outputStream;
    private ProtocolInputStream inputStream;
    private boolean isReady;

    public Connection(ServerInterface server, Socket clientSocket) throws ConnectionException {
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

    public void setSession(ServerGameSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        Message message;
        try {
            while ((message = inputStream.readMessage()) != null) {
                System.out.println("\n Got new message from player with id= " + player.getId());
                System.out.println(message + "\n");
                if (!isReady) {
                    server.acceptMessage(this, message);
                } else {
                    session.acceptMessage(this, message);
                }
            }
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    public void sendMessage(Message message) throws ConnectionException {
        Thread newThread = new Thread( () -> {
            try {
                outputStream.writeMessage(message);
                outputStream.flush();
                System.out.println("\nMessage to client " + player.getId() + " was sent\n");
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
        });
        newThread.start();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(clientSocket, that.clientSocket) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSocket, player);
    }
}
