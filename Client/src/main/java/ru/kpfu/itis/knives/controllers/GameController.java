package ru.kpfu.itis.knives.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Fonts;
import ru.kpfu.itis.knives.helpers.KnifeState;
import ru.kpfu.itis.knives.view.GameFieldCanvas;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.KnifeLocationCanvas;
import ru.kpfu.itis.knives.view.ProgressHBox;

public class GameController extends AbstractController {
    // UI
    private final BorderPane mainPane;
    private final ProgressHBox progressHBox;
    private final Label statusLabel;
    private final GameFieldCanvas gameFieldCanvas;
    private final KnifeLocationCanvas knifeLocationCanvas;
    private final VBox messagesVBox;
    private final Label messageLabel;

    // Init
    public GameController(Stage stage) {
        super(stage);

        mainPane = new BorderPane();
        progressHBox = new ProgressHBox("Игра началась");
        statusLabel = new Label("Игра началась");
        gameFieldCanvas = new GameFieldCanvas();
        messagesVBox = new VBox();
        messageLabel = new Label("Ваш ход");
        knifeLocationCanvas = new KnifeLocationCanvas();

        stage.setMinWidth(1125);
        stage.setMinHeight(700);

        initListeners();
    }

    // Create scene
    @Override
    public void createScene() {
        mainPane.setTop(new HeadMenuBar());

        mainPane.setBottom(progressHBox);

        VBox contentVBox = new VBox();
        contentVBox.setSpacing(64);
        contentVBox.setAlignment(Pos.CENTER);

        statusLabel.setFont(Fonts.robotoNormal30.font);

        HBox contentHBox = new HBox();
        contentHBox.setBackground(new Background(new BackgroundFill(Colors.beige.color, CornerRadii.EMPTY, Insets.EMPTY)));
        contentHBox.setAlignment(Pos.CENTER);
        contentHBox.setSpacing(200);

        messagesVBox.setAlignment(Pos.CENTER);
        messagesVBox.setSpacing(80);

        messageLabel.setFont(Fonts.robotoNormal36.font);

        messagesVBox.getChildren().addAll(messageLabel, knifeLocationCanvas);
        contentVBox.getChildren().addAll(statusLabel, gameFieldCanvas);
        contentHBox.getChildren().addAll(contentVBox, messagesVBox);

        mainPane.setCenter(contentHBox);

        stage.setScene(new Scene(mainPane));
    }

    // Private
    private void initListeners() {
        gameFieldCanvas.setOnMouseClicked(event -> {
            gameFieldCanvas.drawPoint(event.getX(), event.getY(), Color.AQUA);
            knifeLocationCanvas.drawKnifeWithIncline(15, KnifeState.success);
        });
    }
}
