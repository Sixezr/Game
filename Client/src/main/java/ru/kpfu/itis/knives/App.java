package ru.kpfu.itis.knives;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.client.SocketClientImpl;
import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.controllers.InitialController;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.listeners.errors.ErrorBadMessageListener;
import ru.kpfu.itis.knives.listeners.errors.ErrorServerListener;
import ru.kpfu.itis.knives.listeners.errors.ErrorWrongMoveListener;
import ru.kpfu.itis.knives.listeners.errors.ErrorWrongPosListener;
import ru.kpfu.itis.knives.listeners.informations.*;
import ru.kpfu.itis.knives.protocol.Protocol;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        SocketClient socketClient;
        try {
            socketClient = new SocketClientImpl(InetAddress.getByName(ru.kpfu.itis.knives.Constants.HOST), Protocol.PORT);
            initListeners(socketClient);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setWidth(screenBounds.getWidth());

        primaryStage.setOnCloseRequest(event -> {
            // TODO: exit from room on server, if there is
            Platform.exit();
        });
        primaryStage.setTitle(Constants.MAIN_NAME);

        // todo update abstract controller constructor to pass socket client
        AbstractController initialController = new InitialController(primaryStage);
        initialController.createScene();
        primaryStage.show();
    }

    private void initListeners(SocketClient socketClient) {
        socketClient.registerListener(new ServerReadyListener());
        socketClient.registerListener(new MoveResultListener());
        socketClient.registerListener(new MoveResultGoodListener());
        socketClient.registerListener(new MoveResultBadListener());
        socketClient.registerListener(new GameStartListener());
        socketClient.registerListener(new GameInterruptListener());
        socketClient.registerListener(new GameEndListener());
        socketClient.registerListener(new ErrorWrongPosListener());
        socketClient.registerListener(new ErrorWrongMoveListener());
        socketClient.registerListener(new ErrorServerListener());
        socketClient.registerListener(new ErrorBadMessageListener());
    }
}
