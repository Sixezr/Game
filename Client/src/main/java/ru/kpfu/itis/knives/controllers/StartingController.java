package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;
import ru.kpfu.itis.knives.view.StartingVBox;

import java.util.Timer;
import java.util.TimerTask;

public final class StartingController extends AbstractController {
    // UI
    private final BorderPane mainPane;

    // Properties
    private final boolean isCurrentPlayerFirst;

    // Init
    public StartingController(Stage stage, SocketClient socketClient, boolean isCurrentPlayerFirst) {
        super(stage, socketClient);
        mainPane = new BorderPane();
        this.isCurrentPlayerFirst = isCurrentPlayerFirst;

        stage.setMinWidth(750);
        stage.setMinHeight(400);
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(new StartingVBox(isCurrentPlayerFirst));
        mainPane.setBottom(new ProgressHBox("Игра начинается"));

        stage.setScene(new Scene(mainPane));
        animate(mainPane);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AbstractController gameController = new GameController(stage, socketClient);
                socketClient.setController(gameController);
                gameController.createScene();
            }
        }, 3000);
    }
}
