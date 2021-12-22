package ru.kpfu.itis.knives;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.scenes.GameScene;
import ru.kpfu.itis.knives.scenes.InitialScene;

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
        primaryStage.setTitle(Constants.MAIN_NAME);
        primaryStage.setScene(new GameScene(new BorderPane()));
        primaryStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
