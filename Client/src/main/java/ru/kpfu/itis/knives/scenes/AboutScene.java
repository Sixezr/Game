package ru.kpfu.itis.knives.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class AboutScene extends Scene {
    // UI
    private final StackPane mainPane;

    // Init
    public AboutScene(StackPane pane) {
        super(pane);
        mainPane = pane;

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setSpacing(20);
        contentBox.setPrefSize(300, 200);

        Label titleLabel = new Label("Разработчики:");
        titleLabel.setFont(CustomFonts.robotoBold36.font);
        titleLabel.setWrapText(true);

        Label firstDevLabel = new Label(Constants.FIRST_DEVELOPER);
        firstDevLabel.setFont(CustomFonts.robotoNormal24.font);
        firstDevLabel.setWrapText(true);

        Label secondDevLabel = new Label(Constants.SECOND_DEVELOPER);
        secondDevLabel.setFont(CustomFonts.robotoNormal24.font);
        secondDevLabel.setWrapText(true);

        Label thirdDevLabel = new Label(Constants.THIRD_DEVELOPER);
        thirdDevLabel.setFont(CustomFonts.robotoNormal24.font);
        thirdDevLabel.setWrapText(true);

        Label fourthDevLabel = new Label(Constants.FOURTH_DEVELOPER);
        fourthDevLabel.setFont(CustomFonts.robotoNormal24.font);
        fourthDevLabel.setWrapText(true);

        contentBox.getChildren().addAll(titleLabel, firstDevLabel, secondDevLabel, thirdDevLabel, fourthDevLabel);

        mainPane.getChildren().addAll(contentBox);
        StackPane.setAlignment(contentBox, Pos.CENTER);
    }
}
