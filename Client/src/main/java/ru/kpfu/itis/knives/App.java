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
    private static Stage mainStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        mainStage.setHeight(screenBounds.getHeight());
        mainStage.setWidth(screenBounds.getWidth());

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        mainStage.setTitle(Constants.MAIN_NAME);

        AbstractController initialController = new InitialController(mainStage);
        initialController.createScene();
        primaryStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
