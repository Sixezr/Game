package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.client.SocketClient;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.KnifeLocationCanvas;
import ru.kpfu.itis.knives.view.ProgressHBox;
import ru.kpfu.itis.knives.view.WaitingVBox;

public final class WaitingController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final WaitingVBox waitingVBox;
    private final HeadMenuBar headMenuBar;

    // Init
    public WaitingController(Stage stage, SocketClient socketClient) {
        super(stage, socketClient);
        mainPane = new BorderPane();
        waitingVBox = new WaitingVBox();
        headMenuBar = new HeadMenuBar();

        stage.setMinWidth(600);
        stage.setMinHeight(400);
        addActions();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(headMenuBar);
        mainPane.setCenter(waitingVBox);
        mainPane.setBottom(new ProgressHBox("Поиск соперника..."));

        stage.setScene(new Scene(mainPane, stage.getWidth(), stage.getHeight()));
        animate(mainPane);
    }

    @Override
    public KnifeLocationCanvas getKnifeLocationCanvas() {
        return new KnifeLocationCanvas();
    }

    // Private
    private void addActions() {
        waitingVBox.getCancelButton().setOnAction(event -> {
            AbstractController initialController = new InitialController(stage, socketClient);
            initialController.createScene();
        });

        headMenuBar.getMainItemLabel().setOnMouseClicked(event -> {
            AbstractController initialController = new InitialController(stage, socketClient);
            initialController.createScene();
        });
    }
}
