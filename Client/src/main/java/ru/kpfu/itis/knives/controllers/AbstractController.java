package ru.kpfu.itis.knives.controllers;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class AbstractController {
    protected final Stage stage;

    protected AbstractController(Stage stage) {
        this.stage = stage;
    }

    public abstract void createScene();

    protected void animate(Node node) {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }
}
