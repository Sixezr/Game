package ru.kpfu.itis.knives.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public class RulesController extends AbstractController {
    // UI
    private final StackPane mainPane;

    // Init
    public RulesController(Stage stage) {
        super(stage);
        mainPane = new StackPane();

        stage.setMinWidth(450);
        stage.setMinHeight(500);
    }

    // Create scene
    @Override
    public void createScene() {
        VBox contentBox = new VBox();
        contentBox.setBackground(new Background(new BackgroundFill(Colors.beige.color, CornerRadii.EMPTY, Insets.EMPTY)));
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(40);
        contentBox.setPadding(new Insets(10, 20, 10, 20));
        contentBox.setPrefSize(450, 450);

        Label firstRuleLabel = new Label(Constants.FIRST_RULE);
        firstRuleLabel.setFont(Fonts.robotoNormal15.font);
        firstRuleLabel.setTextAlignment(TextAlignment.CENTER);
        firstRuleLabel.setWrapText(true);

        Label secondRuleLabel = new Label(Constants.SECOND_RULE);
        secondRuleLabel.setFont(Fonts.robotoNormal15.font);
        secondRuleLabel.setTextAlignment(TextAlignment.CENTER);
        secondRuleLabel.setWrapText(true);

        Label thirdRuleLabel = new Label(Constants.THIRD_RULE);
        thirdRuleLabel.setFont(Fonts.robotoNormal15.font);
        thirdRuleLabel.setTextAlignment(TextAlignment.CENTER);
        thirdRuleLabel.setWrapText(true);

        Label fourthRuleLabel = new Label(Constants.FOURTH_RULE);
        fourthRuleLabel.setFont(Fonts.robotoNormal15.font);
        fourthRuleLabel.setTextAlignment(TextAlignment.CENTER);
        fourthRuleLabel.setWrapText(true);

        contentBox.getChildren().addAll(firstRuleLabel, secondRuleLabel, thirdRuleLabel, fourthRuleLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);

        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}
