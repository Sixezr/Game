package ru.kpfu.itis.knives.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.knives.App;
import ru.kpfu.itis.knives.view.GameFieldCanvas;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;

public class GameScene extends Scene {
    // UI
    private final BorderPane mainPane;
    private final ProgressHBox progressHBox;
    private final Label statusLabel;
    private final GameFieldCanvas gameFieldCanvas;

    // Init
    public GameScene(BorderPane pane) {
        super(pane, App.getMainStage().getWidth(), App.getMainStage().getHeight());
        mainPane = pane;
        mainPane.setTop(new HeadMenuBar());
        progressHBox = new ProgressHBox("Игра началась");
        mainPane.setBottom(progressHBox);

        VBox contentVBox = new VBox();
        contentVBox.setSpacing(64);
        contentVBox.setAlignment(Pos.CENTER);

        statusLabel = new Label("Игра началась");

        // TODO: add game circle to HBox and to right add messages
        HBox contentHBox = new HBox();

        gameFieldCanvas = new GameFieldCanvas();
        contentVBox.getChildren().addAll(statusLabel, gameFieldCanvas);
        mainPane.setCenter(contentVBox);
    }
}
