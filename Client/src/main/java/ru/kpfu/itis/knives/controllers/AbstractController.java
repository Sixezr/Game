package ru.kpfu.itis.knives.controllers;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.view.KnifeLocationCanvas;

public abstract class AbstractController {
    protected final Stage stage;
    protected final SocketClient socketClient;

    protected AbstractController(Stage stage, SocketClient socketClient) {
        this.stage = stage;
        this.socketClient = socketClient;
    }

    public abstract void createScene();

    protected void animate(Node node) {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    public Stage getStage() {
        return stage;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public abstract KnifeLocationCanvas getKnifeLocationCanvas();
}
