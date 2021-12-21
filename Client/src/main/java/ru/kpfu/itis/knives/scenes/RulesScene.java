package ru.kpfu.itis.knives.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.kpfu.itis.knives.helpers.Constants;

public class RulesScene extends Scene {
    // Const
    private static final int FONT_SIZE = 15;

    // UI
    private final StackPane mainPane;

    // Init
    public RulesScene(StackPane pane) {
        super(pane);
        mainPane = pane;

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setSpacing(40);
        contentBox.setPrefSize(400, 400);

        Label firstRuleLabel = new Label(Constants.FIRST_RULE);
        firstRuleLabel.setFont(Font.font(FONT_SIZE));
        firstRuleLabel.setWrapText(true);

        Label secondRuleLabel = new Label(Constants.SECOND_RULE);
        secondRuleLabel.setFont(Font.font(FONT_SIZE));
        secondRuleLabel.setWrapText(true);

        Label thirdRuleLabel = new Label(Constants.THIRD_RULE);
        thirdRuleLabel.setFont(Font.font(FONT_SIZE));
        thirdRuleLabel.setWrapText(true);

        Label fourthRuleLabel = new Label(Constants.FOURTH_RULE);
        fourthRuleLabel.setFont(Font.font(FONT_SIZE));
        fourthRuleLabel.setWrapText(true);

        contentBox.getChildren().addAll(firstRuleLabel, secondRuleLabel, thirdRuleLabel, fourthRuleLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);
    }
}
