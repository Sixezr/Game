package ru.kpfu.itis.knives.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.kpfu.itis.knives.helpers.Constants;

public class AboutScene extends Scene {
    // Const
    private static final int FONT_SIZE = 24;

    // UI
    private final StackPane mainPane;

    // Init
    public AboutScene(StackPane pane) {
        super(pane);
        mainPane = pane;

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setSpacing(20);
        contentBox.setPrefSize(400, 200);

        Label titleLabel = new Label("Разработчики:");
        titleLabel.setFont(Font.font(30));
        titleLabel.setWrapText(true);

        Label firstDevLabel = new Label(Constants.FIRST_DEVELOPER);
        firstDevLabel.setFont(Font.font(FONT_SIZE));
        firstDevLabel.setWrapText(true);

        Label secondDevLabel = new Label(Constants.SECOND_DEVELOPER);
        secondDevLabel.setFont(Font.font(FONT_SIZE));
        secondDevLabel.setWrapText(true);

        Label thirdDevLabel = new Label(Constants.THIRD_DEVELOPER);
        thirdDevLabel.setFont(Font.font(FONT_SIZE));
        thirdDevLabel.setWrapText(true);

        Label fourthDevLabel = new Label(Constants.FOURTH_DEVELOPER);
        fourthDevLabel.setFont(Font.font(FONT_SIZE));
        fourthDevLabel.setWrapText(true);

        contentBox.getChildren().addAll(titleLabel, firstDevLabel, secondDevLabel, thirdDevLabel, fourthDevLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);
    }
}
