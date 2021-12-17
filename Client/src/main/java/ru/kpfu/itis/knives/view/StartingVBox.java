package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.kpfu.itis.knives.helpers.Constants;

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
        mainNameLabel.setFont(Font.font(64));

        Label descriptionLabel = new Label("Игра начинается");
        descriptionLabel.setFont(Font.font(36));

        HBox playersHBox = new HBox();
        playersHBox.setAlignment(Pos.CENTER);
        playersHBox.setSpacing(250);

        // TODO: add animation for first player label
        Label currentPlayerLabel = new Label("Вы");
        currentPlayerLabel.setFont(Font.font(26));
        Label enemyPlayerLabel = new Label("Соперник");
        enemyPlayerLabel.setFont(Font.font(26));

        playersHBox.getChildren().addAll(currentPlayerLabel, enemyPlayerLabel);

        getChildren().addAll(mainNameLabel, descriptionLabel, playersHBox);
    }
}
