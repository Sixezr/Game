package ru.kpfu.itis.knives;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Constants;
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

        primaryStage.setTitle(Constants.MAIN_NAME);
        primaryStage.setScene(new InitialScene());
        primaryStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
