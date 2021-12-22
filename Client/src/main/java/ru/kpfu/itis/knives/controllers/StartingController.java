package ru.kpfu.itis.knives.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;
import ru.kpfu.itis.knives.view.StartingVBox;

public class StartingController extends AbstractController {
    // UI
    private final BorderPane mainPane;

    // Init
    public StartingController(Stage stage) {
        super(stage);
        mainPane = new BorderPane();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(new StartingVBox());
        mainPane.setBottom(new ProgressHBox("Игра начинается"));

        stage.setScene(new Scene(mainPane));
    }
}
