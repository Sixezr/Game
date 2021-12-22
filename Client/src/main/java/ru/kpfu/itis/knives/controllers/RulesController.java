package ru.kpfu.itis.knives.controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class RulesController extends AbstractController {
    // UI
    private final StackPane mainPane;

    // Init
    public RulesController(Stage stage) {
        super(stage);
        mainPane = new StackPane();
    }

    // Create scene
    @Override
    public void createScene() {
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(40);
        contentBox.setPrefSize(450, 400);

        Label firstRuleLabel = new Label(Constants.FIRST_RULE);
        firstRuleLabel.setFont(CustomFonts.robotoNormal15.font);
        firstRuleLabel.setTextAlignment(TextAlignment.CENTER);
        firstRuleLabel.setWrapText(true);

        Label secondRuleLabel = new Label(Constants.SECOND_RULE);
        secondRuleLabel.setFont(CustomFonts.robotoNormal15.font);
        secondRuleLabel.setTextAlignment(TextAlignment.CENTER);
        secondRuleLabel.setWrapText(true);

        Label thirdRuleLabel = new Label(Constants.THIRD_RULE);
        thirdRuleLabel.setFont(CustomFonts.robotoNormal15.font);
        thirdRuleLabel.setTextAlignment(TextAlignment.CENTER);
        thirdRuleLabel.setWrapText(true);

        Label fourthRuleLabel = new Label(Constants.FOURTH_RULE);
        fourthRuleLabel.setFont(CustomFonts.robotoNormal15.font);
        fourthRuleLabel.setTextAlignment(TextAlignment.CENTER);
        fourthRuleLabel.setWrapText(true);

        contentBox.getChildren().addAll(firstRuleLabel, secondRuleLabel, thirdRuleLabel, fourthRuleLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);

        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}
