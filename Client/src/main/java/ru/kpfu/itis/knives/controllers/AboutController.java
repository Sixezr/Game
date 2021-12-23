package ru.kpfu.itis.knives.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public final class AboutController extends AbstractController {
    // UI
    private final StackPane mainPane;

    // Init
    public AboutController(Stage stage) {
        super(stage);
        mainPane = new StackPane();

        stage.setMinWidth(300);
        stage.setMinHeight(275);
    }

    // Create scene
    @Override
    public void createScene() {
        VBox contentBox = new VBox();
        contentBox.setBackground(new Background(new BackgroundFill(Colors.beige.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(20);
        contentBox.setPrefSize(300, 275);

        Label titleLabel = new Label("Разработчики:");
        titleLabel.setFont(Fonts.robotoBold36.getFont());
        titleLabel.setWrapText(true);

        Label firstDevLabel = new Label(Constants.FIRST_DEVELOPER);
        firstDevLabel.setFont(Fonts.robotoNormal24.getFont());
        firstDevLabel.setWrapText(true);

        Label secondDevLabel = new Label(Constants.SECOND_DEVELOPER);
        secondDevLabel.setFont(Fonts.robotoNormal24.getFont());
        secondDevLabel.setWrapText(true);

        Label thirdDevLabel = new Label(Constants.THIRD_DEVELOPER);
        thirdDevLabel.setFont(Fonts.robotoNormal24.getFont());
        thirdDevLabel.setWrapText(true);

        Label fourthDevLabel = new Label(Constants.FOURTH_DEVELOPER);
        fourthDevLabel.setFont(Fonts.robotoNormal24.getFont());
        fourthDevLabel.setWrapText(true);

        contentBox.getChildren().addAll(titleLabel, firstDevLabel, secondDevLabel, thirdDevLabel, fourthDevLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);

        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}
