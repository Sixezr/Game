package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.view.EndVBox;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;

public class GameOverController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final EndVBox endVBox;
    private final HeadMenuBar headMenuBar;

    // Init
    public GameOverController(Stage stage, boolean isCurrentPlayerWon) {
        super(stage);
        mainPane = new BorderPane();
        endVBox = new EndVBox(isCurrentPlayerWon);
        headMenuBar = new HeadMenuBar();

        stage.setMinWidth(600);
        stage.setMinHeight(470);

        addActions();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(headMenuBar);
        mainPane.setCenter(endVBox);
        mainPane.setBottom(new ProgressHBox("Игра окончена"));

        stage.setScene(new Scene(mainPane));
        animate(mainPane);
    }

    // Private
    private void addActions() {
        endVBox.getOkButton().setOnAction(event -> {
            AbstractController initialController = new InitialController(stage);
            initialController.createScene();
        });

        headMenuBar.getMainItemLabel().setOnMouseClicked(event -> {
            AbstractController initialController = new InitialController(stage);
            initialController.createScene();
        });
    }
}
