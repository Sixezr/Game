package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.InitialVBox;
import ru.kpfu.itis.knives.view.KnifeLocationCanvas;
import ru.kpfu.itis.knives.view.ProgressHBox;

public final class InitialController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final InitialVBox initialVBox;

    // Init
    public InitialController(Stage stage, SocketClient socketClient) {
        super(stage, socketClient);
        mainPane = new BorderPane();
        initialVBox = new InitialVBox();

        stage.setMinWidth(750);
        stage.setMinHeight(400);
        addActions();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(initialVBox);
        mainPane.setBottom(new ProgressHBox("Начните игру"));

        stage.setScene(new Scene(mainPane));
        animate(mainPane);
    }

    @Override
    public KnifeLocationCanvas getKnifeLocationCanvas() {
        return new KnifeLocationCanvas();
    }

    // Private
    private void addActions() {
        initialVBox.getStartButton().setOnAction(event -> {
            socketClient.ready();
            System.out.println("готово");
            AbstractController waitingController = new WaitingController(stage, socketClient);
            socketClient.setController(waitingController);
            waitingController.createScene();
        });
    }
}
