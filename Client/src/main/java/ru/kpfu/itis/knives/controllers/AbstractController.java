package ru.kpfu.itis.knives.controllers;

import javafx.stage.Stage;

public abstract class AbstractController {
    protected final Stage stage;

    protected AbstractController(Stage stage) {
        this.stage = stage;
    }

    public abstract void createScene();
}
