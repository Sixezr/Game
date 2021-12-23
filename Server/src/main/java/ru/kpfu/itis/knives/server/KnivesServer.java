package ru.kpfu.itis.knives.server;

import ru.kpfu.itis.knives.exceptions.ServerException;
import ru.kpfu.itis.knives.game.ServerGameSession;
import ru.kpfu.itis.knives.listeners.MessageListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.kpfu.itis.knives.Constants.MAX_PLAYER_NUM;
import static ru.kpfu.itis.knives.Constants.PORT;

public class KnivesServer implements ServerInterface {
    private ServerSocket serverSocket;

    private List<MessageListener> listeners;
    private List<ServerGameSession> sessions;
    private List<Socket> clients;

    public KnivesServer() {
        initServer();
    }

    @Override
    public void initServer() throws ServerException {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new ServerException(e);
        }
        listeners = new ArrayList<>();
        sessions = new ArrayList<>();
        clients = new ArrayList<>();
    }

    @Override
    public void registerListener(MessageListener listener) throws ServerException {
        listener.init(this);
        listeners.add(listener);
    }

    @Override
    public void startServer() throws ServerException {
        while (true) {
            while (clients.size() < MAX_PLAYER_NUM) {
                try {
                    Socket client = serverSocket.accept();
                    clients.add(client);
                } catch (IOException e) {
                    throw new ServerException(e);
                }
            }
            ServerGameSession gameSession = new ServerGameSession(this, clients.get(0), clients.get(1));
            gameSession.initListeners(listeners);
            sessions.add(gameSession);

            Thread newSession = new Thread(gameSession);
            newSession.start();

            clients.clear();
        }
    }

    @Override
    public void removeSession(ServerGameSession gameSession) {
        sessions.remove(gameSession);
    }


}
