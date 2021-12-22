package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class StartingVBox extends VBox {
    // Init
    public StartingVBox() {
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(CustomFonts.sarpanchBold64.font);

        Label descriptionLabel = new Label("Игра начинается");
        descriptionLabel.setFont(CustomFonts.robotoBold36.font);

        HBox playersHBox = new HBox();
        playersHBox.setAlignment(Pos.CENTER);
        playersHBox.setSpacing(250);

        // TODO: add animation for first player label
        Label currentPlayerLabel = new Label("Вы");
        currentPlayerLabel.setFont(CustomFonts.robotoNormal26.font);
        Label enemyPlayerLabel = new Label("Соперник");
        enemyPlayerLabel.setFont(CustomFonts.robotoNormal26.font);

        playersHBox.getChildren().addAll(currentPlayerLabel, enemyPlayerLabel);

        getChildren().addAll(mainNameLabel, descriptionLabel, playersHBox);
    }
}
