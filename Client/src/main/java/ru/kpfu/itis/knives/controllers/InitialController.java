package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.InitialVBox;
import ru.kpfu.itis.knives.view.ProgressHBox;

public final class InitialController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final InitialVBox initialVBox;

    // Init
    public InitialController(Stage stage) {
        super(stage);
        mainPane = new BorderPane();
        initialVBox = new InitialVBox();

        addActions();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(initialVBox);
        mainPane.setBottom(new ProgressHBox("Начните игру"));

        stage.setScene(new Scene(mainPane));
    }

    // Private
    private void addActions() {
        initialVBox.getStartButton().setOnAction(event -> {
            // TODO: Network actions
            AbstractController waitinController = new WaitingController(stage);
            waitinController.createScene();
        });
    }
}
