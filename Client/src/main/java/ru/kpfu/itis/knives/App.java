package ru.kpfu.itis.knives;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.controllers.GameController;
import ru.kpfu.itis.knives.helpers.Constants;

public final class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setWidth(screenBounds.getWidth());

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        primaryStage.setTitle(Constants.MAIN_NAME);

        AbstractController initialController = new GameController(primaryStage);
        initialController.createScene();
        primaryStage.show();
    }
}
