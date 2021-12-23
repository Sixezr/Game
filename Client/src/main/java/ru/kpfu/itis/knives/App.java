package ru.kpfu.itis.knives;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.controllers.InitialController;
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
            // TODO: exit from room on server, if there is
            Platform.exit();
        });
        primaryStage.setTitle(Constants.MAIN_NAME);

        AbstractController initialController = new InitialController(primaryStage);
        initialController.createScene();
        primaryStage.show();
    }
}
