package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public class StartingVBox extends VBox {
    // Init
    public StartingVBox() {
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setBackground(new Background(new BackgroundFill(Colors.beige.color, CornerRadii.EMPTY, Insets.EMPTY)));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Fonts.sarpanchBold64.font);

        Label descriptionLabel = new Label("Игра начинается");
        descriptionLabel.setFont(Fonts.robotoBold36.font);

        HBox playersHBox = new HBox();
        playersHBox.setAlignment(Pos.CENTER);
        playersHBox.setSpacing(200);

        // TODO: add animation for first player label
        Label currentPlayerLabel = new Label("Вы");
        currentPlayerLabel.setPadding(new Insets(5, 20, 5, 20));
        currentPlayerLabel.setFont(Fonts.robotoNormal26.font);
        currentPlayerLabel.setTextFill(Color.WHITE);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.color, new CornerRadii(15), Insets.EMPTY)));


        Label enemyPlayerLabel = new Label("Соперник");
        enemyPlayerLabel.setPadding(new Insets(5, 20, 5, 20));
        enemyPlayerLabel.setFont(Fonts.robotoNormal26.font);
        enemyPlayerLabel.setTextFill(Color.WHITE);
        enemyPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.color, new CornerRadii(15), Insets.EMPTY)));


        playersHBox.getChildren().addAll(currentPlayerLabel, enemyPlayerLabel);

        getChildren().addAll(mainNameLabel, descriptionLabel, playersHBox);
    }
}
