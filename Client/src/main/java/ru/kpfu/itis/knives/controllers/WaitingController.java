package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;
import ru.kpfu.itis.knives.view.WaitingVBox;

public class WaitingController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final WaitingVBox waitingVBox;

    // Init
    public WaitingController(Stage stage) {
        super(stage);
        mainPane = new BorderPane();
        waitingVBox = new WaitingVBox();

        stage.setMinWidth(600);
        stage.setMinHeight(400);
        addActions();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(waitingVBox);
        mainPane.setBottom(new ProgressHBox("Поиск соперника..."));

        stage.setScene(new Scene(mainPane, stage.getWidth(), stage.getHeight()));
    }

    // Private
    private void addActions() {
        waitingVBox.getCancelButton().setOnAction(event -> {
            // TODO: Network actions
            AbstractController initialController = new InitialController(stage);
            initialController.createScene();
        });
    }
}
